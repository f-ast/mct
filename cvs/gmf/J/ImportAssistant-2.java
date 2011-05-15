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
package org.eclipse.gmf.common.codegen;


/**
 * @author artem
 */
public interface ImportAssistant {
	void markImportLocation(StringBuffer stringBuffer);
	void emitSortedImports();
	String getImportedName(String qualifiedName);
	void addImport(String qualifiedName);
}