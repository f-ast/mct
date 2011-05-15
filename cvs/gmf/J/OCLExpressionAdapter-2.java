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
package org.eclipse.gmf.internal.validate.ocl;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ocl.expressions.ExpressionsFactory;
import org.eclipse.emf.ocl.expressions.OCLExpression;
import org.eclipse.emf.ocl.expressions.Variable;
import org.eclipse.emf.ocl.expressions.util.ExpressionsUtil;
import org.eclipse.emf.ocl.parser.EcoreEnvironment;
import org.eclipse.emf.ocl.parser.EcoreEnvironmentFactory;
import org.eclipse.emf.ocl.parser.Environment;
import org.eclipse.emf.ocl.parser.EnvironmentFactory;
import org.eclipse.emf.ocl.parser.ParserException;
import org.eclipse.emf.ocl.query.Query;
import org.eclipse.emf.ocl.query.QueryFactory;
import org.eclipse.emf.ocl.types.util.Types;
import org.eclipse.gmf.internal.validate.DebugOptions;
import org.eclipse.gmf.internal.validate.DefUtils;
import org.eclipse.gmf.internal.validate.EDataTypeConversion;
import org.eclipse.gmf.internal.validate.GMFValidationPlugin;
import org.eclipse.gmf.internal.validate.Messages;
import org.eclipse.gmf.internal.validate.StatusCodes;
import org.eclipse.gmf.internal.validate.Trace;
import org.eclipse.gmf.internal.validate.expressions.AbstractExpression;
import org.eclipse.gmf.internal.validate.expressions.IEvaluationEnvironment;
import org.eclipse.gmf.internal.validate.expressions.IParseEnvironment;

public class OCLExpressionAdapter extends AbstractExpression {
	/**
	 * The OCL language identifier.
	 */
	public static final String OCL = "ocl"; //$NON-NLS-1$
	
	private Query query; 
	
	public OCLExpressionAdapter(String body, EClassifier context, IParseEnvironment extEnv) {
		super(body, context, extEnv);
		
		try {
			if(extEnv == null) {
				this.query = QueryFactory.eINSTANCE.createQuery(body, context);
			} else {
				EnvironmentFactory factory = extEnv.getImportRegistry() == null ?
						EnvironmentFactory.ECORE_INSTANCE : new EcoreEnvironmentFactory(extEnv.getImportRegistry());
				Environment env = factory.createClassifierContext(context);

				for (Iterator it = extEnv.getVariableNames().iterator(); it.hasNext();) {
					String varName = (String)it.next();
					EClassifier type = extEnv.getTypeOf(varName);				
					
					Variable varDecl = ExpressionsFactory.eINSTANCE.createVariable();
					varDecl.setName(varName);
					varDecl.setType(type);
					env.addElement(varDecl.getName(), varDecl, false);
				}
				OCLExpression oclExpression = ExpressionsUtil.createQuery(env, body, true);
				this.query = QueryFactory.eINSTANCE.createQuery(oclExpression);
			}
		} catch (ParserException e) {
			setInvalidOclExprStatus(e);
		} catch (IllegalArgumentException e) {
			setInvalidOclExprStatus(e);				
		} catch(RuntimeException e) {				
			setStatus(GMFValidationPlugin.createStatus(
					IStatus.ERROR, StatusCodes.UNEXPECTED_PARSE_ERROR, 
					Messages.unexpectedExprParseError, e));
			GMFValidationPlugin.log(getStatus());
			Trace.catching(DebugOptions.EXCEPTIONS_CATCHING, e);				
		}
	}
	
	public String getLanguage() {	
		return OCL;
	}
	
	public boolean isLooselyTyped() {	
		return false;
	}
	
	public boolean isAssignableTo(EClassifier ecoreType) {
		EClassifier oclType = EcoreEnvironment.getOCLType(ecoreType);
		if(oclType == null) {
			return false;
		}
		return isOclConformantTo(oclType);			
	}
	
	public boolean isAssignableToElement(ETypedElement typedElement) {
		EClassifier oclType = EcoreEnvironment.getOCLType(typedElement);
		if(oclType == null) {
			return false;
		}
		return isOclConformantTo(oclType);
	}
	
	public EClassifier getResultType() {	
		return (query != null) ? query.getExpression().getType() : super.getResultType();
	}
	
	protected Object doEvaluate(Object context) {
		return filterOCLInvalid((query != null) ? query.evaluate(context) : null);
	}
	
	protected Object doEvaluate(Object context, IEvaluationEnvironment extEnvironment) {
		// TODO - add custom variables !!!
		return doEvaluate(context);
	}
	
	private static Object filterOCLInvalid(Object object) {
		return object == Types.OCL_INVALID ? null : object;
	}
	
	boolean isOclConformantTo(EClassifier oclType) {
		EClassifier thisOclType = getResultType();
		// Note: in OCL, Double extends Integer
		if ((oclType.getInstanceClass() == Integer.class ||
			oclType.getInstanceClass() == int.class) && 
			(thisOclType.getInstanceClass() == Double.class || 
				thisOclType.getInstanceClass() == double.class)) {
			return true;
		}
		
		if(thisOclType instanceof EDataType && oclType instanceof EDataType) {
			if(EDataTypeConversion.isConvertable((EDataType)oclType, (EDataType)thisOclType)) {
				return true;
			}
		}		
		return DefUtils.checkTypeAssignmentCompatibility(oclType, thisOclType);			
	}
	
	void setInvalidOclExprStatus(Exception exception) {
		String message = MessageFormat.format(
				Messages.invalidExpressionBody, 
				new Object[] { getBody(), exception.getLocalizedMessage() });
		
		setStatus(GMFValidationPlugin.createStatus(
				IStatus.ERROR, StatusCodes.INVALID_VALUE_EXPRESSION, message, exception));
		Trace.catching(DebugOptions.EXCEPTIONS_CATCHING, exception);			
	}
}