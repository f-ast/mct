/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2004.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.image;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * A data structure that stores the positional information of a
 *  shape as well as the semantic element behind the shape.
 * 
 * @author yqian 
 * @author cmcgee
 * @author Barys Dubauski
 */
public class PartPositionInfo {

	private int m_iPartHeight;
	private int m_iPartWidth;
	private int m_iPartX;
	private int m_iPartY;
	private EObject m_semanticElement;
	private List m_polyline = null; // List contains objects of type Point (x,y coordinate pairs)

	/**
	 * get the part Height
	 * @return part height
	 */
	public int getPartHeight() {
		return m_iPartHeight;
	}

	/**
	 * set the part height
	 * @param iPartHeight the part height
	 */
	public void setPartHeight(int iPartHeight) {
		m_iPartHeight = iPartHeight;
	}

	/**
	 * get the part width
	 * @return the part width
	 */
	public int getPartWidth() {
		return m_iPartWidth;

	}

	/**
	 * set the part width
	 * @param iPartWidth the part width
	 */
	public void setPartWidth(int iPartWidth) {
		m_iPartWidth = iPartWidth;
	}

	/**
	 * get the part X position
	 * @return X position of the part
	 */
	public int getPartX() {
		return m_iPartX;

	}

	/**
	 * get the part Y position
	 * @return Y position of the part
	 */
	public int getPartY() {
		return m_iPartY;
	}

	/**
	 * set the part X position
	 * @param iPartX the X position of the part 
	 */
	public void setPartX(int iPartX) {
		m_iPartX = iPartX;
	}

	/**
	 * set the part Y Position
	 * @param iPartY the Y position of the part
	 */
	public void setPartY(int iPartY) {
		m_iPartY = iPartY;
	}
	
	/**
	 * Retreive the semantic element of the edit part.
	 * @return The semantic element EObject.
	 */
	public EObject getSemanticElement() {
		return m_semanticElement;
	}

	/**
	 * Sets the semantic element for the edit part.
	 * @param element The semantic element EObject.
	 */
	public void setSemanticElement(EObject element) {
		m_semanticElement = element;
	}
	
	/**
	 * @return List of Point type objects (that carry X and Y coordinate pair) representing the polyline
	 */
	public List getPolyline() {
		return m_polyline;
	}
	/**
	 * @param List of Point type objects (that carry X and Y coordinate pair) representing the polyline
	 */
	public void setPolyline(List m_polyline) {
		this.m_polyline = m_polyline;
	}
	
}