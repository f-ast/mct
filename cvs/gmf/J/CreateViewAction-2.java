/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.actions.internal;

import org.eclipse.gef.Request;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;

import org.eclipse.gmf.runtime.diagram.ui.actions.PresentationAction;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import com.ibm.xtools.notation.Node;

/**
 * This is the action handler for the diagram menu.   Adds actions to 
 * create note and text views.
 *
 * @author schafe
 * @canBeSeenBy %level1
 */
public class CreateViewAction extends PresentationAction {
	
	protected String semanticHint;
	
	/**
	 * Constructor
	 * 
	 * @param workbenchPage, the workbench page
	 * @param id, the id of this action
	 * @param semanticHint
	 * @param label, the menu item label the user will see
	 * @param imageDescriptor, the image next to the label that the user sees
	 */
		public CreateViewAction(
			IWorkbenchPage workbenchPage,
			String actionId,
			String semanticHint,
			String label,
			ImageDescriptor imageDescriptor) {
			
			super(workbenchPage);
			setId(actionId);
			setSemanticHint(semanticHint);
			setText(label);
			setToolTipText(label);
			setImageDescriptor(imageDescriptor);						
		}
		
	/**
	 * Creates a new request to create the shape view.
	 * 
	 * @return A request to create the shape view.
	 */
	protected Request createTargetRequest() {
		
		ViewDescriptor viewDescriptor;
	
		if (getId().equals(ActionIds.ACTION_ADD_NOTE)) {
			viewDescriptor = new ViewDescriptor(null, Node.class,
				Properties.NOTE, getPreferencesHint());
		} else {
			viewDescriptor = new ViewDescriptor(null, Node.class,
				Properties.TEXT, getPreferencesHint());
		}
		
		return new CreateViewRequest(viewDescriptor);
	}	

	/**
	 * @see org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler#isSelectionListener()
	 */
	protected boolean isSelectionListener() {
		return true;
	}	
	
	protected void setSemanticHint(String hint){
		this.semanticHint = hint;		
	}
	
	protected String getSemanticHint(){
		return this.semanticHint;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.PresentationAction#updateTargetRequest()
	 */
//	protected void updateTargetRequest() {
//		super.updateTargetRequest();
//
//		Point p = Point.SINGLETON.getCopy();
//		MapMode.translateToDP(p);
//		CreateViewRequest req = (CreateViewRequest)getTargetRequest();
//		req.setLocation(p);
//	}
}