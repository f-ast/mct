/**
 * Copyright (c) 2006 Borland Software Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.part;

import java.util.List;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import java.util.ArrayList;

import org.eclipse.gef.palette.PaletteDrawer;

import org.eclipse.gmf.graphdef.editor.providers.GMFGraphElementTypes;

import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class GMFGraphPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createDiagramElements1Group());
		paletteRoot.add(createFigures2Group());
	}

	/**
	 * @generated
	 */
	private PaletteContainer createDiagramElements1Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Diagram Elements");
		paletteContainer.setDescription("Can visualize domain model elements");
		paletteContainer.add(createNode1CreationTool());
		paletteContainer.add(createChildNode2CreationTool());
		paletteContainer.add(createCompartment3CreationTool());
		paletteContainer.add(createConnection4CreationTool());
		paletteContainer.add(createFigureLink5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private PaletteContainer createFigures2Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Figures");
		paletteContainer.setDescription("Can be references by Diagram Elements");
		paletteContainer.add(createFigureGallery1CreationTool());
		paletteContainer.add(createRectangle2CreationTool());
		paletteContainer.add(createEllipse3CreationTool());
		paletteContainer.add(createRoundedRectangle4CreationTool());
		paletteContainer.add(createPolyline5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNode1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.Node_2002);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.Node_2002);
		ToolEntry result = new NodeToolEntry("Node", "Create Diagram Element representing Top-level diagram Node", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createChildNode2CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = null;

		largeImage = smallImage;

		ToolEntry result = new ToolEntry("ChildNode", "Create Diagram Element representing Child diagram Node", smallImage, largeImage) {
		};

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCompartment3CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.Compartment_2001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.Compartment_2001);
		ToolEntry result = new NodeToolEntry("Compartment", "Create Diagram Element representing Children Compartment", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConnection4CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.Connection_2003);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.Connection_2003);
		ToolEntry result = new NodeToolEntry("Connection", "Create Diagram Element representing Connection", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFigureLink5CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.DiagramElementFigure_4001);

		largeImage = smallImage;

		final List relationshipTypes = new ArrayList();
		relationshipTypes.add(GMFGraphElementTypes.DiagramElementFigure_4001);
		ToolEntry result = new LinkToolEntry("Figure Link", "Link from the Diagram Element to the figure", smallImage, largeImage, relationshipTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFigureGallery1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.FigureGallery_2004);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.FigureGallery_2004);
		ToolEntry result = new NodeToolEntry("FigureGallery", "Create FigureGallery - physical container for figures", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRectangle2CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.Rectangle_3001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.Rectangle_3001);
		elementTypes.add(GMFGraphElementTypes.Rectangle_3002);
		ToolEntry result = new NodeToolEntry("Rectangle", "Create Rectangle", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEllipse3CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.Ellipse_3003);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.Ellipse_3003);
		elementTypes.add(GMFGraphElementTypes.Ellipse_3006);
		ToolEntry result = new NodeToolEntry("Ellipse", "Create Ellipse", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoundedRectangle4CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.RoundedRectangle_3004);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.RoundedRectangle_3004);
		elementTypes.add(GMFGraphElementTypes.RoundedRectangle_3007);
		ToolEntry result = new NodeToolEntry("Rounded Rectangle", "Create Rounded Rectangle", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPolyline5CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFGraphElementTypes.getImageDescriptor(GMFGraphElementTypes.Polyline_3005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFGraphElementTypes.Polyline_3005);
		elementTypes.add(GMFGraphElementTypes.Polyline_3008);
		ToolEntry result = new NodeToolEntry("Polyline", "Create new PolylineConnection", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description, ImageDescriptor smallIcon, ImageDescriptor largeIcon, List elementTypes) {
			super(title, description, smallIcon, largeIcon);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description, ImageDescriptor smallIcon, ImageDescriptor largeIcon, List relationshipTypes) {
			super(title, description, smallIcon, largeIcon);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}