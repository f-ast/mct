/**
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alexander Fedorov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.bridge.transform;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.internal.bridge.ui.Plugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

/*
 * XXX: duplicates functionality of 
 * org.eclipse.gmf.internal.codegen.popup.actions.TransformToGenModelOptionsDialog 
 * and
 * org.eclipse.gmf.internal.graphdef.codegen.ui.FigureGeneratorOptionsDialog
 */
class ViewmapProducerWizardPage extends WizardPage {
	
    private Button generateRCPButton;
    private Button useMapModeButton;
    private Button useRuntimeFiguresButton;
	private Text templatesPathControl;

	protected ViewmapProducerWizardPage(String pageName) {
		super(pageName);
	}
	
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		createControls(composite);
		initControls();
		validatePage();

		setControl(composite);
		Dialog.applyDialogFont(composite);
	}

	private void createControls(Composite result) {
        useMapModeButton = new Button(result, SWT.CHECK);
        useMapModeButton.setText(Messages.ViewmapProducerWizardPage_btn_mapmode);
        useMapModeButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER));
        useRuntimeFiguresButton = new Button(result, SWT.CHECK);
        useRuntimeFiguresButton.setText(Messages.ViewmapProducerWizardPage_btn_runtime);
        useRuntimeFiguresButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER));
        generateRCPButton = new Button(result, SWT.CHECK);
        generateRCPButton.setText(Messages.ViewmapProducerWizardPage_btn_rcp);
        generateRCPButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER));
        SelectionListener selectionListener = new SelectionListener() {
        	public void widgetDefaultSelected(SelectionEvent e) {
        		handleSelection(e.widget);
        	}
        	public void widgetSelected(SelectionEvent e) {
        		handleSelection(e.widget);
        	}
		};
        useMapModeButton.addSelectionListener(selectionListener);
        useRuntimeFiguresButton.addSelectionListener(selectionListener);
        generateRCPButton.addSelectionListener(selectionListener);
        createTemplatePathControl(result);
        Composite glue = new Composite(result, SWT.NONE);
        glue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	private void createTemplatePathControl(Composite result) {
		Group parent = new Group(result, SWT.SHADOW_ETCHED_IN);
		parent.setText("Provisional");
		parent.setLayout(new FillLayout());
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		ExpandBar c = new ExpandBar(parent, SWT.NONE);
		c.setBackground(parent.getBackground());
        templatesPathControl = new Text(c, SWT.SINGLE | SWT.BORDER);
		templatesPathControl.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validatePage();
			}
		});
        ExpandItem item = new ExpandItem(c, SWT.NONE, 0);
        item.setText("GMFGraph dynamic templates");
		item.setHeight(templatesPathControl.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item.setControl(templatesPathControl);
		if (getOperation().getOptions().getFigureTemplatesPath() != null) {
			// reveal the value to avoid confusion.
			// FIXME extract expand bar with template path as separate control and
			// move expand logic there (based on setInitialValue event
			item.setExpanded(true);
		}
			
	}

	void handleSelection(Widget w) {
		if (generateRCPButton.equals(w)){
			getOperation().getOptions().setGenerateRCP(generateRCPButton.getSelection());
		} else if (useMapModeButton.equals(w)) {
			getOperation().getOptions().setUseMapMode(useMapModeButton.getSelection());
		} else if (useRuntimeFiguresButton.equals(w)) {
			getOperation().getOptions().setUseRuntimeFigures(useRuntimeFiguresButton.getSelection());
		}
		validatePage();
	}
	
	private void validatePage() {
		IStatus checkOptions = checkOptions();
		if (checkOptions.isOK()) {
			setMessage(null);
			setPageComplete(true);
		} else {
    		setMessage(checkOptions.getMessage(), IMessageProvider.INFORMATION);
			setPageComplete(checkOptions.getSeverity() < IStatus.WARNING);
		}
	}
	
	private IStatus checkOptions() {
		boolean hasLite = TransformOptions.checkLiteOptionPresent();
		if (hasLite) {
			if (!useRuntimeFiguresButton.getSelection() && useMapModeButton.getSelection()) {
				return Plugin.createInfo(Messages.ViewmapProducerWizardPage_i_not_recommended);
			}
		}
		if (templatesPathControl.getText().trim().length() > 0) {
			try {
				URL res = new URL(guessAndResolvePathURL(templatesPathControl.getText().trim()));
				// safe to set option value now as they get flushed into storage only on Wizard.performFinish
				getOperation().getOptions().setFigureTemplatesPath(res);
			} catch (MalformedURLException ex) {
				return Plugin.createWarning(ex.getMessage());
			}
		} else {
			if (getOperation().getOptions().getFigureTemplatesPath() != null) {
				getOperation().getOptions().setFigureTemplatesPath(null);
			}
		}
		return Status.OK_STATUS;
	}

	private void initControls() {
		TransformOptions options = getOperation().getOptions();
		generateRCPButton.setSelection(options.getGenerateRCP());
		boolean hasLite = TransformOptions.checkLiteOptionPresent();
		boolean useRuntimeFigures = options.getUseRuntimeFigures();
		if (hasLite) {
			useRuntimeFiguresButton.setEnabled(true);
			useRuntimeFiguresButton.setSelection(useRuntimeFigures);
		} else {
			useRuntimeFiguresButton.setEnabled(false);
			useRuntimeFiguresButton.setSelection(true);
			options.setUseRuntimeFigures(true);
		}
		useMapModeButton.setSelection(options.getUseMapMode());
		if (null != options.getFigureTemplatesPath()) {
			templatesPathControl.setText(options.getFigureTemplatesPath().toString());
		}
	}

	private TransformToGenModelOperation getOperation() {
		TransformToGenModelWizard wizard = (TransformToGenModelWizard) getWizard();
		return wizard.getTransformOperation();
	}

	private static String guessAndResolvePathURL(String path) {
		assert path != null;
		URI templatesURI = path.indexOf(':') == -1 ? URI.createPlatformResourceURI(path, true) : URI.createURI(path);
		return CommonPlugin.resolve(templatesURI).toString();
	}
}