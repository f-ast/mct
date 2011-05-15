/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenExpressionProviderBaseImpl.java,v 1.9 2007/05/22 17:27:35 atikhomirov Exp $
 */
package org.eclipse.gmf.codegen.gmfgen.impl;

import java.util.Collection;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenExpressionProviderBase;
import org.eclipse.gmf.codegen.gmfgen.GenExpressionProviderContainer;
import org.eclipse.gmf.codegen.gmfgen.GenLanguage;
import org.eclipse.gmf.codegen.gmfgen.ValueExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Expression Provider Base</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenExpressionProviderBaseImpl#getExpressions <em>Expressions</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenExpressionProviderBaseImpl#getContainer <em>Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GenExpressionProviderBaseImpl extends EObjectImpl implements GenExpressionProviderBase {
	/**
	 * The cached value of the '{@link #getExpressions() <em>Expressions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressions()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueExpression> expressions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenExpressionProviderBaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GMFGenPackage.eINSTANCE.getGenExpressionProviderBase();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract GenLanguage getLanguage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */	
	public String getQualifiedInstanceClassName(GenClassifier genClassifier) {
		if(genClassifier instanceof GenClass) {
			return ((GenClass)genClassifier).getQualifiedInterfaceName();
		} else if(genClassifier instanceof GenDataType) {
			Class<?> clazz = genClassifier.getEcoreClassifier().getInstanceClass();
			if(clazz != null && clazz.isPrimitive()) {
				return EcoreUtil.wrapperClassFor(clazz).getName();
			}
			return ((GenDataType)genClassifier).getQualifiedInstanceClassName();
		}
		return "java.lang.Object"; //$NON-NLS-1$
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getQualifiedTypeInstanceClassName(GenTypedElement genTypedElement) {
		if(genTypedElement.isPrimitiveType() && !genTypedElement.isListType()) {
			return getQualifiedInstanceClassName(genTypedElement.getTypeGenClassifier());
		}
		String type = genTypedElement.getType();
		return (type != null) ? type : "java.lang.Object"; //$NON-NLS-1$
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueExpression> getExpressions() {
		if (expressions == null) {
			expressions = new EObjectResolvingEList<ValueExpression>(ValueExpression.class, this, GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__EXPRESSIONS);
		}
		return expressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenExpressionProviderContainer getContainer() {
		if (eContainerFeatureID != GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER) return null;
		return (GenExpressionProviderContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER:
				return eBasicSetContainer(null, GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER:
				return eInternalContainer().eInverseRemove(this, GMFGenPackage.GEN_EXPRESSION_PROVIDER_CONTAINER__PROVIDERS, GenExpressionProviderContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__EXPRESSIONS:
				return getExpressions();
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER:
				return getContainer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__EXPRESSIONS:
				getExpressions().clear();
				getExpressions().addAll((Collection<? extends ValueExpression>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__EXPRESSIONS:
				getExpressions().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__EXPRESSIONS:
				return expressions != null && !expressions.isEmpty();
			case GMFGenPackage.GEN_EXPRESSION_PROVIDER_BASE__CONTAINER:
				return getContainer() != null;
		}
		return super.eIsSet(featureID);
	}

} //GenExpressionProviderBaseImpl