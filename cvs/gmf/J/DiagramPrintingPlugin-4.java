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

package org.eclipse.gmf.runtime.diagram.ui.printing.internal;

import org.eclipse.gmf.runtime.common.ui.plugin.XToolsUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
/*
 * @canBeSeenBy org.eclipse.gmf.runtime.diagram.ui.printing.*
 */
public class DiagramPrintingPlugin extends XToolsUIPlugin {
	//The shared instance.
	private static DiagramPrintingPlugin plugin;

	/**
	 * The constructor.
	 */
	public DiagramPrintingPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the plugin instance
	 */
	public static DiagramPrintingPlugin getInstance() {
		return plugin;
	}

	/**
	 * Retrieves the unique identifier of this plug-in.
	 *
	 * @return A non-empty string which is unique within the plug-in registry.
	 */
	public static String getPluginId() {
		return getInstance().getBundle().getSymbolicName();
	}

}