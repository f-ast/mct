/*+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.l10n.PresentationResourceManager;


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
	private String GLOBAL_SETTINGS_GROUP_LABEL = PresentationResourceManager
		.getI18NString("DiagramsPreferencePage.globalGroup.label"); //$NON-NLS-1$

	private String SHOW_CONNECTOR_HANDLES_LABEL = PresentationResourceManager
		.getI18NString("DiagramsPreferencePage.showConnectorHandles.label"); //$NON-NLS-1$

	private String SHOW_ACTION_BARS_LABEL = PresentationResourceManager
		.getI18NString("DiagramsPreferencePage.showActionBars.label"); //$NON-NLS-1$

	private String ENABLE_ANIMATED_LAYOUT = PresentationResourceManager
	.getI18NString("DiagramsPreferencePage.enableAnimatedLayout.label"); //$NON-NLS-1$

	private String ENABLE_ANIMATED_ZOOM = PresentationResourceManager
	.getI18NString("DiagramsPreferencePage.enableAnimatedZoom.label"); //$NON-NLS-1$

	private String ENABLE_ANTIALIAS = PresentationResourceManager
	.getI18NString("DiagramsPreferencePage.enableAntiAlias.label"); //$NON-NLS-1$	
	
	//preference page editor controls
	private BooleanFieldEditor showConnectorHandles = null;

	private BooleanFieldEditor showActionBars = null;
	
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

		showConnectorHandles = new BooleanFieldEditor(
			IPreferenceConstants.PREF_SHOW_CONNECTOR_HANDLES,
			SHOW_CONNECTOR_HANDLES_LABEL, composite);
		addField(showConnectorHandles);

		showActionBars = new BooleanFieldEditor(
			IPreferenceConstants.PREF_SHOW_ACTION_BARS, SHOW_ACTION_BARS_LABEL,
			composite);
		addField(showActionBars);
		
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
		
	}

	
	/**
	 * Initializes the default preference values for this preference store.
	 * 
	 * @param IPreferenceStore preferenceStore
	 */
	public static void initDefaults(IPreferenceStore preferenceStore) {

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_CONNECTOR_HANDLES, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_ACTION_BARS, true);
	
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
