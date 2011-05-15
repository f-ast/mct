/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004 - 2005.  All Rights Reserved.             |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.render.clipboard;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.w3c.dom.Element;

import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.Images;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.DiagramUIRenderDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.DiagramUIRenderPlugin;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.image.ImageConverter;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.RenderedImageDescriptor;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.svg.export.GraphicsSVG;

/**
 * Supports generation of an SVG DOM for a diagram or a subset of
 * editparts on a diagram.
 * 
 * @author jschofie / sshaw
 */
public class DiagramSVGGenerator
	extends DiagramGenerator {

	private RenderedImage svgImage = null;

	private Element svgRoot = null;

	private Rectangle viewBox = null;

	/**
	 * Creates a new instance.
	 * 
	 * @param diagramEditPart
	 *            the diagram editpart
	 */
	public DiagramSVGGenerator(DiagramEditPart diagramEditPart) {
		super(diagramEditPart);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator#setUpGraphics(int, int)
	 */
	protected Graphics setUpGraphics(int width, int height) {
		viewBox = new Rectangle(0, 0, width, height);
		return GraphicsSVG.getInstance(viewBox);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator#getImageDescriptor(org.eclipse.draw2d.Graphics)
	 */
	protected ImageDescriptor getImageDescriptor(Graphics g) {
		try {
			GraphicsSVG svgG = (GraphicsSVG) g;
			// Get the root element (the svg element)
			svgRoot = svgG.getRoot();

			ByteArrayOutputStream os = new ByteArrayOutputStream(5000); // 5K
																		// buffer
			stream(os);
			os.close();

			setSVGImage(RenderedImageFactory.getInstance(os.toByteArray()));

			return RenderedImageDescriptor
				.createFromRenderedImage(getSVGImage());
		} catch (IOException ex) {
			Log.error(DiagramUIRenderPlugin.getInstance(), IStatus.ERROR, ex
				.getMessage(), ex);
		}

		return null;
	}

	/**
	 * Writes the SVG Model out to a file.
	 * 
	 * @param outputStream
	 *            output stream to store the SVG Model
	 */
	public void stream(OutputStream outputStream) {
		try {

			// Define the view box
			svgRoot.setAttributeNS(null,
				"viewBox", String.valueOf(viewBox.x) + " " + //$NON-NLS-1$ //$NON-NLS-2$
					String.valueOf(viewBox.y) + " " + //$NON-NLS-1$
					String.valueOf(viewBox.width) + " " + //$NON-NLS-1$
					String.valueOf(viewBox.height));

			// Write the document to the stream
			Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
			transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$

			DOMSource source = new DOMSource(svgRoot);
			StreamResult result = new StreamResult(outputStream);
			transformer.transform(source, result);
		} catch (Exception ex) {
			Log.error(DiagramUIRenderPlugin.getInstance(), IStatus.ERROR, ex
				.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.internal.clipboard.DiagramGenerator#createAWTImageForParts(java.util.List)
	 */
	public Image createAWTImageForParts(List editparts) {
		createSWTImageDescriptorForParts(editparts);
		if (getSVGImage() != null) {
			try {
				return getSVGImage().getBufferedImage();
			} catch (Error e) {
				// log the Error but allow execution to continue
				Trace.catching(DiagramUIRenderPlugin.getInstance(),
					DiagramUIRenderDebugOptions.EXCEPTIONS_THROWING, getClass(),
					"createAWTImageForParts() failed to generate image", //$NON-NLS-1$
					e);
				return ImageConverter.convert(Images.ICON_ERROR);

			} catch (Exception ex) {
				// log the Exception but allow execution to continue
				Trace.catching(DiagramUIRenderPlugin.getInstance(),
					DiagramUIRenderDebugOptions.EXCEPTIONS_THROWING, getClass(),
					"createAWTImageForParts() failed to generate image", //$NON-NLS-1$
					ex);
				return ImageConverter.convert(Images.ICON_ERROR);
			}
		}

		return ImageConverter.convert(Images.ICON_ERROR);
	}

	/**
	 * @return Returns the svgImage.
	 */
	private RenderedImage getSVGImage() {
		return svgImage;
	}

	/**
	 * @param svgImage
	 *            The svgImage to set.
	 */
	private void setSVGImage(RenderedImage svgImage) {
		this.svgImage = svgImage;
	}
}