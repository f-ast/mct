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
 *    Dmitry Stadnik (Borland) - delegate to chained starategy when unable to get name from model facet
 */
package org.eclipse.gmf.internal.bridge.naming;

import java.util.Iterator;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.gmf.codegen.gmfgen.GenCompartment;
import org.eclipse.gmf.codegen.gmfgen.GenLabel;
import org.eclipse.gmf.codegen.gmfgen.GenLink;
import org.eclipse.gmf.codegen.gmfgen.GenNode;
import org.eclipse.gmf.common.IncrementalNamesDispenser;
import org.eclipse.gmf.common.NamesDispenser;
import org.eclipse.gmf.mappings.CanvasMapping;
import org.eclipse.gmf.mappings.CompartmentMapping;
import org.eclipse.gmf.mappings.LabelMapping;
import org.eclipse.gmf.mappings.LinkMapping;
import org.eclipse.gmf.mappings.MappingEntry;
import org.eclipse.gmf.mappings.NodeMapping;

/**
 * Generates names based on model facet identifiers.
 * Should be consistent with genmodel naming strategy
 * and GenCommonBase.getClassNamePrefix() in particular.
 * 
 * @author artem
 */
public class ClassNamingStrategy extends AbstractNamingStrategy {

	private final NamesDispenser namesDispenser;
	
	private final String suffix;

	public ClassNamingStrategy(String suffix) {
		this(suffix, null, new IncrementalNamesDispenser());
	}

	public ClassNamingStrategy(String suffix, NamingStrategy chained, NamesDispenser namesDispenser) {
		super(chained);
		assert suffix != null;
		this.suffix = suffix;
		assert namesDispenser != null;
		this.namesDispenser = namesDispenser;
	}

	public String get(CanvasMapping mapping) {
		if (mapping.getDomainMetaElement() != null) {
			String name = mapping.getDomainMetaElement().getName();
			if (!isEmpty(name)) {
				return createClassName(name);
			}
		}
		return super.get(mapping);
	}

	public String get(NodeMapping mapping) {
		if (mapping.getDomainMetaElement() != null) {
			String name = mapping.getDomainMetaElement().getName();
			if (!isEmpty(name)) {
				return createClassName(name);
			}
		}
		return super.get(mapping);
	}

	public String getAsPrefix(NodeMapping mapping) {
		if (mapping.getDomainMetaElement() != null) {
			String name = mapping.getDomainMetaElement().getName();
			if (!isEmpty(name)) {
				return getValidClassName(name);
			}
		}
		return GenNode.CLASS_NAME_PREFIX;
	}

	public String get(LinkMapping mapping) {
		if (mapping.getDomainMetaElement() != null) {
			String name = mapping.getDomainMetaElement().getName();
			if (!isEmpty(name)) {
				return createClassName(name);
			}
		}
		if (mapping.getLinkMetaFeature() != null) {
			String name = mapping.getLinkMetaFeature().getName();
			if (!isEmpty(name)) {
				name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
				name = mapping.getLinkMetaFeature().getEContainingClass().getName() + name;
				return createClassName(name);
			}
		}
		return super.get(mapping);
	}

	public String getAsPrefix(LinkMapping mapping) {
		if (mapping.getDomainMetaElement() != null) {
			String name = mapping.getDomainMetaElement().getName();
			if (!isEmpty(name)) {
				return getValidClassName(name);
			}
		}
		if (mapping.getLinkMetaFeature() != null) {
			String name = mapping.getLinkMetaFeature().getName();
			if (!isEmpty(name)) {
				name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
				name = mapping.getLinkMetaFeature().getEContainingClass().getName() + name;
				return getValidClassName(name);
			}
		}
		return GenLink.CLASS_NAME_PREFIX;
	}

	public String get(CompartmentMapping mapping) {
		// TODO : move to design naming strategy and allow to pass node name as prefix
		if (mapping.getCompartment() != null) {
			String name = mapping.getCompartment().getName();
			if (!isEmpty(name)) {
				name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
				return createClassName(getAsPrefix(mapping.getParentNode()) + name);
			}
		}
		return createClassName(getAsPrefix(mapping.getParentNode()) + GenCompartment.CLASS_NAME_PREFIX);
	}

	public String get(LabelMapping mapping) {
		// TODO : allow to use host/label name parts from chained strategy
		StringBuffer sb = new StringBuffer();
		for (Iterator features = mapping.getFeatures().iterator(); features.hasNext();) {
			EAttribute feature = (EAttribute) features.next();
			String name = feature.getName();
			if (!isEmpty(name)) {
				name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
				sb.append(name);
			}
		}
		String hostName;
		MappingEntry parentMapping = mapping.getMapEntry();
		if (parentMapping instanceof NodeMapping) {
			hostName = getAsPrefix((NodeMapping) parentMapping);
		} else if (parentMapping instanceof LinkMapping) {
			hostName = getAsPrefix((LinkMapping) parentMapping);
		} else {
			throw new IllegalArgumentException(String.valueOf(mapping));
		}
		return createClassName(hostName + (sb.length() > 0 ? sb.toString() : GenLabel.CLASS_NAME_PREFIX));
	}

	protected String createClassName(String name) {
		assert !isEmpty(name);
		name = CodeGenUtil.validJavaIdentifier(name);
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return namesDispenser.get(name, suffix);
	}

	protected static String getValidClassName(String s) {
		assert !isEmpty(s);
		s = CodeGenUtil.validJavaIdentifier(s);
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}
}