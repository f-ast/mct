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
package org.eclipse.gmf.internal.xpand.editor.scan;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.WhitespaceRule;

/**
 * @author Sven Efftinge
 * 
 * 
 */
public class CommentScanner extends AbstractXpandRuleBasedScanner {

    public CommentScanner() {
        final IRule[] rules = new IRule[1];
        // Add generic whitespace rule.
        rules[0] = new WhitespaceRule(new WhitespaceDetector());

        setRules(rules);
        setDefaultReturnToken(comment);
    }
}