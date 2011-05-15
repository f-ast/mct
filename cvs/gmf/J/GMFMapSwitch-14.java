/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.mappings.*;

import org.eclipse.gmf.mappings.AbstractNodeMapping;
import org.eclipse.gmf.mappings.AppearanceSteward;
import org.eclipse.gmf.mappings.AuditContainer;
import org.eclipse.gmf.mappings.AuditRule;
import org.eclipse.gmf.mappings.CanvasMapping;
import org.eclipse.gmf.mappings.ChildNodeMapping;
import org.eclipse.gmf.mappings.CompartmentMapping;
import org.eclipse.gmf.mappings.Constraint;
import org.eclipse.gmf.mappings.ElementInitializer;
import org.eclipse.gmf.mappings.FeatureSeqInitializer;
import org.eclipse.gmf.mappings.FeatureValueSpec;
import org.eclipse.gmf.mappings.GMFMapPackage;
import org.eclipse.gmf.mappings.LinkConstraints;
import org.eclipse.gmf.mappings.LinkMapping;
import org.eclipse.gmf.mappings.Mapping;
import org.eclipse.gmf.mappings.MappingEntry;
import org.eclipse.gmf.mappings.MenuOwner;
import org.eclipse.gmf.mappings.NodeMapping;
import org.eclipse.gmf.mappings.ToolOwner;
import org.eclipse.gmf.mappings.ValueExpression;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmf.mappings.GMFMapPackage
 * @generated
 */
public class GMFMapSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GMFMapPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GMFMapSwitch() {
		if (modelPackage == null) {
			modelPackage = GMFMapPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case GMFMapPackage.MAPPING: {
				Mapping mapping = (Mapping)theEObject;
				Object result = caseMapping(mapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.MAPPING_ENTRY: {
				MappingEntry mappingEntry = (MappingEntry)theEObject;
				Object result = caseMappingEntry(mappingEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.ABSTRACT_NODE_MAPPING: {
				AbstractNodeMapping abstractNodeMapping = (AbstractNodeMapping)theEObject;
				Object result = caseAbstractNodeMapping(abstractNodeMapping);
				if (result == null) result = caseMappingEntry(abstractNodeMapping);
				if (result == null) result = caseMenuOwner(abstractNodeMapping);
				if (result == null) result = caseToolOwner(abstractNodeMapping);
				if (result == null) result = caseAppearanceSteward(abstractNodeMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.NODE_MAPPING: {
				NodeMapping nodeMapping = (NodeMapping)theEObject;
				Object result = caseNodeMapping(nodeMapping);
				if (result == null) result = caseAbstractNodeMapping(nodeMapping);
				if (result == null) result = caseMappingEntry(nodeMapping);
				if (result == null) result = caseMenuOwner(nodeMapping);
				if (result == null) result = caseToolOwner(nodeMapping);
				if (result == null) result = caseAppearanceSteward(nodeMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.CHILD_NODE_MAPPING: {
				ChildNodeMapping childNodeMapping = (ChildNodeMapping)theEObject;
				Object result = caseChildNodeMapping(childNodeMapping);
				if (result == null) result = caseAbstractNodeMapping(childNodeMapping);
				if (result == null) result = caseMappingEntry(childNodeMapping);
				if (result == null) result = caseMenuOwner(childNodeMapping);
				if (result == null) result = caseToolOwner(childNodeMapping);
				if (result == null) result = caseAppearanceSteward(childNodeMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.COMPARTMENT_MAPPING: {
				CompartmentMapping compartmentMapping = (CompartmentMapping)theEObject;
				Object result = caseCompartmentMapping(compartmentMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.LINK_MAPPING: {
				LinkMapping linkMapping = (LinkMapping)theEObject;
				Object result = caseLinkMapping(linkMapping);
				if (result == null) result = caseMappingEntry(linkMapping);
				if (result == null) result = caseMenuOwner(linkMapping);
				if (result == null) result = caseToolOwner(linkMapping);
				if (result == null) result = caseAppearanceSteward(linkMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.CANVAS_MAPPING: {
				CanvasMapping canvasMapping = (CanvasMapping)theEObject;
				Object result = caseCanvasMapping(canvasMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.LABEL_MAPPING: {
				LabelMapping labelMapping = (LabelMapping)theEObject;
				Object result = caseLabelMapping(labelMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.CONSTRAINT: {
				Constraint constraint = (Constraint)theEObject;
				Object result = caseConstraint(constraint);
				if (result == null) result = caseValueExpression(constraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.LINK_CONSTRAINTS: {
				LinkConstraints linkConstraints = (LinkConstraints)theEObject;
				Object result = caseLinkConstraints(linkConstraints);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.VALUE_EXPRESSION: {
				ValueExpression valueExpression = (ValueExpression)theEObject;
				Object result = caseValueExpression(valueExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.ELEMENT_INITIALIZER: {
				ElementInitializer elementInitializer = (ElementInitializer)theEObject;
				Object result = caseElementInitializer(elementInitializer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.FEATURE_SEQ_INITIALIZER: {
				FeatureSeqInitializer featureSeqInitializer = (FeatureSeqInitializer)theEObject;
				Object result = caseFeatureSeqInitializer(featureSeqInitializer);
				if (result == null) result = caseElementInitializer(featureSeqInitializer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.FEATURE_VALUE_SPEC: {
				FeatureValueSpec featureValueSpec = (FeatureValueSpec)theEObject;
				Object result = caseFeatureValueSpec(featureValueSpec);
				if (result == null) result = caseValueExpression(featureValueSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.MENU_OWNER: {
				MenuOwner menuOwner = (MenuOwner)theEObject;
				Object result = caseMenuOwner(menuOwner);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.TOOL_OWNER: {
				ToolOwner toolOwner = (ToolOwner)theEObject;
				Object result = caseToolOwner(toolOwner);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.APPEARANCE_STEWARD: {
				AppearanceSteward appearanceSteward = (AppearanceSteward)theEObject;
				Object result = caseAppearanceSteward(appearanceSteward);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.AUDIT_CONTAINER: {
				AuditContainer auditContainer = (AuditContainer)theEObject;
				Object result = caseAuditContainer(auditContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GMFMapPackage.AUDIT_RULE: {
				AuditRule auditRule = (AuditRule)theEObject;
				Object result = caseAuditRule(auditRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Abstract Node Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Abstract Node Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAbstractNodeMapping(AbstractNodeMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Mapping Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Mapping Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMappingEntry(MappingEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Node Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Node Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseNodeMapping(NodeMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Child Node Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Child Node Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseChildNodeMapping(ChildNodeMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Compartment Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Compartment Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCompartmentMapping(CompartmentMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Link Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Link Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLinkMapping(LinkMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Canvas Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Canvas Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCanvasMapping(CanvasMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Label Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Label Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLabelMapping(LabelMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMapping(Mapping object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstraint(Constraint object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Link Constraints</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Link Constraints</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLinkConstraints(LinkConstraints object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Value Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Value Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseValueExpression(ValueExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Element Initializer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Element Initializer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseElementInitializer(ElementInitializer object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Feature Seq Initializer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Feature Seq Initializer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFeatureSeqInitializer(FeatureSeqInitializer object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Feature Value Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Feature Value Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFeatureValueSpec(FeatureValueSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Menu Owner</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Menu Owner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMenuOwner(MenuOwner object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Tool Owner</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Tool Owner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseToolOwner(ToolOwner object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Appearance Steward</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Appearance Steward</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAppearanceSteward(AppearanceSteward object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Audit Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Audit Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAuditContainer(AuditContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Audit Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Audit Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAuditRule(AuditRule object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //GMFMapSwitch