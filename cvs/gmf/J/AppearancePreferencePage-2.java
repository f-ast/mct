/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.preferences;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.gmf.runtime.common.ui.preferences.FontFieldEditor;
import org.eclipse.gmf.runtime.diagram.ui.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.figures.DiagramColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.l10n.PresentationResourceManager;

/**
 * Presentation Appearance General preference page.
 * 
 * @author schafe
 * @canBeSeenBy org.eclipse.gmf.runtime.diagram.ui.*
 */
public class AppearancePreferencePage
    extends AbstractPreferencePage {

    //localized labels
	private String COLORANDFONT_GROUPBOX_LABEL = PresentationResourceManager.getI18NString("GeneralPreferencePage.colorAndFontGroupBox.label");//$NON-NLS-1$
	private String DEFAULT_FONT_LABEL = PresentationResourceManager.getI18NString("GeneralPreferencePage.defaultFont.label"); //$NON-NLS-1$
    private String FONT_COLOR_LABEL = PresentationResourceManager.getI18NString("GeneralPreferencePage.fontColor.label"); //$NON-NLS-1$
    private String FILL_COLOR_LABEL = PresentationResourceManager.getI18NString("GeneralPreferencePage.fillColor.label"); //$NON-NLS-1$
    private String LINE_COLOR_LABEL = PresentationResourceManager.getI18NString("GeneralPreferencePage.lineColor.label"); //$NON-NLS-1$
    private String NOTE_FILL_COLOR_LABEL = PresentationResourceManager.getI18NString("GeneralPreferencePage.noteFillColor.label"); //$NON-NLS-1$
    private String NOTE_LINE_COLOR_LABEL = PresentationResourceManager.getI18NString("GeneralPreferencePage.noteLineColor.label"); //$NON-NLS-1$
				
    //preference page editor controls 
    private FontFieldEditor defaultFontEditor = null;
    private ColorFieldEditor fontColorEditor = null;
    private ColorFieldEditor fillColorEditor = null;
    private ColorFieldEditor lineColorEditor = null;
    private ColorFieldEditor noteFillColorEditor = null;
    private ColorFieldEditor noteLineColorEditor = null;

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#addFields(org.eclipse.swt.widgets.Composite)
     */
    protected void addFields(Composite parent) {	  	
    	Composite main = createPageLayout(parent);
		createFontAndColorGroup(main); 
    }

	/**
	 * Creates a new composite for the entire page and sets up the layout.
	 * @param parent the parent <code>Composite</code> that the field editors will be added
	 * to
	 * @return the new <code>Composite</code>
	 */
	protected Composite createPageLayout(Composite parent) {
		Composite main = new Composite(parent, SWT.NULL);
		main.setLayout(new GridLayout());
		main.setLayoutData(
			new GridData(
				GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		return main;
	}
        
	/**
	 * Create the font and colour group for the
	 * preference page
	 * @param parent
	 * @return composite fontAndColourGroup
	 */
	protected Composite createFontAndColorGroup(Composite parent) {
    	
    	Group group = new Group(parent, SWT.NONE);
    	group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    	group.setLayout(new GridLayout(3, false));
    	Composite composite = new Composite(group, SWT.NONE);
    	GridLayout gridLayout = new GridLayout(3, false);
    	composite.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 3;
		composite.setLayoutData(gridData);
		group.setText(COLORANDFONT_GROUPBOX_LABEL);
    	
 		addFontAndColorFields(composite);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 8;
		composite.setLayout(layout);               
    	
		return group;
	}

/**
 * Adds the font and color fields to the <code>Composite</code> given.
 * @param composite
 */
protected void addFontAndColorFields(Composite composite) {
	defaultFontEditor =
	    new FontFieldEditor(
	        IPreferenceConstants.PREF_DEFAULT_FONT,
	        DEFAULT_FONT_LABEL,
			composite);
	addField(defaultFontEditor);
	
	fontColorEditor =
	    new ColorFieldEditor(
	        IPreferenceConstants.PREF_FONT_COLOR,
	        FONT_COLOR_LABEL,
			composite);
	addField(fontColorEditor);		

	fillColorEditor =
	    new ColorFieldEditor(
	        IPreferenceConstants.PREF_FILL_COLOR,
	        FILL_COLOR_LABEL,
			composite);
	addField(fillColorEditor);

	lineColorEditor =
	    new ColorFieldEditor(
	        IPreferenceConstants.PREF_LINE_COLOR,
	        LINE_COLOR_LABEL,
			composite);
	addField(lineColorEditor);

	noteFillColorEditor =
	    new ColorFieldEditor(
	        IPreferenceConstants.PREF_NOTE_FILL_COLOR,
	        NOTE_FILL_COLOR_LABEL,
			composite);
	addField(noteFillColorEditor);

	noteLineColorEditor =
	    new ColorFieldEditor(
	        IPreferenceConstants.PREF_NOTE_LINE_COLOR,
	        NOTE_LINE_COLOR_LABEL,
			composite);
	addField(noteLineColorEditor);
}

	/**
     * Initializes the default preference values 
     * for this preference store.
     * 
     * @param store
     */
    public static void initDefaults(IPreferenceStore store) {

    	setDefaultFontPreference(store);

        Color fontColor = ColorConstants.black;
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_FONT_COLOR,
            fontColor.getRGB());

        Color fillColor = DiagramColorConstants.white;
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_FILL_COLOR,
            fillColor.getRGB());

        Color lineColor = DiagramColorConstants.diagramGray;
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_LINE_COLOR,
            lineColor.getRGB());

        Color noteFillColor = DiagramColorConstants.diagramLightYellow;
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_NOTE_FILL_COLOR,
			noteFillColor.getRGB());

        Color noteLineColor = DiagramColorConstants.diagramDarkYellow;
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_NOTE_LINE_COLOR,
			noteLineColor.getRGB());
    }
    	
	/**
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#initHelp()
	 */
	protected void initHelp() {
		//do nothing, help not ready yet
		//setPageHelpContextId(IHelpContextIds.VZ_U_APPEAR_PAGE_PREF);
	}
	
	/**	
	 * The field editor preference page implementation of a <code>PreferencePage</code>
	 * method loads all the field editors with their default values.
	 * 
	 * Override so that the font field editor default is set.
	 * Note: loadDefault() does not work with FontFieldEditor
	 */
	protected void performDefaults() {		
		setDefaultFontPreference(getPreferenceStore());
        super.performDefaults();
	}
	
	/**
	 * Set the default font for this preference store.
	 * @param store IPreferenceStore
	 */
	static private void setDefaultFontPreference(IPreferenceStore store){
		FontData fontDataArray[] =
            JFaceResources.getDefaultFont().getFontData();
        FontData fontData = fontDataArray[0];
        fontData.setHeight(9);
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_DEFAULT_FONT,
            fontData);		
	}

	/**
	 * Gets the field editor used for the default font.
	 * @return the default font field editor
	 */
	protected FontFieldEditor getDefaultFontEditor() {
		return defaultFontEditor;
	}
	
	/**
	 * Gets the field editor used for the fill color.
	 * @return the fill color field editor
	 */
	protected ColorFieldEditor getFillColorEditor() {
		return fillColorEditor;
	}

	/**
	 * Gets the field editor used for the line color.
	 * @return the line color field editor
	 */
	protected ColorFieldEditor getLineColorEditor() {
		return lineColorEditor;
	}	
		
}
