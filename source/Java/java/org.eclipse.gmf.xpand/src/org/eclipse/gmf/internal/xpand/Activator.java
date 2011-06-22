/*******************************************************************************
 * Copyright (c) 2006, 2007 Eclipse.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.gmf.internal.xpand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.internal.xpand.build.MetaModelSource;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {
	private static Activator anInstance;

	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		anInstance = this;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(myRootsTracker);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(myRootsTracker);
		anInstance = null;
		super.stop(context);
	}

	public static String getId() {
		return anInstance.getBundle().getSymbolicName();
	}
	public static void logWarn(String message) {
		log(new Status(IStatus.WARNING, getId(), 0, message, null));
	}
	public static void logError(Exception e) {
		if (e instanceof CoreException) {
			log(((CoreException) e).getStatus());
		} else {
			log(new Status(IStatus.ERROR, getId(), 0, e.getMessage(), e));
		}
	}
	public static void log(IStatus status) {
		anInstance.getLog().log(status);
	}

	private final Map<IProject, RootManager> rootManagers = new HashMap<IProject, RootManager>();

	public static ResourceManager getResourceManager(final IFile file) {
		//TODO: return a delegating resource manager to XpandEditor, to silently change context when roots change.
		RootManager manager = getRootManager(file.getProject());
		ResourceManager result = manager.getResourceManager(file);
		assert result != null;
		return result;
	}

	public static RootManager getRootManager(IProject project) {
		synchronized(anInstance.myRootsTracker) {
			RootManager result = anInstance.rootManagers.get(project);
			if (result == null) {
				result = new RootManager(project);
				anInstance.rootManagers.put(project, result);
			}
			return result;
		}
	}

	private final IResourceChangeListener myRootsTracker = new IResourceChangeListener() {
		public synchronized void resourceChanged(IResourceChangeEvent event) {
			if (event == null || event.getDelta() == null) {
				return;
			}
			Set<RootManager> affectedRootManagers = new HashSet<RootManager>();
			IResourceDelta rootDelta = event.getDelta();
			for (IResourceDelta projectDelta : rootDelta.getAffectedChildren()) {
				IProject affectedProject = (IProject) projectDelta.getResource();
				if (isRemovedOrClosed(projectDelta)) {
					rootManagers.remove(affectedProject);
				} else {
					IResourceDelta configFileDelta = projectDelta.findMember(RootManager.PROJECT_RELATIVE_PATH_TO_CONFIG_FILE);
					if (configFileDelta != null && rootManagers.containsKey(affectedProject) && affectsConfigFile(configFileDelta)) {
						affectedRootManagers.add(getRootManager(affectedProject));
					}
				}
			}
			//Opening/closing or creating/deleting a project may affect roots with absolute paths.
			for (IResourceDelta projectDelta : rootDelta.getAffectedChildren()) {
				if (mayAffectOtherResourceManagers(projectDelta)) {
					IPath projectPath = projectDelta.getFullPath();
					for (RootManager nextManager : rootManagers.values()) {
						if (nextManager.containsProject(projectPath)) {
							affectedRootManagers.add(nextManager);
						}
					}
				}
			}
			for (RootManager nextManager : affectedRootManagers) {
				nextManager.rootsChanged();
			}
		}

		private boolean affectsConfigFile(IResourceDelta configFileDelta) {
			if ((configFileDelta.getKind() & (IResourceDelta.ADDED | IResourceDelta.REMOVED)) > 0) {
				return true;
			}
			if ((configFileDelta.getFlags() & (IResourceDelta.CONTENT | IResourceDelta.ENCODING | IResourceDelta.SYNC | IResourceDelta.TYPE | IResourceDelta.REPLACED)) > 0) {
				return true;
			}
			return false;
		}

		private boolean isRemovedOrClosed(IResourceDelta projectDelta) {
			if (projectDelta.getKind() == IResourceDelta.REMOVED) {
				return true;
			}
			if ((projectDelta.getFlags() & IResourceDelta.OPEN) > 0) {
				return !projectDelta.getResource().isAccessible();
			}
			return false;
		}
		private boolean mayAffectOtherResourceManagers(IResourceDelta projectDelta) {
			if ((projectDelta.getKind() & (IResourceDelta.REMOVED | IResourceDelta.ADDED)) > 0) {
				return true;
			}
			if ((projectDelta.getFlags() & IResourceDelta.OPEN) > 0) {
				return !projectDelta.getResource().isAccessible();
			}
			return false;
		}
	};

	private final Set<MetaModelSource> modelSources = new LinkedHashSet<MetaModelSource>();
	public static void registerModelSource(MetaModelSource modelSource) {
		assert modelSource != null;
		anInstance.modelSources.add(modelSource);
	}

	public static EPackage findMetaModel(String nsURI) {
		if (anInstance == null) {
			// this is for tests execution (which doesn't take place in plugin env)
			return null;
		}
		for (MetaModelSource s : anInstance.modelSources) {
			EPackage p = s.find(nsURI);
			if (p != null) {
				return p;
			}
		}
		return EPackage.Registry.INSTANCE.getEPackage(nsURI);
	}
}