/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.draw2d.ui.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;

/**
 * This is a border that only draws a line at one position around the shape based on a position 
 * constant.  This is useful for cases where it may be necessary to draw a separator between shapes
 * without instantiating a new figure.
 * 
 * @author sshaw
 * @canBeSeenBy %partners
 */
public class OneLineBorder extends LineBorder {

	private int position = PositionConstants.TOP;

	/**
	 * 
	 */
	public OneLineBorder() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param width the width of the line in relative coordinates
	 * @param position the value should be one of <code>PositionConstants.TOP</code>, <code>PositionConstants.BOTTOM</code>,
	 * <code>PositionConstants.LEFT</code>, <code>PositionConstants.RIGHT</code>
	 */
	public OneLineBorder(int width, int position) {
		super(width);
		this.position = position;
	}

	/**
	 * Constructor
	 * 
	 * @param color The color of the border.
	 */
	public OneLineBorder(Color color) {
		super(color);
	}

	/**
	 * Constructor
	 * 
	 * @param color The color of the border.
	 * @param width the width of the line in relative coordinates
	 * @param position the value should be one of <code>PositionConstants.TOP</code>, <code>PositionConstants.BOTTOM</code>,
	 * <code>PositionConstants.LEFT</code>, <code>PositionConstants.RIGHT</code>
	 */
	public OneLineBorder(Color color, int width, int position) {
		super(color, width);
		this.position = position;
	}

	/**
	 * @return Returns the position.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position The position to set.
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.draw2d.Border#getInsets(org.eclipse.draw2d.IFigure)
	 */
	public Insets getInsets(IFigure figure) {
		switch (position) {
			case PositionConstants.TOP :
				return new Insets(getWidth(), 0, 0, 0);
			case PositionConstants.LEFT :
				return new Insets(0, getWidth(), 0, 0);
			case PositionConstants.BOTTOM :
				return new Insets(0, 0, getWidth(), 0);
			case PositionConstants.RIGHT :
				return new Insets(0, 0, 0, getWidth());
			default :
				return IFigure.NO_INSETS;
		}
	}

	/**
	 * Paints the border based on the inputs given. 
	 *
	 * @param figure  <code>IFigure</code> for which this is the border.
	 * @param graphics  <code>Graphics</code> handle for drawing the border.
	 * @param insets  Space to be taken up by this border.
	 */
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		tempRect.setBounds(getPaintRectangle(figure, insets));
		if (getWidth() % 2 == 1) {
			tempRect.width--;
			tempRect.height--;
		}
		tempRect.shrink(getWidth() / 2, getWidth() / 2);
		graphics.setLineWidth(getWidth());
		switch (position) {
			case PositionConstants.TOP :
				graphics.drawLine(tempRect.getTopLeft(), tempRect.getTopRight());
				break;
			case PositionConstants.BOTTOM :
				graphics.drawLine(tempRect.getBottomLeft(), tempRect.getBottomRight());
				break;
			case PositionConstants.LEFT :
				graphics.drawLine(tempRect.getTopLeft(), tempRect.getBottomLeft());
				break;
			case PositionConstants.RIGHT :
				graphics.drawLine(tempRect.getTopRight(), tempRect.getBottomRight());
				break;
		}
	}

}