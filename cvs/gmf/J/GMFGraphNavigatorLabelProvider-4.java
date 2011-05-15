/*
 *  Copyright (c) 2006, 2007 Borland Software Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      Borland Software Corporation - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.navigator;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.gmfgraph.ChildAccess;
import org.eclipse.gmf.gmfgraph.Identity;
import org.eclipse.gmf.gmfgraph.RealFigure;

import org.eclipse.gmf.graphdef.editor.edit.parts.CanvasEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ChildAccessEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.CompartmentAccessorEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.CompartmentEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.CompartmentNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ConnectionEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ConnectionNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.DiagramElementFigureEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.DiagramLabelAccessorEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Ellipse2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Ellipse3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.EllipseEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureDescriptorEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureDescriptorNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureGalleryEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureGalleryNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polyline2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polyline3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.PolylineEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Rectangle2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Rectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RectangleEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangle2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangleEditPart;

import org.eclipse.gmf.graphdef.editor.part.GMFGraphDiagramEditorPlugin;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry;

import org.eclipse.gmf.graphdef.editor.providers.GMFGraphElementTypes;
import org.eclipse.gmf.graphdef.editor.providers.GMFGraphParserProvider;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;

import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;

import org.eclipse.swt.graphics.Image;

import org.eclipse.ui.IMemento;

import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class GMFGraphNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		GMFGraphDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?InvalidElement", ImageDescriptor.getMissingImageDescriptor());
		GMFGraphDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor());
		GMFGraphDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor());
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof GMFGraphNavigatorItem && !isOwnView(((GMFGraphNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof GMFGraphNavigatorGroup) {
			GMFGraphNavigatorGroup group = (GMFGraphNavigatorGroup) element;
			return GMFGraphDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof GMFGraphNavigatorItem) {
			GMFGraphNavigatorItem navigatorItem = (GMFGraphNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		// Due to plugin.xml content will be called only for "own" views
		if (element instanceof IAdaptable) {
			View view = (View) ((IAdaptable) element).getAdapter(View.class);
			if (view != null && isOwnView(view)) {
				return getImage(view);
			}
		}
		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (GMFGraphVisualIDRegistry.getVisualID(view)) {
		case CompartmentEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Compartment", GMFGraphElementTypes.Compartment_2005);
		case NodeEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Node", GMFGraphElementTypes.Node_2006);
		case ConnectionEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Connection", GMFGraphElementTypes.Connection_2007);
		case FigureGalleryEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.eclipse.org/gmf/2006/GraphicalDefinition?FigureGallery", GMFGraphElementTypes.FigureGallery_2008);
		case FigureDescriptorEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?FigureDescriptor", GMFGraphElementTypes.FigureDescriptor_3009);
		case RectangleEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Rectangle", GMFGraphElementTypes.Rectangle_3010);
		case Rectangle2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Rectangle", GMFGraphElementTypes.Rectangle_3011);
		case EllipseEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Ellipse", GMFGraphElementTypes.Ellipse_3012);
		case RoundedRectangleEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?RoundedRectangle", GMFGraphElementTypes.RoundedRectangle_3013);
		case PolylineEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Polyline", GMFGraphElementTypes.Polyline_3014);
		case Ellipse2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Ellipse", GMFGraphElementTypes.Ellipse_3015);
		case RoundedRectangle2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?RoundedRectangle", GMFGraphElementTypes.RoundedRectangle_3016);
		case Polyline2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Polyline", GMFGraphElementTypes.Polyline_3017);
		case Rectangle3EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Rectangle", GMFGraphElementTypes.Rectangle_3018);
		case Ellipse3EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Ellipse", GMFGraphElementTypes.Ellipse_3019);
		case RoundedRectangle3EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?RoundedRectangle", GMFGraphElementTypes.RoundedRectangle_3020);
		case Polyline3EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Polyline", GMFGraphElementTypes.Polyline_3021);
		case CanvasEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Canvas", GMFGraphElementTypes.Canvas_1000);
		case ChildAccessEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.eclipse.org/gmf/2006/GraphicalDefinition?ChildAccess", GMFGraphElementTypes.ChildAccess_4002);
		case CompartmentAccessorEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.eclipse.org/gmf/2006/GraphicalDefinition?Compartment?accessor", GMFGraphElementTypes.CompartmentAccessor_4003);
		case DiagramLabelAccessorEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.eclipse.org/gmf/2006/GraphicalDefinition?DiagramLabel?accessor", GMFGraphElementTypes.DiagramLabelAccessor_4004);
		case DiagramElementFigureEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.eclipse.org/gmf/2006/GraphicalDefinition?DiagramElement?figure", GMFGraphElementTypes.DiagramElementFigure_4005);
		default:
			return getImage("Navigator?UnknownElement", null);
		}
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = GMFGraphDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && GMFGraphElementTypes.isKnownElementType(elementType)) {
			image = GMFGraphElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound");
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof GMFGraphNavigatorGroup) {
			GMFGraphNavigatorGroup group = (GMFGraphNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof GMFGraphNavigatorItem) {
			GMFGraphNavigatorItem navigatorItem = (GMFGraphNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		// Due to plugin.xml content will be called only for "own" views
		if (element instanceof IAdaptable) {
			View view = (View) ((IAdaptable) element).getAdapter(View.class);
			if (view != null && isOwnView(view)) {
				return getText(view);
			}
		}
		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (GMFGraphVisualIDRegistry.getVisualID(view)) {
		case CompartmentEditPart.VISUAL_ID:
			return getCompartment_2005Text(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006Text(view);
		case ConnectionEditPart.VISUAL_ID:
			return getConnection_2007Text(view);
		case FigureGalleryEditPart.VISUAL_ID:
			return getFigureGallery_2008Text(view);
		case FigureDescriptorEditPart.VISUAL_ID:
			return getFigureDescriptor_3009Text(view);
		case RectangleEditPart.VISUAL_ID:
			return getRectangle_3010Text(view);
		case Rectangle2EditPart.VISUAL_ID:
			return getRectangle_3011Text(view);
		case EllipseEditPart.VISUAL_ID:
			return getEllipse_3012Text(view);
		case RoundedRectangleEditPart.VISUAL_ID:
			return getRoundedRectangle_3013Text(view);
		case PolylineEditPart.VISUAL_ID:
			return getPolyline_3014Text(view);
		case Ellipse2EditPart.VISUAL_ID:
			return getEllipse_3015Text(view);
		case RoundedRectangle2EditPart.VISUAL_ID:
			return getRoundedRectangle_3016Text(view);
		case Polyline2EditPart.VISUAL_ID:
			return getPolyline_3017Text(view);
		case Rectangle3EditPart.VISUAL_ID:
			return getRectangle_3018Text(view);
		case Ellipse3EditPart.VISUAL_ID:
			return getEllipse_3019Text(view);
		case RoundedRectangle3EditPart.VISUAL_ID:
			return getRoundedRectangle_3020Text(view);
		case Polyline3EditPart.VISUAL_ID:
			return getPolyline_3021Text(view);
		case CanvasEditPart.VISUAL_ID:
			return getCanvas_1000Text(view);
		case ChildAccessEditPart.VISUAL_ID:
			return getChildAccess_4002Text(view);
		case CompartmentAccessorEditPart.VISUAL_ID:
			return getCompartmentAccessor_4003Text(view);
		case DiagramLabelAccessorEditPart.VISUAL_ID:
			return getDiagramLabelAccessor_4004Text(view);
		case DiagramElementFigureEditPart.VISUAL_ID:
			return getDiagramElementFigure_4005Text(view);
		default:
			return getUnknownElementText(view);
		}
	}

	/**
	 * @generated
	 */
	private String getCompartment_2005Text(View view) {
		IAdaptable hintAdapter = new GMFGraphParserProvider.HintAdapter(GMFGraphElementTypes.Compartment_2005, (view.getElement() != null ? view.getElement() : view), GMFGraphVisualIDRegistry
				.getType(CompartmentNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE.intValue());
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5005);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2006Text(View view) {
		IAdaptable hintAdapter = new GMFGraphParserProvider.HintAdapter(GMFGraphElementTypes.Node_2006, (view.getElement() != null ? view.getElement() : view), GMFGraphVisualIDRegistry
				.getType(NodeNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE.intValue());
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5006);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getConnection_2007Text(View view) {
		IAdaptable hintAdapter = new GMFGraphParserProvider.HintAdapter(GMFGraphElementTypes.Connection_2007, (view.getElement() != null ? view.getElement() : view), GMFGraphVisualIDRegistry
				.getType(ConnectionNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE.intValue());
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5007);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getFigureGallery_2008Text(View view) {
		IAdaptable hintAdapter = new GMFGraphParserProvider.HintAdapter(GMFGraphElementTypes.FigureGallery_2008, (view.getElement() != null ? view.getElement() : view), GMFGraphVisualIDRegistry
				.getType(FigureGalleryNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE.intValue());
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5009);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getFigureDescriptor_3009Text(View view) {
		IAdaptable hintAdapter = new GMFGraphParserProvider.HintAdapter(GMFGraphElementTypes.FigureDescriptor_3009, (view.getElement() != null ? view.getElement() : view), GMFGraphVisualIDRegistry
				.getType(FigureDescriptorNameEditPart.VISUAL_ID));
		IParser parser = ParserService.getInstance().getParser(hintAdapter);

		if (parser != null) {
			return parser.getPrintString(hintAdapter, ParserOptions.NONE.intValue());
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5008);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getRectangle_3010Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3010);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getRectangle_3011Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3011);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getEllipse_3012Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3012);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getRoundedRectangle_3013Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3013);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getPolyline_3014Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3014);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getEllipse_3015Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3015);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getRoundedRectangle_3016Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3016);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getPolyline_3017Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3017);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getRectangle_3018Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3018);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getEllipse_3019Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3019);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getRoundedRectangle_3020Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3020);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getPolyline_3021Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((RealFigure) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3021);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getCanvas_1000Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((Identity) domainModelElement).getName();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 1000);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getChildAccess_4002Text(View view) {
		EObject domainModelElement = view.getElement();
		if (domainModelElement != null) {
			return ((ChildAccess) domainModelElement).getAccessor();
		} else {
			GMFGraphDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 4002);
			return "";
		}
	}

	/**
	 * @generated
	 */
	private String getCompartmentAccessor_4003Text(View view) {
		return "";
	}

	/**
	 * @generated
	 */
	private String getDiagramLabelAccessor_4004Text(View view) {
		return "";
	}

	/**
	 * @generated
	 */
	private String getDiagramElementFigure_4005Text(View view) {
		return "";
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">";
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">";
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return CanvasEditPart.MODEL_ID.equals(GMFGraphVisualIDRegistry.getModelID(view));
	}

}