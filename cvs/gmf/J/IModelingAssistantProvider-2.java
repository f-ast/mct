/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.  	               |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.emf.ui.services.modelingassistant;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.common.core.service.IProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * The interface for providers of the modeling assistant service.
 * 
 * @author cmahoney
 */
public interface IModelingAssistantProvider
	extends IProvider {

	/**
	 * Gets a list of types to be used in the popup menu when qualifying an
	 * attribute type. This is triggered when the user attempts to show an
	 * untyped attribute as an association.
	 * 
	 * @param attribute
	 *            the attribute to be shown as an association: adapts to
	 *            editpart, view, element, etc.
	 * @return a list of element types (<code>IElementType</code>)
	 */
	List getTypesForAttribute(IAdaptable attribute);

	/**
	 * Returns a list of relationship types that could appear in EITHER the
	 * popup that appears when creating a connection from source to target or
	 * the popup that appears when creating a connection from source to an
	 * unspecified target.
	 * 
	 * @param source
	 *            the source: adapts to editpart, view, element, etc.
	 * @return a list of relationship types (<code>IElementType</code>)
	 */
	List getRelTypesOnSource(IAdaptable source);

	/**
	 * Returns a list of relationship types that could appear in EITHER the
	 * popup that appears when creating a connection from target to source or
	 * the popup that appears when creating a connection from target to an
	 * unspecified source.
	 * 
	 * @param target
	 *            the target: adapts to editpart, view, element, etc.
	 * @return a list of relationship types (<code>IElementType</code>)
	 */
	List getRelTypesOnTarget(IAdaptable target);

	/**
	 * Returns a list of relationship types to appear in the popup that the user
	 * can select from when creating a relationship from a source element to a
	 * target element.
	 * 
	 * @param source
	 *            the source: adapts to editpart, view, element, etc.
	 * @param target
	 *            the target: adapts to editpart, view, element, etc.
	 * @return a list of relationship types (<code>IElementType</code>)
	 */
	List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target);

	/**
	 * Returns a list of relationship types to appear in the popup that the user
	 * can select from when showing related elements by double-clicking an
	 * outgoing connector handle.
	 * 
	 * @param source
	 *            the source: adapts to editpart, view, element, etc.
	 * @return a list of relationship types (<code>IElementType</code>)
	 */
	List getRelTypesForSREOnSource(IAdaptable source);

	/**
	 * Returns a list of relationship types to appear in the popup that the user
	 * can select from when showing related elements by double-clicking an
	 * incoming connector handle.
	 * 
	 * @param target
	 *            the target: adapts to editpart, view, element, etc.
	 * @return a list of relationship types (<code>IElementType</code>
	 *         objects)
	 */
	List getRelTypesForSREOnTarget(IAdaptable target);

	/**
	 * Returns a list of element types to appear in the popup when creating a
	 * relationship from a target element to an unspecified source from which
	 * the user can select the source type.
	 * 
	 * @param target
	 *            the target: adapts to editpart, view, element, etc.
	 * @param relationshipType
	 *            the relationship type to be created
	 * @return a list of types for the new source (<code>IElementType</code>
	 *         objects)
	 */
	List getTypesForSource(IAdaptable target, IElementType relationshipType);

	/**
	 * Returns a list of element types to appear in the popup when creating a
	 * relationship from a source element to an unspecified target from which
	 * the user can select the target type.
	 * 
	 * @param source
	 *            the source: adapts to editpart, view, element, etc.
	 * @param relationshipType
	 *            the relationship type to be created
	 * @return a list of types for the new target (<code>IElementType</code>
	 *         objects)
	 */
	List getTypesForTarget(IAdaptable source, IElementType relationshipType);

	/**
	 * Pops up a dialog that allows the user to select an existing element that
	 * is to be used as the source in a new relationship being created.
	 * 
	 * @param target
	 *            the target: adapts to editpart, view, element, etc.
	 * @param relationshipType
	 *            the relationship type to be created
	 * @return Returns the <code>EObject</code> the user selected, or null if
	 *         an error occurred or the gesture was cancelled.
	 */
	EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType);

	/**
	 * Pops up a dialog that allows the user to select an existing element that
	 * is to be used as the target in a new relationship being created.
	 * 
	 * @param source
	 *            the source: adapts to editpart, view, element, etc.
	 * @param relationshipType
	 *            the relationship type to be created
	 * @return Returns the <code>IElement</code> the user selected, or null if
	 *         an error occurred or the gesture was cancelled.
	 */
	EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType);


	/**
	 * Gets a list of types to be used in the actionbar to support creating an
	 * shape or element within the context of another.
	 * 
	 * @param host
	 *            the host on which the actionbar will be displayed: adapts to
	 *            editpart, view, element, etc.
	 * @return a list of element types (<code>IElementType</code>)
	 */
	List getTypesForActionBar(IAdaptable host);
}