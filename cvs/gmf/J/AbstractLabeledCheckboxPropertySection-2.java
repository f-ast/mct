/*+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004, 2005.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractNotationPropertiesSection;
import com.ibm.xtools.notation.View;

/**
 * @author dlander
 * @canBeSeenBy com.ibm.xtools.uml.ui.diagram.*
 */
public abstract class AbstractLabeledCheckboxPropertySection
	extends AbstractNotationPropertiesSection {

	//	Abstract methods to provide us with basic data.
	// Command info. Visible to user in undo/redo
	public abstract String getCommandName();

	// Label string positioned left of control
	public abstract String getPrefixLabel();

	// ID to use in communicating with model
	public abstract String getNameLabel();

	// List of entries to populate with
	public abstract String getID();

	// Controls
	private Button checkbox;

	private CLabel leftLabel;

	/**
	 * @see org.eclipse.gmf.runtime.common.ui.properties.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {

		try {
			executeAsReadAction(new Runnable() {

				public void run() {

					// Update display from model
					if (getSingleInput() instanceof GraphicalEditPart) {
						GraphicalEditPart ep = (GraphicalEditPart) getSingleInput();
						checkbox.setSelection(((Boolean) ep
							.getPropertyValue(getID())).booleanValue());
					} else if (getSingleInput() instanceof ConnectionNodeEditPart) {
						ConnectionNodeEditPart ep = (ConnectionNodeEditPart) getSingleInput();
						checkbox.setSelection(((Boolean) ep
							.getPropertyValue(getID())).booleanValue());
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//blank
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.common.ui.properties.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.gmf.runtime.common.ui.properties.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);


		FormData data;

		// Create checkbox
		checkbox = getWidgetFactory().createButton(composite, getNameLabel(),
			SWT.CHECK); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(parent));
		data.top = new FormAttachment(0, 0);
		checkbox.setLayoutData(data);
		checkbox.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {

				if (isReadOnly()) {
					refresh();
					return;
				}

				ArrayList commands = new ArrayList();

				Iterator it = getInput().iterator();

				while (it.hasNext()) {
					final AbstractEditPart aep = (AbstractEditPart) it.next();

					Resource res = ((View)aep.getModel()).eResource();

					commands.add(createCommand(getCommandName(), res,
						new Runnable() {

							public void run() {
								if (aep instanceof GraphicalEditPart) {
									GraphicalEditPart ep = (GraphicalEditPart) aep;
									ep.setPropertyValue(getID(), new Boolean(
										checkbox.getSelection()));
								} else if (aep instanceof ConnectionNodeEditPart) {
									ConnectionNodeEditPart ep = (ConnectionNodeEditPart) aep;
									ep.setPropertyValue(getID(), new Boolean(
										checkbox.getSelection()));
								}
							}
						}));
				}

				executeAsCompositeCommand(getCommandName(), commands);
			}
		});

		// Create label
		leftLabel = getWidgetFactory()
			.createCLabel(composite, getPrefixLabel()); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(checkbox, 0, SWT.CENTER);
		leftLabel.setLayoutData(data);

	}
}
