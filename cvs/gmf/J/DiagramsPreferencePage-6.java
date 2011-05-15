/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.diagram.ui.preferences;

import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


/**
 * Top-level "Diagrams" preference page to hold preferences relating to diagram
 * functionality.
 * 
 * @author schafe, cmahoney
 * @canBeSeenBy org.eclipse.gmf.runtime.diagram.ui.*
 */
public class DiagramsPreferencePage
	extends AbstractPreferencePage {

	//	localized labels
	private String GLOBAL_SETTINGS_GROUP_LABEL = DiagramUIMessages.DiagramsPreferencePage_globalGroup_label;

	private String SHOW_CONNECTION_HANDLES_LABEL = DiagramUIMessages.DiagramsPreferencePage_showConnectionHandles_label;

	private String SHOW_POPUP_BARS_LABEL = DiagramUIMessages.DiagramsPreferencePage_showPopupBars_label;

	private String ENABLE_ANIMATED_LAYOUT = DiagramUIMessages.DiagramsPreferencePage_enableAnimatedLayout_label;

	private String ENABLE_ANIMATED_ZOOM = DiagramUIMessages.DiagramsPreferencePage_enableAnimatedZoom_label;

	private String ENABLE_ANTIALIAS = DiagramUIMessages.DiagramsPreferencePage_enableAntiAlias_label;	
	
	// preference page editor controls
	private BooleanFieldEditor showConnectionHandles = null;

	private BooleanFieldEditor showPopupBars = null;
	
	private BooleanFieldEditor enableAnimatedLayout = null;

	private BooleanFieldEditor enableAnimatedZoom = null;

	private BooleanFieldEditor enableAntiAlias = null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#addFields(org.eclipse.swt.widgets.Composite)
	 */
	protected void addFields(Composite parent) {

		Group generalGlobalGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		generalGlobalGroup.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		generalGlobalGroup.setLayoutData(gridData);
		generalGlobalGroup.setText(GLOBAL_SETTINGS_GROUP_LABEL);

		Composite composite = new Composite(generalGlobalGroup, SWT.NONE);		

		showConnectionHandles = new BooleanFieldEditor(
			IPreferenceConstants.PREF_SHOW_CONNECTION_HANDLES,
			SHOW_CONNECTION_HANDLES_LABEL, composite);
		addField(showConnectionHandles);

		showPopupBars = new BooleanFieldEditor(
			IPreferenceConstants.PREF_SHOW_POPUP_BARS, SHOW_POPUP_BARS_LABEL,
			composite);
		addField(showPopupBars);
		
		enableAnimatedLayout = new BooleanFieldEditor(
			IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, ENABLE_ANIMATED_LAYOUT,
			composite);
		addField(enableAnimatedLayout);
		
		enableAnimatedZoom = new BooleanFieldEditor(
			IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, ENABLE_ANIMATED_ZOOM,
			composite);
		addField(enableAnimatedZoom);

		enableAntiAlias = new BooleanFieldEditor(
			IPreferenceConstants.PREF_ENABLE_ANTIALIAS, ENABLE_ANTIALIAS,
			composite);
		addField(enableAntiAlias);
        
        // Anti Alias is disabled if the RTL orintation is ON
        // this is to avoid an SWT bug, on the GC object, where if the Advanced
        // graphics Mask is on it does not consider mirroring
        enableAntiAlias.setEnabled(Window.getDefaultOrientation()!=SWT.RIGHT_TO_LEFT,composite);
		
	}

	
	/**
	 * Initializes the default preference values for this preference store.
	 * 
	 * @param IPreferenceStore preferenceStore
	 */
	public static void initDefaults(IPreferenceStore preferenceStore) {

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_CONNECTION_HANDLES, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_POPUP_BARS, true);
	
		preferenceStore.setDefault(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, true);
		
		preferenceStore.setDefault(IPreferenceConstants.PREF_ENABLE_ANTIALIAS, true);	

	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#initHelp()
	 */
	protected void initHelp() {
		//setPageHelpContextId(IHelpContextIds.VZ_U_UMLV_PAGE_PREF);
		//do nothing, no context help for modeler yet
	}
	
}
