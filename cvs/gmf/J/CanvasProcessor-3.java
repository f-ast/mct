/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.graphdef.codegen;

import java.util.Iterator;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.gmfgraph.Canvas;
import org.eclipse.gmf.gmfgraph.Compartment;
import org.eclipse.gmf.gmfgraph.Connection;
import org.eclipse.gmf.gmfgraph.CustomFigure;
import org.eclipse.gmf.gmfgraph.DiagramLabel;
import org.eclipse.gmf.gmfgraph.Figure;
import org.eclipse.gmf.gmfgraph.FigureAccessor;
import org.eclipse.gmf.gmfgraph.FigureGallery;
import org.eclipse.gmf.gmfgraph.GMFGraphFactory;
import org.eclipse.gmf.gmfgraph.Node;
import org.eclipse.gmf.graphdef.codegen.StandaloneGenerator.Config;
import org.eclipse.gmf.graphdef.codegen.StandaloneGenerator.Processor;
import org.eclipse.gmf.graphdef.codegen.StandaloneGenerator.ProcessorCallback;

public class CanvasProcessor extends Processor {
	private final DiagramElementsCopier myElementCopier;
	private ProcessorCallback myCallback;
	final Canvas myInput;
	private Canvas myOutcome;
	private FigureGallery myOutcomeGallery; 

	public CanvasProcessor(Canvas input) {
		assert input != null;
		myInput = input;
		myElementCopier = new DiagramElementsCopier();
	}

	public Canvas getOutcome() {
		return myOutcome;
	}

	public void go(ProcessorCallback callback, Config config) throws InterruptedException {
		myCallback = callback;
		myOutcomeGallery = GMFGraphFactory.eINSTANCE.createFigureGallery();
		myOutcomeGallery.setName(myInput.getFigures().size() == 1 ? ((FigureGallery) myInput.getFigures().get(0)).getName() : "GeneratedGallery");
		myOutcomeGallery.setImplementationBundle(config.getPluginID());
		handleNodes();
		handleLinks();
		handleCompartments();
		handleLabels();
		// can't use 
		// = (Canvas) diagramElementCopier.copy(myInput);
		// here because Copier.copy doesn't respect already copied elements
		myOutcome = GMFGraphFactory.eINSTANCE.createCanvas();
		myOutcome.setName(myInput.getName());
		myOutcome.getFigures().add(myOutcomeGallery);

		myOutcome.getCompartments().addAll(myElementCopier.copyAll(myInput.getCompartments()));
		myOutcome.getLabels().addAll(myElementCopier.copyAll(myInput.getLabels()));
		myOutcome.getNodes().addAll(myElementCopier.copyAll(myInput.getNodes()));
		myOutcome.getConnections().addAll(myElementCopier.copyAll(myInput.getConnections()));

		if (!myOutcome.eContents().isEmpty()) {
			myElementCopier.copyReferences();
		}
		myCallback = null;
	}

	private void handleNodes() throws InterruptedException {
		for (Iterator it = myInput.getNodes().iterator(); it.hasNext();) {
			Node next = (Node) it.next();
			handleFigure(next.getNodeFigure());
		}
	}

	private void handleLinks() throws InterruptedException {
		for (Iterator it = myInput.getConnections().iterator(); it.hasNext();) {
			Connection next = (Connection) it.next();
			handleFigure(next.getConnectionFigure());
		}
	}

	private void handleCompartments() throws InterruptedException {
		for (Iterator it = myInput.getCompartments().iterator(); it.hasNext();) {
			Compartment next = (Compartment) it.next();
			if (next.getFigure() instanceof Figure) {
				handleFigure((Figure) next.getFigure());
			} else {
				throw new IllegalStateException("Don't support accessors for compartments yet");
			}
		}
	}

	private void handleLabels() throws InterruptedException {
		for (Iterator it = myInput.getLabels().iterator(); it.hasNext();) {
			DiagramLabel next = (DiagramLabel) it.next();
			if (next.getFigure() instanceof FigureAccessor) {
				assert myElementCopier.containsKey(next.getFigure()) : "Should be copied as part of previously referenced CustomFigure";
			} else {
				assert next.getFigure() instanceof Figure;
				Figure f = (Figure) next.getFigure(); 
				if (isInsideProcessedFigure(f)) {
					// obviously, fact we got here means f is !getReferencingElements().isEmpty()
					// feedback.findAccessorFor(f)
					FigureAccessor accessor = GMFGraphFactory.eINSTANCE.createFigureAccessor();
					accessor.setAccessor("get" + CodeGenUtil.capName(f.getName()));
					myElementCopier.put(f, accessor);
				} else {
					handleFigure(f);
				}
				
			}
		}
	}

	private boolean isInsideProcessedFigure(Figure f) {
		return EcoreUtil.isAncestor(myElementCopier.keySet(), f);
	}

	private void handleFigure(Figure figure) throws InterruptedException {
		if (figure instanceof CustomFigure /* && isPlainBareFigureHandle()*/) {
			myElementCopier.copy(figure);
		} else {
			String fqn = myCallback.visitFigure(figure);
			myElementCopier.registerSubstitution(figure, createCustomFigure(figure, fqn));
		}
	}

	private CustomFigure createCustomFigure(Figure original, String fqn) {
		CustomFigure cf = DiagramElementsCopier.createCustomFigure(original);
		cf.setName(original.getName());
		cf.setQualifiedClassName(fqn);
		myOutcomeGallery.getFigures().add(cf);
		return cf;
	}
}