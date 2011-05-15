/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *    Radek Dvorak (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.validate;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.gmf.internal.validate.expressions.AbstractExpression;
import org.eclipse.gmf.internal.validate.expressions.IEvaluationEnvironment;
import org.eclipse.gmf.internal.validate.expressions.IModelExpression;

public class ConstraintAdapter extends AbstractExpression {
	private IModelExpression expression;
	private EClassifier resultType;
	private int severity;
	private String description;
	
	public ConstraintAdapter(IModelExpression expression, int severity, String description) {
		super(expression.getBody(), expression.getContext(), null);
		this.expression = expression;
		this.severity = severity;
		this.description = description;
		if(getStatus().isOK()) {
			this.resultType = expression.getResultType();				
			if(this.resultType != null && !expression.isLooselyTyped()) { 
				if(!resultType.isInstance(Boolean.TRUE)) {
					String msg = MessageFormat.format(
							Messages.invalidConstraintExprType, 
							new Object[] { resultType.getName(), getBody() }); 
					setStatus(new Status(IStatus.ERROR, 
							GMFValidationPlugin.getPluginId(),
							StatusCodes.INVALID_EXPRESSION_TYPE, msg, null));
				}
			}			
		}
	}
		
	public int getSeverity() {
		return severity;
	}
		
	public String getDescription() {
		return description;
	}
	
	public IStatus getStatus() {
		if(expression.getStatus().isOK()) {
			return super.getStatus();
		}
		return expression.getStatus();
	}
	
	public String getLanguage() {	
		return expression.getLanguage();
	}
	
	public boolean isLooselyTyped() {
		return expression.isLooselyTyped();
	}
	
	public boolean isAssignableToElement(ETypedElement typedElement) {
		return expression.isAssignableToElement(typedElement);
	}
	
	public boolean isAssignableTo(EClassifier ecoreType) {
		return expression.isAssignableTo(ecoreType);		
	}
	
	public EClassifier getResultType() {
		return resultType;
	}
	
	protected Object doEvaluate(Object context) {
		return expression.evaluate(context);
	}

	protected Object doEvaluate(Object context, IEvaluationEnvironment extEnvironment) {
		return expression.evaluate(context, extEnvironment);
	}
	
	public boolean isSatisfied(EObject context) {
		Object value = evaluate(context);
		return (value instanceof Boolean) ? ((Boolean)value).booleanValue() : false;
	}
}