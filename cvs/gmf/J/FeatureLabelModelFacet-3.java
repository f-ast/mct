/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Label Model Facet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Model facet of label based on an EStructuralFeature
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet#getDefaultText <em>Default Text</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet#getViewPattern <em>View Pattern</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet#getEditPattern <em>Edit Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.codegen.gmfgen.GMFGenPackage#getFeatureLabelModelFacet()
 * @model
 * @generated
 */
public interface FeatureLabelModelFacet extends FeatureModelFacet, LabelModelFacet {
	/**
	 * Returns the value of the '<em><b>Default Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default text that should be displayed when produced text is an empty string
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Text</em>' attribute.
	 * @see #setDefaultText(String)
	 * @see org.eclipse.gmf.codegen.gmfgen.GMFGenPackage#getFeatureLabelModelFacet_DefaultText()
	 * @model
	 * @generated
	 */
	String getDefaultText();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet#getDefaultText <em>Default Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Text</em>' attribute.
	 * @see #getDefaultText()
	 * @generated
	 */
	void setDefaultText(String value);

	/**
	 * Returns the value of the '<em><b>View Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Pattern for java.text.MessageFormat to produce label on diagram
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>View Pattern</em>' attribute.
	 * @see #setViewPattern(String)
	 * @see org.eclipse.gmf.codegen.gmfgen.GMFGenPackage#getFeatureLabelModelFacet_ViewPattern()
	 * @model
	 * @generated
	 */
	String getViewPattern();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet#getViewPattern <em>View Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Pattern</em>' attribute.
	 * @see #getViewPattern()
	 * @generated
	 */
	void setViewPattern(String value);

	/**
	 * Returns the value of the '<em><b>Edit Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Pattern for java.text.MessageFormat to produce text for inplace editor; if not specified then viewPattern should be used
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Edit Pattern</em>' attribute.
	 * @see #setEditPattern(String)
	 * @see org.eclipse.gmf.codegen.gmfgen.GMFGenPackage#getFeatureLabelModelFacet_EditPattern()
	 * @model
	 * @generated
	 */
	String getEditPattern();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet#getEditPattern <em>Edit Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edit Pattern</em>' attribute.
	 * @see #getEditPattern()
	 * @generated
	 */
	void setEditPattern(String value);

} // FeatureLabelModelFacet