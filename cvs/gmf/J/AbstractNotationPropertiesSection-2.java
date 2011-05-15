/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.properties.sections;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.wst.common.ui.properties.internal.provisional.ITabbedPropertyConstants;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.descriptors.NotationPropertyDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.ResourceManager;
import org.eclipse.gmf.runtime.emf.core.util.MetaModelUtil;
import com.ibm.xtools.notation.NotationPackage;
import com.ibm.xtools.notation.View;

/**
 * @author nbalaba
 * 
 */
public abstract class AbstractNotationPropertiesSection
	extends AbstractModelerPropertySection {

	protected static int vertical_offset = ITabbedPropertyConstants.VSPACE;

	protected static int button_margin = IDialogConstants.BUTTON_MARGIN / 2;

	protected int standardLabelWidth = -1;

	protected Composite composite;

	protected PaintListener painter;

	/**
	 * 
	 * Get a label provider to do the translation Most entries on this page are
	 * handled using the string table to take care of translation. In this case
	 * the string table does not have what we need so we get a label provider
	 * from a property descriptor. The property descriptor itself comes from a
	 * property source. This is the way the advanced tab operates.
	 * 
	 * @return
	 */
	protected ILabelProvider getLabelProvider(Object propertyId) {
		IPropertySource propertySource = propertiesProvider
			.getPropertySource(getSingleInput());
		ILabelProvider labelProvider = null;
		NotationPropertyDescriptor pdNotation = null;
		IPropertyDescriptor[] descriptors = null;

		if (null != propertySource) {
			descriptors = propertySource.getPropertyDescriptors();
			for (int i = 0; i < descriptors.length; i++) {
				if (descriptors[i].getId() == NotationPackage.eINSTANCE
					.getView_Styles()) {
					pdNotation = (NotationPropertyDescriptor) descriptors[i];
					break;
				}
			}
		}

		if (null != pdNotation) {
			propertySource = (IPropertySource) pdNotation.getPropertyValue();

			descriptors = propertySource.getPropertyDescriptors();
			for (int j = 0; j < descriptors.length; j++) {
				if (descriptors[j].getId() == MetaModelUtil
					.getElement(propertyId.toString())) {
					labelProvider = descriptors[j].getLabelProvider();
					break;
				}
			}
		}
		return labelProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		initializeControls(parent);
	}

	
	/**
	 * Provides a level of indirection for subclasses which want 'jump' over
	 * some control creation/initilialization steps and/or chnage thier order
	 * 
	 * @param parent - parent composite
	 */
	protected void initializeControls(Composite parent) {
		createPaintedSectionComposite(parent);		
	}

	/**
	 * Create a composite for the section
	 * 
	 * @param parent - parent widget to host the section's composite
	 */
	protected Composite createPaintedSectionComposite(Composite parent) {
		composite = getWidgetFactory().createFlatFormComposite(parent);

		FormLayout layout = (FormLayout) composite.getLayout();
		layout.spacing = 3;

		painter = createPainter();
		composite.addPaintListener(painter);
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#dispose()
	 */
	public void dispose() {
		if (composite != null && ! composite.isDisposed() && painter != null)
			composite.removePaintListener(painter);
		super.dispose();

	}

	/**
	 * @return - create and return a painter for the section composite which
	 *         will do partial gradient color fill fopr the section widget
	 */
	protected PaintListener createPainter() {
		return new PaintListener() {

			public void paintControl(PaintEvent e) {
				Rectangle bounds = composite.getClientArea();
				GC gc = e.gc;

				gc.setForeground(gc.getBackground());
				gc.setBackground(getWidgetFactory().getColors().getColor(
					FormColors.TB_BG));

				gc.fillGradientRectangle(4 + bounds.width / 2, 0,
					bounds.width / 2 - 9, bounds.height, false);

				gc.setForeground(getWidgetFactory().getColors().getColor(
					FormColors.TB_BORDER));
				gc.drawLine(bounds.width - 5, 0, bounds.width - 5,
					bounds.height);
			}

		};

	}

	/**
	 * Determines if the page is displaying properties for this element The
	 * element being passed to us is the style ie UMLConnectorStyle or
	 * UMLShapeStype. The element which we are holding will be an association,
	 * class etc. Comparing these 2 eobjects will always result in false being
	 * returned and no refresh. We are unable to analytically, consistantly
	 * trave from the eobject representing the style to the eobject representing
	 * the shape so we are stuck returning true in all cases.
	 * 
	 * @param notification
	 *            The notification
	 * @param element
	 *            The element to be tested
	 * @return 'true' if the page is displaying properties for this element
	 * 
	 */
	protected boolean isCurrentSelection(Notification notification,
			EObject element) {

		return true;
	}
	
	/**
	 * Returns currently selected view object
	 * 
	 * @return Returns the input.
	 */
	public IGraphicalEditPart getSingleInput() {
		return (IGraphicalEditPart) getPrimarySelection();
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#unwrap(java.lang.Object)
	 */
	protected EObject unwrap(Object object) {
		EObject o = super.unwrap(object);
		if (o != null && object instanceof IGraphicalEditPart)
			return o;
		return null;
	}

	/**
	 * Adapt the object to an EObject - if possible
	 * 
	 * @param object
	 *            object from a diagram or ME
	 * @return EObject
	 */
	protected EObject adapt(Object object) {
		if (object instanceof IAdaptable) {
			if (object instanceof IGraphicalEditPart)// digram case
				return (EObject) ((IAdaptable) object).getAdapter(View.class);
			// ME case
			return (EObject) ((IAdaptable) object).getAdapter(EObject.class);
		}

		return null;
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#getStandardLabelWidth(org.eclipse.swt.widgets.Composite,
	 *      java.lang.String[])
	 */
	protected int getStandardLabelWidth(Composite parent) {
		if (standardLabelWidth == -1) {
			standardLabelWidth = getStandardLabelWidth(parent,
				getAllNameLabels());
		}
		return standardLabelWidth;
	}

	/**
	 * @return - an array of property names (I18N-ed) to compute label column
	 *         width
	 */
	protected String[] getAllNameLabels() {
		return new String[] {};
	}
}