/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.providers.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.util.Assert;

import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager;
import org.eclipse.gmf.runtime.common.ui.resources.IBookmark;
import org.eclipse.gmf.runtime.common.ui.resources.IFileObserver;
import org.eclipse.gmf.runtime.diagram.core.internal.util.MEditingDomainGetter;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.DiagramActionsDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.internal.l10n.DiagramProvidersResourceManager;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapMode;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import com.ibm.xtools.notation.Diagram;
import com.ibm.xtools.notation.Edge;
import com.ibm.xtools.notation.View;


/**
 * Provides bookmark decorations for views 
 * 
 * @author Michael Yee
 * @canBeSeenBy org.eclipse.gmf.runtime.diagram.ui.providers.*
 */
public class BookmarkDecorator
	extends AbstractDecorator {

	/**
	 * Listner class for bookmark decorators
	 */
	public class BookmarkObserver
		implements IFileObserver {
		/**
		 * Map of id's and their list of decorators
		 */
		private HashMap mapOfIdsToDecorators = null;

		/**
		 * Flag to indicate if already registered with the file change manager
		 */
		private boolean isRegistered = false;
		
		/**
		 * Registers the specified decorator. Nothing happens if already
		 * registered. The decorator is registered against its view id
		 * 
		 * @param decorator the input bookmark decorator
		 */
		private void registerDecorator(BookmarkDecorator decorator) {
			/* Return if invalid decorator */
			if ( decorator == null ) {
				return;
			}
			
			/* Initialize the map */
			if ( mapOfIdsToDecorators == null ) {
				mapOfIdsToDecorators = new HashMap();
			}			

			/* Return if the decorator has invalid view id */
			String decoratorViewId = decorator.getViewId();
			if ( decoratorViewId == null ) {
				return;
			}
			
			/* Add to the list */
			List list = (List)mapOfIdsToDecorators.get(decoratorViewId);
			if (list == null) {
				list = new ArrayList(2);
				list.add(decorator);
				mapOfIdsToDecorators.put(decoratorViewId, list);
			} else if (!list.contains(decorator)) {
				list.add(decorator);
			}
			
			/* Register with the file change manager */
			if ( !isRegistered() ) {
				FileChangeManager.getInstance().addFileObserver(this);
				isRegistered = true;
			}
		}

		/**
		 * Unregisters the specified decorator. Nothing happens if already
		 * unregistered.
		 * 
		 * @param decorator the input bookmark decorator
		 */
		private void unregisterDecorator(BookmarkDecorator decorator) {
			/* Return if invalid decorator */
			if ( decorator == null ) {
				return;
			}
			
			/* Return if the decorator has invalid view id */
			String decoratorViewId = decorator.getViewId();
			if ( decoratorViewId == null ) {
				return;
			}
			
			if (mapOfIdsToDecorators != null) {
				List list = (List)mapOfIdsToDecorators.get(decoratorViewId);
				if ( list != null ) {
					list.remove(decorator);
					if ( list.isEmpty()) {
						mapOfIdsToDecorators.remove(decoratorViewId);
					}				
				}
				
				if ( mapOfIdsToDecorators.isEmpty() ) {
					mapOfIdsToDecorators = null;
				}			
			}
			
			if (mapOfIdsToDecorators == null) {
				/* Unregister with the file change manager */
				if ( isRegistered() ) {
					FileChangeManager.getInstance().removeFileObserver(this);
					isRegistered = false;
				}			
			}			
		}
		
		/*
		 * @see org.eclipse.gmf.runtime.common.ui.resources.IFileObserver#handleFileRenamed(org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile)
		 */
		public void handleFileRenamed(IFile oldFile, IFile file) {
			//Empty Code
		}

		/*
		 * @see org.eclipse.gmf.runtime.common.ui.resources.IFileObserver#handleFileMoved(org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile)
		 */
		public void handleFileMoved(IFile oldFile, IFile file) {
			//Empty Code
		}

		/*
		 * @see org.eclipse.gmf.runtime.common.ui.resources.IFileObserver#handleFileDeleted(org.eclipse.core.resources.IFile)
		 */
		public void handleFileDeleted(IFile file) {
			//Empty Code
		}

		/*
		 * @see org.eclipse.gmf.runtime.common.ui.resources.IFileObserver#handleFileChanged(org.eclipse.core.resources.IFile)
		 */
		public void handleFileChanged(IFile file) {
			//Empty Code
		}

		/*
		 * @see org.eclipse.gmf.runtime.common.ui.resources.IFileObserver#handleMarkerAdded(org.eclipse.core.resources.IMarker)
		 */
		public void handleMarkerAdded(IMarker marker) {
			//Empty Code
		}

		/*
		 * @see org.eclipse.gmf.runtime.common.ui.resources.IFileObserver#handleMarkerDeleted(org.eclipse.core.resources.IMarker, java.util.Map)
		 */
		public void handleMarkerDeleted(IMarker marker, final Map attributes) {
			if ( mapOfIdsToDecorators == null ) {
				return;
			}
			
			Assert.isTrue(!marker.exists());
			// Extract the element guid from the marker and retrieve
			// corresponding view
			try {
				MEditingDomainGetter.getMEditingDomain(getDecoratorTarget()).runAsRead(new MRunnable() {

					public Object run() {
						String elementId = (String) attributes
							.get(IBookmark.ELEMENT_ID);
						List list = elementId != null ? (List)mapOfIdsToDecorators.get(elementId) : null;
						if ( list != null && !list.isEmpty() ) {
							Iterator iter = list.iterator();
							while (iter.hasNext()) {
								IDecorator decorator = (IDecorator)iter.next();
								if ( decorator != null ) {
									decorator.refresh();
								}
							}
						}
						return null;
					}
				});
			} catch (Exception e) {
				Trace.catching(DiagramProvidersPlugin.getInstance(),
					DiagramActionsDebugOptions.EXCEPTIONS_CATCHING, getClass(),
					"handleMarkerDeleted()", //$NON-NLS-1$
					e);
			}
		}

		/*
		 * @see org.eclipse.gmf.runtime.common.ui.resources.IFileObserver#handleMarkerChanged(org.eclipse.core.resources.IMarker)
		 */
		public void handleMarkerChanged(final IMarker marker) {
			if ( mapOfIdsToDecorators == null ) {
				return;
			}
			
			Assert.isTrue(marker.exists());
			// Extract the element ID list from the marker and retrieve
			// corresponding view
			try {
				
				MEditingDomainGetter.getMEditingDomain(getDecoratorTarget()).runAsRead(new MRunnable() {

					public Object run() {
						String elementId = marker.getAttribute(
							IBookmark.ELEMENT_ID, StringStatics.BLANK);
						List list = elementId != null ? (List)mapOfIdsToDecorators.get(elementId) : null;
						if ( list != null && !list.isEmpty() ) {
							Iterator iter = list.iterator();
							while (iter.hasNext()) {
								IDecorator decorator = (IDecorator)iter.next();
								if ( decorator != null ) {
									decorator.refresh();
								}
							}
						}
						return null;
					}
				});
			} catch (Exception e) {
				Trace.catching(DiagramProvidersPlugin.getInstance(),
					DiagramActionsDebugOptions.EXCEPTIONS_CATCHING, getClass(),
					"handleMarkerChanged()", //$NON-NLS-1$
					e);
			}
		}
		
		/**
		 * Returns the flag if this observer is registered
		 * @return the isRegistered.
		 */
		private boolean isRegistered() {
			return isRegistered;
		}
	}
	
	/**
	 * Resource listener for bookmark creation and removal 
	 */
	private static BookmarkObserver fileObserver = null;
	
	private static final String BOOKMARK = "bookmark.gif"; //$NON-NLS-1$

	/**
	 * Attribute for the view id
	 */
	private String viewId = null;
	
	/**
	 * Creates a new <code>BookmarkDecorator</code>.
	 * @param decoratorTarget
	 */
	public BookmarkDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);

		/* Set the id */		
		try {
			MEditingDomainGetter.getMEditingDomain(decoratorTarget).runAsRead(
				new MRunnable() {

					/*
					 * @see java.lang.Runnable#run()
					 */
					public Object run() {
						View view = (View) BookmarkDecorator.this
							.getDecoratorTarget().getAdapter(View.class);
						BookmarkDecorator.this.viewId = view != null ? ViewUtil
							.getIdStr(view)
							: null;
						return null;
					}
				});
		} catch (Exception e) {
			Trace.catching(DiagramProvidersPlugin.getInstance(),
				DiagramActionsDebugOptions.EXCEPTIONS_CATCHING, getClass(),
				"BookmarkDecorator::Constructor", //$NON-NLS-1$
				e);
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#refresh()
	 */
	public void refresh() {
		removeDecoration();

		View view = (View) getDecoratorTarget().getAdapter(View.class);
		EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(
			EditPart.class);
		if (!(editPart instanceof IPrimaryEditPart) || view == null || view.eResource()==null) {
			return;
		}

		IResource resource = getResource(view);
		// make sure we have a resource and that it exists in an open project
		if (resource == null || !resource.exists()) {
			return;
		}

		// query for all the bookmarks of the current resource
		IMarker[] bookmarks = null;
		try {
			bookmarks = resource.findMarkers(IBookmark.TYPE, true,
				IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			Trace.catching(DiagramProvidersPlugin.getInstance(),
				DiagramActionsDebugOptions.EXCEPTIONS_CATCHING, getClass(),
				"getDecorations", e); //$NON-NLS-1$
			Log.error(DiagramProvidersPlugin.getInstance(), IStatus.ERROR, e
				.getMessage());
		}
		if (bookmarks == null) {
			return;
		}

		// find the bookmark containing the element's GUID
		IMarker foundMarker = null;
		String elementId = EObjectUtil.getID(view);
		if (elementId == null) {
			return;
		}
		
		for (Iterator i = Arrays.asList(bookmarks).iterator(); i.hasNext();) {
			IMarker marker = (IMarker) i.next();
			String attribute = marker.getAttribute(IBookmark.ELEMENT_ID,
				StringStatics.BLANK);
			if (attribute.equals(elementId)) {
				foundMarker = marker;
				break;
			}
		}
		if (foundMarker == null) {
			return;
		}

		// add the bookmark decoration
		if (editPart instanceof ShapeEditPart) {
			setDecoration(getDecoratorTarget().addShapeDecoration(
				DiagramProvidersResourceManager.getInstance().getImage(BOOKMARK),
				IDecoratorTarget.Direction.NORTH_EAST, MapMode.DPtoLP(-4), true));
		} else if (view instanceof Edge) {
			setDecoration(getDecoratorTarget().addConnectionDecoration(
				DiagramProvidersResourceManager.getInstance().getImage(
					BOOKMARK), 50, true));
		}
	}

	/**
	 * Gets the underlying resource of the given view
	 * @param view the given view
	 * @return the view's resource, otherwise <code>null</code>
	 */
	private static IResource getResource(View view) {
		Resource model = view.eResource();
		if (model != null) {
			MEditingDomain editingDomain = MEditingDomainGetter.getMEditingDomain(model);
			String filePath = editingDomain.getResourceFileName(model);
			if (filePath != null && filePath.length() != 0) {
				return ResourcesPlugin.getWorkspace().getRoot()
					.getFileForLocation(
						new Path(editingDomain.getResourceFileName(model)));
			}
		}
		return null;
	}

	/** 
	 * Starts listening to events on the decoratorTarget element's container.
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#activate()
	 */
	public void activate() {
		View view = (View) getDecoratorTarget().getAdapter(View.class);
		if (view == null) return;
		Diagram diagramView = view.getDiagram();
		if (diagramView == null) return;
		IFile file = EObjectUtil.getFile(diagramView);
		// It does not make sense to add a file observer if the resource
		//  is not persisted or the uri is not in the form of file:///
		if (file != null) {
			if ( fileObserver == null ) {
				fileObserver = new BookmarkObserver();
			}

			fileObserver.registerDecorator(this);
		}
	}

	/**
	 * Stops the listener and removes the decoration if it is being displayed.
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#deactivate()
	 */
	public void deactivate() {
		if ( fileObserver != null ) {
			fileObserver.unregisterDecorator(this);
			if (!fileObserver.isRegistered()) {
				fileObserver = null;
			}
		}
	
		super.deactivate();
	}
	
	/**
	 * Returns the view id
	 * @return the viewId.
	 */
	private String getViewId() {
		return viewId;
	}
}