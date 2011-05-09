/*
 * <copyright>
 *
 * Copyright (c) 2005-2006 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.editor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Manager for colors used in the Template editor
 */
public class ColorProvider {
    protected Map<RGB, Color> ivColorTable = new HashMap<RGB, Color>();

    /**
     * Release all of the color resources held onto by the receiver.
     */
    public void dispose() {
        final Iterator<Color> e = ivColorTable.values().iterator();
        while (e.hasNext()) {
            e.next().dispose();
        }
    }

    /**
     * Return the Color that is stored in the Color table as rgb. Create new
     * entry, if none can be found.
     * 
     * @param rgb
     *            RGB color to lookup from HashMap
     */
    public Color getColor(final RGB aRgb) {
        Color color = ivColorTable.get(aRgb);
        if (color == null) {
            color = new Color(Display.getCurrent(), aRgb);
            ivColorTable.put(aRgb, color);
        }
        return color;
    }
}
