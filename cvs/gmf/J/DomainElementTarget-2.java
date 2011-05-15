/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Element Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Target for rules applied on domain model elements
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.mappings.DomainElementTarget#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.mappings.GMFMapPackage#getDomainElementTarget()
 * @model
 * @generated
 */
public interface DomainElementTarget extends RuleTarget {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Targeted domain model element
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(EClass)
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getDomainElementTarget_Element()
	 * @model required="true"
	 * @generated
	 */
	EClass getElement();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.mappings.DomainElementTarget#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EClass value);

} // DomainElementTarget