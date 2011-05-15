/******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.diagram.ui.properties.sections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.common.ui.properties.internal.provisional.AbstractPropertySection;
import org.eclipse.wst.common.ui.properties.internal.provisional.ITabbedPropertySheetPageContributor;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;

import org.eclipse.gmf.runtime.common.core.command.CommandManager;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.common.ui.services.properties.PropertiesServiceAdapterFactory;
import org.eclipse.gmf.runtime.diagram.core.internal.util.MEditingDomainGetter;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesPlugin;
import org.eclipse.gmf.runtime.diagram.ui.properties.util.SectionUpdateRequestCollapser;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.IReadOnlyDiagramPropertySheetPageContributor;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.PropertiesBrowserPage;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractModelCommand;
import org.eclipse.gmf.runtime.emf.core.edit.DemuxingMListener;
import org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.ui.internal.l10n.EMFUIMessages;

/**
 * An abstract implementation of a section in a tab in the tabbed property sheet
 * page for modeler.
 * 
 * @author Anthony Hunter <a
 *         href="mailto:anthonyh@ca.ibm.com">anthonyh@ca.ibm.com </a>
 */
public abstract class AbstractModelerPropertySection
	extends AbstractPropertySection
	implements IDemuxedMListener {

	private TabbedPropertySheetPage tabbedPropertySheetPage;

	/**
	 * model event listener
	 */
	protected DemuxingMListener eventListener = new DemuxingMListener(this);

	// properties provider to obtain properties of the objects on the list
	protected static final PropertiesServiceAdapterFactory propertiesProvider = new PropertiesServiceAdapterFactory();

	private boolean bIsCommandInProgress = false;

	/** value changed string */
	static protected String VALUE_CHANGED_STRING = EMFUIMessages.AbstractPropertySection_UndoIntervalPropertyString;

	/** object currently selected on either a diagram or a ME - a view */
	protected List input;

	/** eObject should gradually replace EElement */
	protected EObject eObject;

	private List eObjectList = new ArrayList();

	/**
	 * a flag indicating if this property section got disposed
	 */
	protected boolean disposed = false;

	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		if (!(selection instanceof IStructuredSelection)
			|| selection.equals(getInput()))
			return;

		input = new ArrayList();

		eObjectList = new ArrayList();
		for (Iterator it = ((IStructuredSelection) selection).iterator(); it
			.hasNext();) {
			Object next = it.next();
			
			// unwrap down to EObject and add to the eObjects list
			if (addToEObjectList(next)) {
				input.add(next);
			}
		}


		// RATLC000524513 Sometimes there is no eobject. For example if user
		// creates a constraint,
		// on a class there will be a connection shown on the diagram which
		// connects the constraint
		// with the class. The user can select this connection, even though it
		// does not have an
		// underlying eobject. Comments are similar. In this case we show only
		// the appearanced tab.
		if (false == eObjectList.isEmpty())
			setEObject((EObject) eObjectList.get(0));

	}

	/**
	 * Add next object in the selection to the list of EObjects if this object 
	 * could be adapted to an <code>EObject</code>
	 * @param object the object to add
	 * @return - true if the object is added, false otherwise 
	 */
	protected boolean addToEObjectList(Object object) {
		EObject adapted = unwrap(object);
		if (adapted != null){
			getEObjectList().add(adapted);
			return true;
		}		
		return false;

	}

	/**
	 * Unwarp the ME or diagram object down to the underlaying UML element
	 * 
	 * @param object -
	 *            object from a diagram or ME
	 * @return - underlaying UML element
	 */
	protected EObject unwrap(Object object) {
		return adapt(object);
	}

	/**
	 * Adapt the object to an EObject - if possible
	 * 
	 * @param object
	 *            object from a diagram or ME
	 * @return EObject
	 */
	protected EObject adapt(Object object) {
		if (object instanceof IAdaptable) {
			return (EObject) ((IAdaptable) object).getAdapter(EObject.class);
		}

		return null;
	}

	/**
	 * Determines if the page is displaying properties for this element
	 * 
	 * @param notification
	 *            The notification
	 * @param element
	 *            The element to be tested
	 * @return 'true' if the page is displaying properties for this element
	 */
	protected boolean isCurrentSelection(Notification notification,
			EObject element) {

		if (element == null)
			return false;

		if (eObjectList.contains(element))
			return true;

		if (eObjectList.size() > 0) {
			EObject eventObject = element;

			// check for annotations
			if (element instanceof EAnnotation) {
				eventObject = element.eContainer();
			} else {
				EObject container = element.eContainer();
				if (container != null && container instanceof EAnnotation) {
					eventObject = container.eContainer();
				}
			}

			if (eventObject == null) {
				// the annotation has been removed - check the old owner
				Object tmpObj = notification.getOldValue();
				if (tmpObj != null && tmpObj instanceof EObject) {
					eventObject = (EObject) tmpObj;
				} else {
					return false;
				}
			}

			if (eventObject != element) {
				return eObjectList.contains(eventObject);
			}

		}
		return false;
	}

	/**
	 * A utility method allows execute a piece of code wrapping it in the read
	 * call to the model.
	 * 
	 * @param code -
	 *            Runnable code to execute
	 */
	protected void executeAsReadAction(final Runnable code) {
		executeAsReadAction(new MRunnable() {

			public Object run() {
				code.run();
				return null;
			}
		});
	}
	
	/**
	 * A utility method allows execute a piece of code wrapping it in the read
	 * call to the model.
	 * 
	 * @param code -
	 *            MRunnable code to execute
	 */
	protected Object executeAsReadAction(MRunnable code) {		
		return MEditingDomainGetter.getMEditingDomain(getEObjectList()).runAsRead(code);
	}	

	/**
	 * A utility method allows execute a list of commands by wrapping them\ in a
	 * composite command.
	 * 
	 * @param commands -
	 *            List of commands to execute
	 */
	protected CommandResult executeAsCompositeCommand(String actionName,
			List commands) {
		
		if (true == bIsCommandInProgress)
			return null;

		bIsCommandInProgress = true;

		CompositeCommand compCmd = new CompositeCommand(actionName, commands);

		CommandResult result = CommandManager.getDefault().execute(compCmd);

		if (result.getStatus().getCode() == DiagramPropertiesStatusCodes.CANCELLED)
			refresh();

		bIsCommandInProgress = false;

		return result;

	}

	/**
	 * Returns currently selected view object
	 * 
	 * @return Returns the input.
	 */
	public List getInput() {
		return input;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#aboutToBeHidden()
	 */
	public void aboutToBeHidden() {
		super.aboutToBeHidden();
		eventListener.stopListening();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#aboutToBeShown()
	 */
	public void aboutToBeShown() {
		super.aboutToBeShown();
		eventListener.startListening();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#dispose()
	 */
	public void dispose() {
		super.dispose();
		/*
		 * if (getUpdateRequestCollapser() != null) {
		 * getUpdateRequestCollapser().stop(); updateRequestCollapser = null; }
		 */
		disposed = true;

	}
	
	/**
	 * Returns currently selected view object
	 * 
	 * @return Returns the input.
	 */
	protected Object getPrimarySelection() {
		return (getInput() != null && !getInput().isEmpty() ? getInput().get(0)
			: null);
	}
	

	/**
	 * @return Returns the eObject.
	 */
	protected EObject getEObject() {
		return eObject;
	}

	/**
	 * @param object
	 *            The eObject to set.
	 */	
	protected void setEObject(EObject object) {
		this.eObject = object;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#getFilter()
	 */
	public MFilter getFilter() {
		return MFilter.ELEMENT_MODIFIED_FILTER;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleResourceLoadedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.resource.Resource)
	 */
	public void handleResourceLoadedEvent(Notification notification,
			Resource resource) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleResourceUnloadedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.resource.Resource, org.eclipse.emf.ecore.EObject)
	 */
	public void handleResourceUnloadedEvent(Notification notification,
			Resource resource, EObject modelRoot) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleResourceDirtiedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.resource.Resource)
	 */
	public void handleResourceDirtiedEvent(Notification notification,
			Resource resource) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleResourceSavedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.resource.Resource)
	 */
	public void handleResourceSavedEvent(Notification notification,
			Resource resource) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleResourceImportedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.resource.Resource)
	 */
	public void handleResourceImportedEvent(Notification notification,
			Resource resource) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleResourceExportedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.resource.Resource)
	 */
	public void handleResourceExportedEvent(Notification notification,
			Resource resource) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleElementCreatedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	public void handleElementCreatedEvent(Notification notification,
			EObject owner, EObject newElement) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleElementDeletedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	public void handleElementDeletedEvent(Notification notification,
			EObject owner, EObject oldElement) {
		/* not implemented */
	}

	/**
	 * Update if nessesary, upon receiving the model event. This event will only
	 * be processed when the reciever is visible (the default behavior is not to
	 * listen to the model events while not showing). Therefore it is safe to
	 * refresh the UI. Sublclasses, which will choose to override event
	 * listening behavior should take into account that the model events are
	 * sent all the time - regardless of the section visibility. Thus special
	 * care should be taken by the section that will choose to listen to such
	 * events all the time. Also, the default implementation of this method
	 * synchronizes on the GUI thread. The subclasses that overwrite it should
	 * do the same if they perform any GUI work (because events may be sent from
	 * a non-GUI thread).
	 * 
	 * @see #aboutToBeShown()
	 * @see #aboutToBeHidden()
	 * 
	 * @param notification notification object
	 * @param element element that has changed
	 */
	public void update(final Notification notification, final EObject element) {
		if (!isDisposed() && isCurrentSelection(notification, element)
			&& !isNotifierDeleted(notification)) {
			postUpdateRequest(new Runnable() {

				public void run() {
					if (!isDisposed()
						&& isCurrentSelection(notification, element)
						&& !isNotifierDeleted(notification))
						refresh();

				}
			});
		}
	}

	/**
	 * Returns whether or not the notifier for a particular notification has been
	 * deleted from its parent.
	 * 
	 * This is a fix for RATLC00535181.  What happens is that during deletion of
	 * an element from the diagram, the element first deletes related elements
	 * which causes a modification of the element itself.  When the modification occurs
	 * the event handling mechanism posts a request to the UI queue to refresh the UI.
	 * A race condition occurs where by the time the posted request runs, the element
	 * in question may or may not have already been deleted from its container.  If
	 * the element has been deleted from its container, we should not refresh the
	 * property section.
	 * 
	 * @param notification
	 * @return <code>true</code> if notification has been deleted from its parent, <code>false</code> otherwise
	 */
	protected boolean isNotifierDeleted(Notification notification) {
		if (!(notification.getNotifier() instanceof EObject)) {
			return false;
		}
		EObject obj = (EObject)notification.getNotifier();
		return obj.eResource() == null;
	}
	
	/**
	 * Use requset collapser to post update requests.
	 * 
	 * @param updateRequest -
	 *            runnable update code
	 */
	protected void postUpdateRequest(Runnable updateRequest) {
		getUpdateRequestCollapser().postRequest(this, updateRequest);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleElementModifiedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.emf.ecore.EObject)
	 */
	public void handleElementModifiedEvent(final Notification notification,
			final EObject element) {
		update(notification, element);
	}

	/**
	 * Handle the action abandoned exception
	 * 
	 * @param exception
	 *            the action abandoned exception
	 */
	protected void handleException(MSLActionAbandonedException exception) {
		Trace.catching(DiagramPropertiesPlugin.getDefault(),
			DiagramPropertiesDebugOptions.EXCEPTIONS_CATCHING, getClass(),
			exception.getMessage(), exception);
		Log.warning(DiagramPropertiesPlugin.getDefault(),
			DiagramPropertiesStatusCodes.IGNORED_EXCEPTION_WARNING,
			exception.getMessage(), exception);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleUndoIntervalClosedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval)
	 */
	public void handleUndoIntervalClosedEvent(Notification notification,
			MUndoInterval undoInterval) {
		/* not implemented */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#handleUndoIntervalsFlushedEvent(org.eclipse.emf.common.notify.Notification, org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval)
	 */
	public void handleUndoIntervalsFlushedEvent(Notification notification,
			MUndoInterval undoInterval) {
		/* not implemented */
	}

	/**
	 * @return Returns the eObjectList.
	 */
	protected List getEObjectList() {
		return eObjectList;
	}

	/**
	 * @return Returns the disposed.
	 */
	protected boolean isDisposed() {
		return disposed;
	}

	/**
	 * @return Returns the eventListener.
	 */
	protected DemuxingMListener getEventListener() {
		return eventListener;
	}

	/**
	 * @return Returns a command
	 */
	protected ICommand createCommand(String name, Resource res,
			final Runnable runnable) {

		return createCommandInternal(name, res, runnable);
	}

	/**
	 * @return Returns a command
	 */
	protected ICommand createCommand(String name, EObject res,
			final Runnable runnable) {

		return createCommandInternal(name, res, runnable);
	}

	/**
	 * @return Returns a command
	 */
	private ICommand createCommandInternal(String name, Object res,
			final Runnable runnable) {

		ICommand command = new AbstractModelCommand(name, res) {

			protected CommandResult doExecute(IProgressMonitor progressMonitor) {
				runnable.run();

				return newOKCommandResult();
			}
		};

		return command;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		this.tabbedPropertySheetPage = aTabbedPropertySheetPage;

	}

	/**
	 * Determine if the property sheet page contributor is read only.
	 * 
	 * Topic and Browse diagrams have properties that are read only, even
	 * theough the selection may be modifiable.
	 * 
	 * @return <code>true</code> if the contributor is read only.
	 */
	protected boolean isReadOnly() {
		if (tabbedPropertySheetPage instanceof PropertiesBrowserPage) {
			PropertiesBrowserPage propertiesBrowserPage = (PropertiesBrowserPage) tabbedPropertySheetPage;
			ITabbedPropertySheetPageContributor contributor = propertiesBrowserPage
				.getContributor();
			if (contributor instanceof IReadOnlyDiagramPropertySheetPageContributor) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the standard label width when labels for sections line up on the left
	 * hand side of the composite. We line up to a fixed position, but if a
	 * string is wider than the fixed position, then we use that widest string.
	 * 
	 * @param parent
	 *            The parent composite used to create a GC.
	 * @param labels
	 *            The list of labels.
	 * @return the standard label width.
	 */
	protected int getStandardLabelWidth(Composite parent, String[] labels) {
		int standardLabelWidth = STANDARD_LABEL_WIDTH;
		GC gc = new GC(parent);
		int indent = gc.textExtent("XXX").x; //$NON-NLS-1$
		for (int i = 0; i < labels.length; i++) {
			int width = gc.textExtent(labels[i]).x;
			if (width + indent > standardLabelWidth) {
				standardLabelWidth = width + indent;
			}
		}
		gc.dispose();
		return standardLabelWidth;
	}

	/**
	 * @return Returns the updateRequestCollapser.
	 */
	protected SectionUpdateRequestCollapser getUpdateRequestCollapser() {
		return DiagramPropertiesPlugin.getDefault()
			.getUpdateRequestCollapser();
	}
}