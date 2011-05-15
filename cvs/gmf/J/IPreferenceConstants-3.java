/******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.diagram.ui.preferences;

/**
 * Defines preference constants for the GMF diagram layer.
 * 
 * @author schafe
 * 
 */
public interface IPreferenceConstants {

    /**
     * the connection line style; shows up in ConnectionsPreferencePage 
     */
    public static final String PREF_LINE_STYLE = "Connectors.lineStyle"; //$NON-NLS-1$

    /**
     * the font; shows up in AppearancePreferencePage 
     */
    public static final String PREF_DEFAULT_FONT = "Appearance.defaultFont"; //$NON-NLS-1$
    /**
     * the font color; shows up in AppearancePreferencePage 
     */
	public static final String PREF_FONT_COLOR = "Appearance.fontColor"; //$NON-NLS-1$
	/**
     * the the fill color; shows up in AppearancePreferencePage 
     */
    public static final String PREF_FILL_COLOR = "Appearance.fillColor"; //$NON-NLS-1$
    /**
     * the line color; shows up in AppearancePreferencePage 
     */
    public static final String PREF_LINE_COLOR = "Appearance.lineColor"; //$NON-NLS-1$
    /**
     * the note fill color; shows up in AppearancePreferencePage 
     */
	public static final String PREF_NOTE_FILL_COLOR = "Appearance.noteFillColor"; //$NON-NLS-1$
	/**
     * the note line color; shows up in AppearancePreferencePage 
     */
	public static final String PREF_NOTE_LINE_COLOR = "Appearance.noteLineColor"; //$NON-NLS-1$
	/**
	 * the comment fill color; shows up in AppearancePreferencePage
	 * 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
	 */
	public static final String PREF_COMMENT_FILL_COLOR = "Appearance.commentFillColor";//$NON-NLS-1$
	/**
     * the comment line color; shows up in AppearancePreferencePage 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String PREF_COMMENT_LINE_COLOR = "Appearance.commentLineColor";//$NON-NLS-1$
	/**
     * the constraint fill color; shows up in AppearancePreferencePage 
 	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String PREF_CONSTRAINT_FILL_COLOR = "Appearance.constraintFillColor";//$NON-NLS-1$
	/**
     * the constraint line color; shows up in AppearancePreferencePage 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
    */
	public static final String PREF_CONSTRAINT_LINE_COLOR = "Appearance.constraintLineColor";//$NON-NLS-1$
	
    /**
     * show drop shadow preference; Global Preference 
 	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
    public static final String PREF_SHOW_DROPSHADOW = "Global.showDropShadow"; //$NON-NLS-1$
    
    /**
     * show connection handles preference; Global Preference 
     */
	public static final String PREF_SHOW_CONNECTION_HANDLES = "Global.showConnectionHandles"; //$NON-NLS-1$
	
	/**
     * show popup bars preference;; Global Preference 
     */
	public static final String PREF_SHOW_POPUP_BARS = "Global.showPopupBars"; //$NON-NLS-1$

	/**
     * the bold name preference;; Global Preference 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String PREF_BOLD_NAME = "Global.boldName"; //$NON-NLS-1$
	/**
     * show gradient preference;; Global Preference 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String PREF_SHOW_GRADIENT = "Global.showGradient"; //$NON-NLS-1$
	/**
     * prompt when user choose delete from model; Global Preference 
     */
	public static final String PREF_PROMPT_ON_DEL_FROM_MODEL = "Global.promptOnDelFromModel"; //$NON-NLS-1$
	/**
     * prompt when user choose delete from diagram ; Global Preference
     */
	public static final String PREF_PROMPT_ON_DEL_FROM_DIAGRAM = "Global.promptOnDelFromDiagram"; //$NON-NLS-1$	
	/**
     * enable Layout animation ; Global Preference
     */
	public static final String PREF_ENABLE_ANIMATED_LAYOUT = "Global.enableAnimatedLayout"; //$NON-NLS-1$
	/**
     * enable zoom animation; Global Preference 
     */
	public static final String PREF_ENABLE_ANIMATED_ZOOM = "Global.enableAnimatedZoom"; //$NON-NLS-1$	

	/**
     * enable anti-aliasing; Global Preference 
     */
	public static final String PREF_ENABLE_ANTIALIAS = "Global.enableAntiAlias"; //$NON-NLS-1$	

	/**
     * show grid preference; Grid/Rulers Preference Page 
     */
	public static final String PREF_SHOW_GRID    = "GridRuler.showGrid";    //$NON-NLS-1$
	/**
     * Show Rulers Preference; Grid/Rulers Preference Page 
     */
	public static final String PREF_SHOW_RULERS  = "GridRuler.showRulers";  //$NON-NLS-1$
	/**
     * Snap to grid preference; Grid/Rulers Preference Page 
     */
	public static final String PREF_SNAP_TO_GRID = "GridRuler.snapToGrid";  //$NON-NLS-1$
	/**
     * ruler units pereference; Grid/Rulers Preference Page 
     */
	public static final String PREF_RULER_UNITS  = "GridRuler.rulerUnits";  //$NON-NLS-1$
	/**
     * grid spacing preference; Grid/Rulers Preference Page 
     */
	public static final String PREF_GRID_SPACING = "GridRuler.gridSpacing"; //$NON-NLS-1$

	/**
     * show compartment title preference; Shapes 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String PREF_SHOW_COMPARTMENT_TITLE = "Shapes.showCompartmentTitle"; //$NON-NLS-1$
			
	/**
     * create a default diagram inside each new package flag; default diagram creation and open preferences 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String CREATE_DEFAULT_DIAGRAM_ON_PACKAGE_CREATION = "DiagramCreationPreference.create_default_diagram_on_package_creation"; //$NON-NLS-1$
	/**
     * the default diagram type for packages; default diagram creation and open preferences 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String DEFAULT_DIAGRAM_TYPE_FOR_PACKAGE = "DiagramCreationPreference.default_diagram_type_for_package"; //$NON-NLS-1$
	/**
     * open the default diagram when the model opens; default diagram creation and open preferences 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
     */
	public static final String OPEN_DEFAULT_DIAGRAM_ON_MODEL_OPEN = "DiagramCreationPreference.open_default_diagram_on_model_open"; //$NON-NLS-1$
	/**
     * open the default diagram on name space open; default diagram creation and open preferences 
	 * @deprecated This preference is not supported in the diagram layer. To
	 *             be deleted after Dec 21, 2005.
      */
	public static final String OPEN_DEFAULT_DIAGRAM_ON_NAMESPACE_OPEN = "DiagramCreationPreference.open_default_diagram_on_namespace_open"; //$NON-NLS-1$

}
