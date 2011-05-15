/*
 * Copyright (c) 2006 Eclipse.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 */
package org.eclipse.gmf.bridge.ui.dashboard;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IMemento;

/**
 * EXPERIMENTAL
 * 
 * @author dstadnik
 */
public final class DashboardState {

	private static final String DM_KEY = "domainModel"; //$NON-NLS-1$

	private static final String DGM_KEY = "domainGenerationModel"; //$NON-NLS-1$

	private static final String GDM_KEY = "graphicalDefinitionModel"; //$NON-NLS-1$

	private static final String TDM_KEY = "toolingDefinitionModel"; //$NON-NLS-1$

	private static final String MM_KEY = "mappingModel"; //$NON-NLS-1$

	private static final String GM_KEY = "generationModel"; //$NON-NLS-1$

	private URI dm;

	private URI dgm;

	private URI gdm;

	private URI tdm;

	private URI mm;

	private URI gm;

	public DashboardState() {
	}

	public DashboardState(IMemento memento) {
		if (memento != null) {
			read(memento);
		}
	}

	public URI getDM() {
		return dm;
	}

	public URI getDGM() {
		return dgm;
	}

	public URI getGDM() {
		return gdm;
	}

	public URI getTDM() {
		return tdm;
	}

	public URI getMM() {
		return mm;
	}

	public URI getGM() {
		return gm;
	}

	public void setDM(URI uri) {
		dm = uri;
	}

	public void setDGM(URI uri) {
		dgm = uri;
	}

	public void setGDM(URI uri) {
		gdm = uri;
	}

	public void setTDM(URI uri) {
		tdm = uri;
	}

	public void setMM(URI uri) {
		mm = uri;
	}

	public void setGM(URI uri) {
		gm = uri;
	}

	public void setDM(IFile file) {
		dm = getURI(file);
	}

	public void setDGM(IFile file) {
		dgm = getURI(file);
	}

	public void setGDM(IFile file) {
		gdm = getURI(file);
	}

	public void setTDM(IFile file) {
		tdm = getURI(file);
	}

	public void setMM(IFile file) {
		mm = getURI(file);
	}

	public void setGM(IFile file) {
		gm = getURI(file);
	}

	private static URI getURI(IFile file) {
		if (file == null) {
			return null;
		}
		return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
	}

	public int getModelsCount() {
		return 6;
	}

	public int getSpecifiedModelsCount() {
		int count = 0;
		if (dm != null) {
			count++;
		}
		if (dgm != null) {
			count++;
		}
		if (gdm != null) {
			count++;
		}
		if (tdm != null) {
			count++;
		}
		if (mm != null) {
			count++;
		}
		if (gm != null) {
			count++;
		}
		return count;
	}

	private void read(IMemento memento) {
		dm = read(memento, DM_KEY);
		dgm = read(memento, DGM_KEY);
		gdm = read(memento, GDM_KEY);
		tdm = read(memento, TDM_KEY);
		mm = read(memento, MM_KEY);
		gm = read(memento, GM_KEY);
	}

	private static URI read(IMemento memento, String key) {
		String s = memento.getString(key);
		if (s == null) {
			return null;
		}
		try {
			return URI.createURI(s);
		} catch (IllegalArgumentException e) {
		}
		return null;
	}

	public void write(IMemento memento) {
		write(memento, DM_KEY, dm);
		write(memento, DGM_KEY, dgm);
		write(memento, GDM_KEY, gdm);
		write(memento, TDM_KEY, tdm);
		write(memento, MM_KEY, mm);
		write(memento, GM_KEY, gm);
	}

	private static void write(IMemento memento, String key, URI uri) {
		String s = null;
		if (uri != null) {
			s = uri.toString();
		}
		memento.putString(key, s);
	}
}