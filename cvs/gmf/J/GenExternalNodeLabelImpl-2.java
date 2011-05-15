/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmf.codegen.gmfgen.ExternalLabel;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenExternalNodeLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen External Node Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenExternalNodeLabelImpl#getTextEditPartClassName <em>Text Edit Part Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenExternalNodeLabelImpl#getTextNotationViewFactoryClassName <em>Text Notation View Factory Class Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenExternalNodeLabelImpl extends GenNodeLabelImpl implements GenExternalNodeLabel {
	/**
	 * The default value of the '{@link #getTextEditPartClassName() <em>Text Edit Part Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextEditPartClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDIT_PART_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTextEditPartClassName() <em>Text Edit Part Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextEditPartClassName()
	 * @generated
	 * @ordered
	 */
	protected String textEditPartClassName = TEXT_EDIT_PART_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTextNotationViewFactoryClassName() <em>Text Notation View Factory Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextNotationViewFactoryClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTextNotationViewFactoryClassName() <em>Text Notation View Factory Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextNotationViewFactoryClassName()
	 * @generated
	 * @ordered
	 */
	protected String textNotationViewFactoryClassName = TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenExternalNodeLabelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GMFGenPackage.eINSTANCE.getGenExternalNodeLabel();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextEditPartClassNameGen() {
		return textEditPartClassName;
	}

	public String getTextEditPartClassName() {
		String value = getTextEditPartClassNameGen();
		if (isEmpty(value)) {
			value = getClassNamePart() + TEXT_EDIT_PART_SUFFIX;
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextEditPartClassName(String newTextEditPartClassName) {
		String oldTextEditPartClassName = textEditPartClassName;
		textEditPartClassName = newTextEditPartClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_EDIT_PART_CLASS_NAME, oldTextEditPartClassName, textEditPartClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextNotationViewFactoryClassNameGen() {
		return textNotationViewFactoryClassName;
	}

	public String getTextNotationViewFactoryClassName() {
		String value = getTextNotationViewFactoryClassNameGen();
		if (isEmpty(value)) {
			value = getClassNamePart() + TEXT_NOTATION_VIEW_FACTORY_SUFFIX;
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextNotationViewFactoryClassName(String newTextNotationViewFactoryClassName) {
		String oldTextNotationViewFactoryClassName = textNotationViewFactoryClassName;
		textNotationViewFactoryClassName = newTextNotationViewFactoryClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME, oldTextNotationViewFactoryClassName, textNotationViewFactoryClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getTextEditPartQualifiedClassName() {
		return getDiagram().getEditPartsPackageName() + '.' + getTextEditPartClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getTextNotationViewFactoryQualifiedClassName() {
		return getDiagram().getNotationViewFactoriesPackageName() + '.' + getTextNotationViewFactoryClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getSemanticHintLabelFieldName() {
		return GenCommonBaseImpl.asJavaConstantName(getUniqueIdentifier()) + "_LABEL"; //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_EDIT_PART_CLASS_NAME:
				return getTextEditPartClassName();
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME:
				return getTextNotationViewFactoryClassName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_EDIT_PART_CLASS_NAME:
				setTextEditPartClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME:
				setTextNotationViewFactoryClassName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_EDIT_PART_CLASS_NAME:
				setTextEditPartClassName(TEXT_EDIT_PART_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME:
				setTextNotationViewFactoryClassName(TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_EDIT_PART_CLASS_NAME:
				return TEXT_EDIT_PART_CLASS_NAME_EDEFAULT == null ? textEditPartClassName != null : !TEXT_EDIT_PART_CLASS_NAME_EDEFAULT.equals(textEditPartClassName);
			case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME:
				return TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME_EDEFAULT == null ? textNotationViewFactoryClassName != null : !TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME_EDEFAULT.equals(textNotationViewFactoryClassName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == ExternalLabel.class) {
			switch (derivedFeatureID) {
				case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_EDIT_PART_CLASS_NAME: return GMFGenPackage.EXTERNAL_LABEL__TEXT_EDIT_PART_CLASS_NAME;
				case GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME: return GMFGenPackage.EXTERNAL_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == ExternalLabel.class) {
			switch (baseFeatureID) {
				case GMFGenPackage.EXTERNAL_LABEL__TEXT_EDIT_PART_CLASS_NAME: return GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_EDIT_PART_CLASS_NAME;
				case GMFGenPackage.EXTERNAL_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME: return GMFGenPackage.GEN_EXTERNAL_NODE_LABEL__TEXT_NOTATION_VIEW_FACTORY_CLASS_NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (textEditPartClassName: ");
		result.append(textEditPartClassName);
		result.append(", textNotationViewFactoryClassName: ");
		result.append(textNotationViewFactoryClassName);
		result.append(')');
		return result.toString();
	}

	public String getClassNameSuffux() {
		return "ExternalNodeLabel";
	}

} //GenExternalNodeLabelImpl