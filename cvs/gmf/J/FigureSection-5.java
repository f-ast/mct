/*
 * Copyright (c) 2008 Borland Software Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.sheet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.gmfgraph.LineKind;
import org.eclipse.gmf.gmfgraph.RoundedRectangle;
import org.eclipse.gmf.gmfgraph.Shape;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class FigureSection extends AbstractPropertySection implements ChangeTracker, Listener {

	private org.eclipse.emf.common.notify.Adapter[] myModelListeners;

	private boolean myIsCommit;
	private boolean myIsRefresh;
	private Object myInput;

	private Group myStyleRadios;
	private Group myCommonStyle;
	private Group myRoundedRectStyle;
	private Button myR1;
	private Button myR2;
	private Button myR3;
	private Button myR4;
	private Button myR5;
	private Button myR6;
	private Spinner myLineWidth;
	private Button myFill;
	private Button myOutline;
	private Button myFillXor;
	private Button myOutlineXor;
	private Spinner myCornerWidth;
	private Spinner myCornerHeight;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage page) {
		super.createControls(parent, page);
		getWidgetFactory().paintBordersFor(parent);
		myStyleRadios = createGroup(parent, "Line Style");
		myR1 = getWidgetFactory().createButton(myStyleRadios, "Solid", SWT.RADIO);
		myR2 = getWidgetFactory().createButton(myStyleRadios, "- - -", SWT.RADIO);
		myR3 = getWidgetFactory().createButton(myStyleRadios, ". . .", SWT.RADIO);
		myR4 = getWidgetFactory().createButton(myStyleRadios, "- . - .", SWT.RADIO);
		myR5 = getWidgetFactory().createButton(myStyleRadios, "- . . - . .", SWT.RADIO);
		myR6 = getWidgetFactory().createButton(myStyleRadios, "Custom", SWT.RADIO);
		myStyleRadios.setLayout(new org.eclipse.swt.layout.FillLayout(org.eclipse.swt.SWT.VERTICAL));
		myCommonStyle = createGroup(parent, "Draw");
		createLabel(myCommonStyle, "Line width");
		myLineWidth = new Spinner(myCommonStyle, SWT.FLAT);
		myLineWidth.setMinimum(0);
		myLineWidth.setMaximum(100);
		myLineWidth.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myFill = getWidgetFactory().createButton(myCommonStyle, "Fill", SWT.CHECK);
		myOutline = getWidgetFactory().createButton(myCommonStyle, "Outline", SWT.CHECK);
		myFillXor = getWidgetFactory().createButton(myCommonStyle, "XOR Fill", SWT.CHECK);
		myOutlineXor = getWidgetFactory().createButton(myCommonStyle, "XOR Outline", SWT.CHECK);
		myCommonStyle.setLayout(new org.eclipse.swt.layout.GridLayout(2, true));
		myRoundedRectStyle = createGroup(parent, "Corners");
		createLabel(myRoundedRectStyle, "Width:");
		myCornerWidth = new Spinner(myRoundedRectStyle, SWT.FLAT);
		myCornerWidth.setMinimum(0);
		myCornerWidth.setMaximum(100);
		myCornerWidth.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myRoundedRectStyle, "Height:");
		myCornerHeight = new Spinner(myRoundedRectStyle, SWT.FLAT);
		myCornerHeight.setMinimum(0);
		myCornerHeight.setMaximum(100);
		myCornerHeight.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myRoundedRectStyle.setLayout(new org.eclipse.swt.layout.GridLayout(2, true));

		parent.setLayout(new org.eclipse.swt.layout.FormLayout());
		org.eclipse.swt.layout.FormData fd;
		fd = new org.eclipse.swt.layout.FormData();
		fd.left = new org.eclipse.swt.layout.FormAttachment(0, 10);
		myStyleRadios.setLayoutData(fd);
		fd = new org.eclipse.swt.layout.FormData();
		fd.left = new org.eclipse.swt.layout.FormAttachment(myStyleRadios, 10, org.eclipse.swt.SWT.RIGHT);
		myCommonStyle.setLayoutData(fd);
		fd = new org.eclipse.swt.layout.FormData();
		fd.left = new org.eclipse.swt.layout.FormAttachment(myStyleRadios, 10, org.eclipse.swt.SWT.RIGHT);
		fd.top = new org.eclipse.swt.layout.FormAttachment(myCommonStyle, 5, org.eclipse.swt.SWT.BOTTOM);
		myRoundedRectStyle.setLayoutData(fd);

		for (Spinner s : new Spinner[] { myLineWidth, myCornerWidth, myCornerHeight }) {
			s.addListener(SWT.Modify, this);
			s.addListener(SWT.FocusOut, this);
		}
		for (Widget w : new Widget[] { myR1, myR2, myR3, myR4, myR5, myR6, myFill, myOutline, myFillXor, myOutlineXor }) {
			w.addListener(SWT.Selection, this);
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Object newInput = null;
		if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
			newInput = unwrap(((IStructuredSelection) selection).getFirstElement());
		}
		if (newInput != myInput) {
			if (myInput != null) {
				detach();
			}
			myInput = newInput;
			if (newInput != null) {
				attach();
			}
		}
	}

	protected void commit() {

		getInput().setLineWidth(myLineWidth.getSelection());
		getInput().setFill(myFill.getSelection());
		getInput().setOutline(myOutline.getSelection());
		getInput().setXorFill(myFillXor.getSelection());
		getInput().setXorOutline(myOutlineXor.getSelection());

		if (myR1.getSelection()) {
			getInput().setLineKind(LineKind.LINE_SOLID_LITERAL);
		}
		if (myR2.getSelection()) {
			getInput().setLineKind(LineKind.LINE_DASH_LITERAL);
		}
		if (myR3.getSelection()) {
			getInput().setLineKind(LineKind.LINE_DOT_LITERAL);
		}
		if (myR4.getSelection()) {
			getInput().setLineKind(LineKind.LINE_DASHDOT_LITERAL);
		}
		if (myR5.getSelection()) {
			getInput().setLineKind(LineKind.LINE_DASHDOTDOT_LITERAL);
		}
		if (myR6.getSelection()) {
			getInput().setLineKind(LineKind.LINE_CUSTOM_LITERAL);
		}
		if (myRoundedRectStyle.isVisible()) {
			((RoundedRectangle) getInput()).setCornerWidth(myCornerWidth.getSelection());
			((RoundedRectangle) getInput()).setCornerHeight(myCornerHeight.getSelection());
		}
	}

	@Override
	public void refresh() {
		myIsRefresh = true;

		myLineWidth.setSelection(getInput().getLineWidth());
		myFill.setSelection(getInput().isFill());
		myOutline.setSelection(getInput().isOutline());
		myFillXor.setSelection(getInput().isXorFill());
		myOutlineXor.setSelection(getInput().isXorOutline());

		if (getInput().getLineKind() == LineKind.LINE_SOLID_LITERAL) {
			myR1.setSelection(true);
		} else {
			myR1.setSelection(false);
		}
		if (getInput().getLineKind() == LineKind.LINE_DASH_LITERAL) {
			myR2.setSelection(true);
		} else {
			myR2.setSelection(false);
		}
		if (getInput().getLineKind() == LineKind.LINE_DOT_LITERAL) {
			myR3.setSelection(true);
		} else {
			myR3.setSelection(false);
		}
		if (getInput().getLineKind() == LineKind.LINE_DASHDOT_LITERAL) {
			myR4.setSelection(true);
		} else {
			myR4.setSelection(false);
		}
		if (getInput().getLineKind() == LineKind.LINE_DASHDOTDOT_LITERAL) {
			myR5.setSelection(true);
		} else {
			myR5.setSelection(false);
		}
		if (getInput().getLineKind() == LineKind.LINE_CUSTOM_LITERAL) {
			myR6.setSelection(true);
		} else {
			myR6.setSelection(false);
		}
		if (getInput() instanceof RoundedRectangle) {
			myCornerWidth.setSelection(((RoundedRectangle) getInput()).getCornerWidth());
			myCornerHeight.setSelection(((RoundedRectangle) getInput()).getCornerHeight());
			myRoundedRectStyle.setVisible(true);
		} else {
			myRoundedRectStyle.setVisible(false);
		}

		myIsRefresh = false;
	}

	@Override
	public void aboutToBeHidden() {
		if (myInput != null) {
			detach();
		}
	}

	public void handleEvent(Event event) {
		if (myIsRefresh) {
			return;
		}

		if (event.type == SWT.Modify) {
			// XXX also override isDirty to compare values if they
			// match model's and to clear dirty state in case like aaa^H^H^H
			markDirty();
		} else if (event.type == SWT.FocusOut) {
			applyChanges();
		} else if (event.type == SWT.KeyDown) {
			if (event.keyCode == SWT.ESC) {
				discardChanges();
			} else if (event.keyCode == SWT.CR) {
				applyChanges();
			}
		}
		if (event.type == SWT.Selection) {
			if (myFill == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myOutline == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myFillXor == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myOutlineXor == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			}
			if (myR1 == event.widget) {
				if (myR1.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR2 == event.widget) {
				if (myR2.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR3 == event.widget) {
				if (myR3.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR4 == event.widget) {
				if (myR4.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR5 == event.widget) {
				if (myR5.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			} else if (myR6 == event.widget) {
				if (myR6.getSelection()) {
					applyChanges(); // Commit; View to Model
				}
			}

		}
	}

	public void modelChanged(Notification msg) {
		if (!myIsCommit && Display.getCurrent() != null) {
			refresh();
		}
	}

	private void markDirty() {
		// NO-OP, need that to share Update::handleEvent template with IFormPart which has same method
		// Perhaps, clients may find this method useful for some purpose?
	}

	protected void applyChanges() {
		try {
			myIsCommit = true;
			commit();
		} finally {
			myIsCommit = false;
		}
	}

	protected void discardChanges() {
		refresh();
	}

	protected Object unwrap(Object element) {
		// TODO may need to adapt selected element to smth else,
		// do it here
		return element;
	}

	private void attach() {
		myModelListeners = new org.eclipse.emf.common.notify.Adapter[] { new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getShape_LineKind(), GMFGraphPackage.eINSTANCE.getShape_LineWidth(),
				GMFGraphPackage.eINSTANCE.getShape_Fill(), GMFGraphPackage.eINSTANCE.getShape_Outline(), GMFGraphPackage.eINSTANCE.getShape_XorFill(), GMFGraphPackage.eINSTANCE.getShape_XorOutline(),
				GMFGraphPackage.eINSTANCE.getRoundedRectangle_CornerWidth(), GMFGraphPackage.eINSTANCE.getRoundedRectangle_CornerHeight()) };
		getInput().eAdapters().addAll(java.util.Arrays.asList(myModelListeners));

	}

	private void detach() {
		if (myModelListeners != null) {
			getInput().eAdapters().removeAll(java.util.Arrays.asList(myModelListeners));
			myModelListeners = null;
		}

	}

	protected Shape getInput() {
		// TODO implement;
		return (Shape) myInput;
	}

	private org.eclipse.swt.widgets.Label createLabel(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Label l = new org.eclipse.swt.widgets.Label(parent, SWT.NONE);
		if (label != null)
			l.setText(label);
		getWidgetFactory().adapt(l, false, false);
		return l;
	}

	private org.eclipse.swt.widgets.Group createGroup(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Group g = new org.eclipse.swt.widgets.Group(parent, SWT.SHADOW_NONE);
		if (label != null)
			g.setText(label);
		getWidgetFactory().adapt(g, false, false);
		getWidgetFactory().paintBordersFor(g);
		return g;
	}

}