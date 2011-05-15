/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.diagram.ui.resources.editor.document;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;


/**
 * An interface defining a <code>IDocument</code> with content 
 * of <code>org.eclipse.gmf.runtime.notation.Diagram</code>
 * 
 * @author mgoyal
 */
public interface IDiagramDocument
	extends IDocument {
	/**
	 * Returns the diagram for this diagram document
	 * @return Diagram for this document
	 */
	Diagram getDiagram();
	
	/**
	 * @return The editing domain for this diagram document
	 */
	MEditingDomain getEditingDomain();
	
	/**
	 * Sets the EditingDomain for this document
	 */
	void setEditingDomain(MEditingDomain domain);
	
	/**
	 * Detaches the attached <code>Diagram</code> from this document.
	 *  
	 * @return the detached diagram.
	 */
	Diagram detachDiagram();
	
	/**
	 * Disables the diagram dirtied listener.
	 * 
	 * @return oldState of the listener
	 */
	boolean disableDiagramListener();
	
	/**
	 * Enables the diagram dirtied listener.
	 * 
	 * @return oldstate of the listener.
	 */
	boolean enableDiagramListener();
}