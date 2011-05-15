/*******************************************************************************
 * Copyright (c) 2006, 2007 Borland Software Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.gmf.internal.xpand.build;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gmf.internal.xpand.Activator;
import org.eclipse.gmf.internal.xpand.ResourceManager;
import org.eclipse.gmf.internal.xpand.expression.SyntaxConstants;
import org.eclipse.gmf.internal.xpand.model.XpandResource;
import org.eclipse.gmf.internal.xpand.util.ParserException;
import org.eclipse.gmf.internal.xpand.util.ResourceManagerImpl;
import org.eclipse.gmf.internal.xpand.util.StreamConverter;
import org.eclipse.gmf.internal.xpand.xtend.ast.XtendResource;

// FIXME package-local?, refactor Activator.getResourceManager uses
public class WorkspaceResourceManager extends ResourceManagerImpl {
	private final IProject contextProject;
	private long configStamp = IResource.NULL_STAMP;
	private IPath[] configuredRoots;

	public WorkspaceResourceManager(IProject context) {
		this.contextProject = context;
	}

	public XtendResource loadXtendResource(IFile file) throws CoreException, IOException, ParserException {
		if (file == null) {
			return null;
		}
		assert file.getProject() == contextProject;
		Reader r = null;
		try {
			r = new StreamConverter().toContentsReader(file);
			return super.loadXtendResource(r, toFullyQualifiedName(file));
		} finally {
			if (r != null) {
				r.close();
			}
		}
	}

	public XpandResource loadXpandResource(IFile file) throws CoreException, IOException, ParserException {
		if (file == null) {
			return null;
		}
		assert file.getProject() == contextProject;
		Reader r = null;
		try {
			r = new StreamConverter().toContentsReader(file);
			return super.loadXpandResource(r, toFullyQualifiedName(file));
		} finally {
			if (r != null) {
				r.close();
			}
		}
	}

	@Override
	protected boolean shouldCache() {
		// we don't cache workspace resources for now (for the sake of reducing 
		// underemined problems that may arise), although may do this later
		return false;
	}

	public void forget(IFile resource) {
		// implement when caching
	}

	protected Reader resolve(String fqn, String ext) throws IOException {
		IPath fp = new Path(fqn.replaceAll(SyntaxConstants.NS_DELIM, "/")).addFileExtension(ext);
		IResource r = null;
		for (IPath p : getResolutions(fp)) {
			r = contextProject.findMember(p);
			if (r != null) {
				break;
			}
		}
		if (false == r instanceof IFile) {
			throw new FileNotFoundException(fp.toString());
		}
		try {
			return new StreamConverter().toContentsReader((IFile) r);
		} catch (CoreException ex) {
			IOException wrap = new IOException(ex.getStatus().getMessage());
			wrap.initCause(ex);
			throw wrap;
		}
	}
	private IPath[] getResolutions(IPath p) {
		IPath[] configured = getConfiguredRoots();
		IPath[] rv = new IPath[configured.length + 1];
		rv[0] = p;
		for (int i = 0; i < configured.length; i++) {
			rv[i+1] = configured[i].append(p);
		}
		return rv;
	}
	private IPath[] getConfiguredRoots() {
		IFile config = contextProject.getFile(".xpand-root");
		if (!config.exists()) {
			return new IPath[] { new Path("templates/") };
		}
		if (config.getModificationStamp() != configStamp) {
			configuredRoots = new IPath[0];
			final ArrayList<IPath> read = new ArrayList<IPath>();
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(config.getContents(), config.getCharset()));
				String line;
				while((line = in.readLine()) != null) {
					line = line.trim();
					if (line.length() > 0 && line.charAt(0) != '#') {
						read.add(new Path(line));
					}
				}
			} catch (CoreException ex) {
				// IGNORE
			} catch (IOException ex) {
				// IGNORE
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException ex) {
						/* IGNORE */
					}
				}
			}
			configuredRoots = read.toArray(new IPath[read.size()]);
			configStamp = config.getModificationStamp();
		}
		return configuredRoots;
	}

	protected ResourceManager[] getDependenies() {
		LinkedList<ResourceManager> rv = new LinkedList<ResourceManager>();
		try {
			IProject[] referencedProjects = contextProject.getReferencedProjects();
				for (IProject next : referencedProjects) {
					if (!next.isAccessible() || !hasXpandBuilder(next)) {
						continue;
					}
					rv.add(Activator.getResourceManager(next));
				}
		} catch (CoreException e) {
			//ignore
		}
		return rv.toArray(new ResourceManager[rv.size()]);
	}

	private static boolean hasXpandBuilder(IProject p) throws CoreException {
		for (ICommand c : p.getDescription().getBuildSpec()) {
			if (OawBuilder.getBUILDER_ID().equals(c.getBuilderName())) {
				return true;
			}
		}
		return false;
	}

	private String toFullyQualifiedName(IFile file) {
		return file.getProjectRelativePath().toString().replaceAll("/", SyntaxConstants.NS_DELIM);
	}
}