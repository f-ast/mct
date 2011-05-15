/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.bridge.naming.gen;

import org.eclipse.gmf.codegen.gmfgen.GenChildNode;
import org.eclipse.gmf.codegen.gmfgen.GenCompartment;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.gmf.codegen.gmfgen.GenLink;
import org.eclipse.gmf.codegen.gmfgen.GenLinkLabel;
import org.eclipse.gmf.codegen.gmfgen.GenNodeLabel;
import org.eclipse.gmf.codegen.gmfgen.GenTopLevelNode;
import org.eclipse.gmf.mappings.AbstractNodeMapping;
import org.eclipse.gmf.mappings.CanvasMapping;
import org.eclipse.gmf.mappings.ChildNodeMapping;
import org.eclipse.gmf.mappings.CompartmentMapping;
import org.eclipse.gmf.mappings.LinkLabelMapping;
import org.eclipse.gmf.mappings.LinkMapping;
import org.eclipse.gmf.mappings.NodeLabelMapping;
import org.eclipse.gmf.mappings.NodeMapping;

/**
 * @author artem
 */
public interface GenModelNamingMediator {

	void feed(GenDiagram genDiagram, CanvasMapping cme);

	void feed(GenTopLevelNode genNode, NodeMapping nme);

	void feed(GenChildNode childNode, ChildNodeMapping nme);

	void feed(GenLink genLink, LinkMapping lme);

	void feed(GenCompartment genCompartment, CompartmentMapping mapping);

	void feed(GenNodeLabel label, AbstractNodeMapping mapping, NodeLabelMapping labelMapping);

	void feed(GenLinkLabel label, LinkMapping mapping, LinkLabelMapping labelMapping);

}