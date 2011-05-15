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
package org.eclipse.gmf.graphdef.editor.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.gmfgraph.Canvas;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.graphdef.editor.edit.parts.CanvasEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ChildAccessEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.CompartmentEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.CompartmentNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.CompartmentVisualFacetsEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ConnectionEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ConnectionNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ConnectionVisualFacetsEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Ellipse2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Ellipse3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.EllipseEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureDescriptorEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureDescriptorNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureGalleryEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureGalleryFiguresEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureGalleryNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeVisualFacetsEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polyline2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polyline3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.PolylineEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Rectangle2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Rectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RectangleEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangle2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangleEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class GMFGraphVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = GMFGraphDiagramEditorPlugin.getInstance().getBundle().getSymbolicName() + "/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (CanvasEditPart.MODEL_ID.equals(view.getType())) {
				return CanvasEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				GMFGraphDiagramEditorPlugin.getInstance().logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (GMFGraphPackage.eINSTANCE.getCanvas().isSuperTypeOf(domainElement.eClass()) && isDiagram((Canvas) domainElement)) {
			return CanvasEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null || !CanvasEditPart.MODEL_ID.equals(org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry.getModelID(containerView))) {
			return -1;
		}
		switch (org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry.getVisualID(containerView)) {
		case FigureDescriptorEditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return Ellipse2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return Polyline2EditPart.VISUAL_ID;
			}
			break;
		case RectangleEditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case Rectangle2EditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case EllipseEditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case RoundedRectangleEditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case Ellipse2EditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case RoundedRectangle2EditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case Rectangle3EditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case Ellipse3EditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case RoundedRectangle3EditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle2EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return EllipseEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangleEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return PolylineEditPart.VISUAL_ID;
			}
			break;
		case FigureGalleryFiguresEditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getFigureDescriptor().isSuperTypeOf(domainElement.eClass())) {
				return FigureDescriptorEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRectangle().isSuperTypeOf(domainElement.eClass())) {
				return Rectangle3EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
				return Ellipse3EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getRoundedRectangle().isSuperTypeOf(domainElement.eClass())) {
				return RoundedRectangle3EditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getPolyline().isSuperTypeOf(domainElement.eClass())) {
				return Polyline3EditPart.VISUAL_ID;
			}
			break;
		case CanvasEditPart.VISUAL_ID:
			if (GMFGraphPackage.eINSTANCE.getCompartment().isSuperTypeOf(domainElement.eClass())) {
				return CompartmentEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())) {
				return NodeEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getConnection().isSuperTypeOf(domainElement.eClass())) {
				return ConnectionEditPart.VISUAL_ID;
			}
			if (GMFGraphPackage.eINSTANCE.getFigureGallery().isSuperTypeOf(domainElement.eClass())) {
				return FigureGalleryEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry.getModelID(containerView);
		if (!CanvasEditPart.MODEL_ID.equals(containerModelID) && !"GMFGraph".equals(containerModelID)) { //$NON-NLS-1$
			return false;
		}
		int containerVisualID;
		if (CanvasEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = CanvasEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case CompartmentEditPart.VISUAL_ID:
			if (CompartmentNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CompartmentVisualFacetsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeEditPart.VISUAL_ID:
			if (NodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NodeVisualFacetsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ConnectionEditPart.VISUAL_ID:
			if (ConnectionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ConnectionVisualFacetsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FigureGalleryEditPart.VISUAL_ID:
			if (FigureGalleryNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FigureGalleryFiguresEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FigureDescriptorEditPart.VISUAL_ID:
			if (FigureDescriptorNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Ellipse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Polyline2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RectangleEditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Rectangle2EditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EllipseEditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoundedRectangleEditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Ellipse2EditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoundedRectangle2EditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Rectangle3EditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Ellipse3EditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoundedRectangle3EditPart.VISUAL_ID:
			if (Rectangle2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PolylineEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FigureGalleryFiguresEditPart.VISUAL_ID:
			if (FigureDescriptorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Rectangle3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Ellipse3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoundedRectangle3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Polyline3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CanvasEditPart.VISUAL_ID:
			if (CompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ConnectionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FigureGalleryEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (GMFGraphPackage.eINSTANCE.getChildAccess().isSuperTypeOf(domainElement.eClass())) {
			return ChildAccessEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Canvas element) {
		return true;
	}

}