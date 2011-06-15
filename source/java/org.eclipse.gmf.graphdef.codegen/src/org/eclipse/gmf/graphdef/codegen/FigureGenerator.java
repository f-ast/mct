/*
 * Copyright (c) 2006, 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.graphdef.codegen;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.common.UnexpectedBehaviourException;
import org.eclipse.gmf.gmfgraph.Figure;
import org.eclipse.gmf.gmfgraph.FigureDescriptor;
import org.eclipse.gmf.internal.common.codegen.TextEmitter;
import org.eclipse.gmf.internal.graphdef.codegen.Activator;
import org.eclipse.gmf.internal.xpand.BufferOutput;
import org.eclipse.gmf.internal.xpand.ResourceManager;
import org.eclipse.gmf.internal.xpand.XpandFacade;
import org.eclipse.gmf.internal.xpand.expression.Variable;
import org.eclipse.gmf.internal.xpand.util.ContextFactory;

public class FigureGenerator implements TextEmitter {

	private static final String VAR_MM_ACCESS = "mapModeAccessor";
	private static final String VAR_OUTPUT_FIELDS = "outputStaticFields";
	private static final String VAR_RT_TOKEN = "runtimeToken";

	private static final String SLOT_FIELDS = "staticFields";

	private final XpandFacade xpandFacade;

	private final StringBuilder result;

	private final boolean myIsInnerClassCode;

	private StringBuilder additionalFields;

	private String packageStatement;


	/**
	 * XXX consider using enum for runtimeToken
	 * @param runtimeToken either "full" or null to indicate full GMF runtime use, any other value is to be processed by custom templates 
	 * @param asInnerClass
	 */
	public FigureGenerator(String runtimeToken, String packageStmt, boolean asInnerClass) {
		this(runtimeToken, packageStmt, MapModeCodeGenStrategy.DYNAMIC, "getMapMode().", asInnerClass);
	}

	public FigureGenerator(String runtimeToken, String packageStmt, MapModeCodeGenStrategy mapModeStrategy, String mapModeAccessor, boolean asInnerClass) {
		this(runtimeToken, packageStmt, mapModeStrategy, mapModeAccessor, asInnerClass, null);
	}

	public FigureGenerator(String runtimeToken, String packageStmt, MapModeCodeGenStrategy mapModeStrategy, String mapModeAccessor, boolean asInnerClass, URL[] dynamicTemplates) {
		myIsInnerClassCode = asInnerClass;
		this.packageStatement = packageStmt;
		if (mapModeStrategy == MapModeCodeGenStrategy.STATIC) {
			if (mapModeAccessor != null && mapModeAccessor.trim().length() > 0) {
				throw new IllegalArgumentException("Can't use map mode accessor with identity map mode");
			}
		}
		final ArrayList<Variable> globals = new ArrayList<Variable>();
		if (mapModeStrategy == MapModeCodeGenStrategy.DYNAMIC) {
			globals.add(new Variable(VAR_MM_ACCESS, mapModeAccessor == null ? "" : mapModeAccessor));
		}
		if (runtimeToken != null) {
			globals.add(new Variable(VAR_RT_TOKEN, runtimeToken));
		}
		additionalFields = new StringBuilder();
		globals.add(new Variable(VAR_OUTPUT_FIELDS, "") {
			public Object getValue() {
				return additionalFields.toString();
			}
		});
		result = new StringBuilder(200);
		Map<String, StringBuilder> slots = new HashMap<String, StringBuilder>();
		slots.put(SLOT_FIELDS, additionalFields);
		BufferOutput bufferOutput = new BufferOutput(result, slots);

		ResourceManager resourceManager = Activator.createResourceEngine(mapModeStrategy, dynamicTemplates);
		xpandFacade = new XpandFacade(ContextFactory.createXpandContext(resourceManager, bufferOutput, globals, getClass().getClassLoader()));
	}

	public String getPackageName() {
		return packageStatement;
	}

	public String fqnSwitch(Figure figure) {
		result.setLength(0);
		additionalFields.setLength(0);
		xpandFacade.evaluate("Runtime::fqn", figure, null);
		return result.toString();
	}
	
	/**
	 * @param packageStmt can be null if asInnerClass was true
	 * @param figure
	 */
	public String go(FigureDescriptor figure) {
		result.setLength(0);
		additionalFields.setLength(0);
		if (myIsInnerClassCode) {
			xpandFacade.evaluate("top::Descriptor::Inner", figure, null);
		} else {
			xpandFacade.evaluate("top::Descriptor::Top", figure, new Object[] { packageStatement });
		}
		return result.toString();
	}

	public String generate(IProgressMonitor monitor, Object[] arguments) throws InterruptedException, InvocationTargetException, UnexpectedBehaviourException {
		if (arguments == null || arguments.length != 1 || false == arguments[0] instanceof FigureDescriptor) {
			throw new UnexpectedBehaviourException("(FigureDescriptor) expected as arguments, not " + arguments);
		}
		return go((FigureDescriptor) arguments[0]);
	}
}