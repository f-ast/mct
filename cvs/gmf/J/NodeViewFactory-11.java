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
package org.eclipse.gmf.graphdef.editor.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;

import org.eclipse.gmf.graphdef.editor.edit.parts.CanvasEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeNameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeVisualFacetsEditPart;

import org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;

import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class NodeViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated 
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createShapeStyle());
		return styles;
	}

	/**
	 * @generated
	 */
	protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
		if (semanticHint == null) {
			semanticHint = GMFGraphVisualIDRegistry.getType(NodeEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
		if (!CanvasEditPart.MODEL_ID.equals(GMFGraphVisualIDRegistry.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put("modelID", CanvasEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService().createNode(eObjectAdapter, view, GMFGraphVisualIDRegistry.getType(NodeNameEditPart.VISUAL_ID), ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(eObjectAdapter, view, GMFGraphVisualIDRegistry.getType(NodeVisualFacetsEditPart.VISUAL_ID), ViewUtil.APPEND, true, getPreferencesHint());
	}
}
