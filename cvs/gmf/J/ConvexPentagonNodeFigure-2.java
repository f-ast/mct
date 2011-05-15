/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.gef.ui.internal.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

import org.eclipse.gmf.runtime.draw2d.ui.figures.IPolygonAnchorableFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;


/**
 * Provides support for Convex Pentagon figure (Send Signal Action for Activity diagram) 
 * 
 * @author oboyko
 * @canBeSeenBy org.eclipse.gmf.runtime.gef.ui.*
 */
public class ConvexPentagonNodeFigure
	extends DefaultSizeNodeFigure implements IPolygonAnchorableFigure{

	private static final double DEFAULT_CONVEX_RATIO = 0.25;
	
	/**
	 * Default constructor
	 */
	public ConvexPentagonNodeFigure() {
		super();
	}

	/**
	 * Constructor
	 * @param defSize
	 */
	public ConvexPentagonNodeFigure(Dimension defSize) {
		super(defSize);
	}

	/**
	 * Constructor
	 * @param width
	 * @param height
	 */
	public ConvexPentagonNodeFigure(int width, int height) {
		super(width, height);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		PointList pointList = getPolygonPoints();

		graphics.fillPolygon(pointList);
		graphics.drawPolygon(pointList);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.gef.ui.internal.figures.IPolygonAnchorableFigure#getPolygonPoints()
	 */
	public PointList getPolygonPoints() {
		PointList ptList = new PointList();
		ptList.addPoint(new Point(getBounds().x, getBounds().y));
		ptList.addPoint(new Point(getBounds().x + getBounds().width*(1-DEFAULT_CONVEX_RATIO), getBounds().y));
		ptList.addPoint(new Point(getBounds().x+getBounds().width, getBounds().y+getBounds().height/2));
		ptList.addPoint(new Point(getBounds().x+getBounds().width*(1-DEFAULT_CONVEX_RATIO), getBounds().y+getBounds().height));
		ptList.addPoint(new Point(getBounds().x, getBounds().y+getBounds().height));
		ptList.addPoint(new Point(getBounds().x, getBounds().y));
		return ptList;
	}
	
}