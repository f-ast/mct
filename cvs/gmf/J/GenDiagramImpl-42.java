/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenAuditContainer;
import org.eclipse.gmf.codegen.gmfgen.GenChildContainer;
import org.eclipse.gmf.codegen.gmfgen.GenChildNode;
import org.eclipse.gmf.codegen.gmfgen.GenCompartment;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.gmf.codegen.gmfgen.GenLink;
import org.eclipse.gmf.codegen.gmfgen.GenNode;
import org.eclipse.gmf.codegen.gmfgen.GenTopLevelNode;
import org.eclipse.gmf.codegen.gmfgen.LinkModelFacet;
import org.eclipse.gmf.codegen.gmfgen.Palette;
import org.eclipse.gmf.codegen.gmfgen.TypeLinkModelFacet;
import org.eclipse.gmf.codegen.gmfgen.TypeModelFacet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getContainedNodes <em>Contained Nodes</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getDomainMetaModel <em>Domain Meta Model</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getDomainDiagramElement <em>Domain Diagram Element</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getTopLevelNodes <em>Top Level Nodes</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getCompartments <em>Compartments</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getAudits <em>Audits</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getPalette <em>Palette</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getEditCommandsPackageName <em>Edit Commands Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getEditPartsPackageName <em>Edit Parts Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getEditPoliciesPackageName <em>Edit Policies Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getEditorPackageName <em>Editor Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getProvidersPackageName <em>Providers Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getNotationViewFactoriesPackageName <em>Notation View Factories Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getPluginID <em>Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getPluginName <em>Plugin Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getProviderName <em>Provider Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#isSameFileForDiagramAndModel <em>Same File For Diagram And Model</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#isPrintingEnabled <em>Printing Enabled</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getDiagramFileExtension <em>Diagram File Extension</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getReorientConnectionViewCommandClassName <em>Reorient Connection View Command Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getEditPartFactoryClassName <em>Edit Part Factory Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getBaseExternalNodeLabelEditPartClassName <em>Base External Node Label Edit Part Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getBaseItemSemanticEditPolicyClassName <em>Base Item Semantic Edit Policy Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getBaseGraphicalNodeEditPolicyClassName <em>Base Graphical Node Edit Policy Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getReferenceConnectionEditPolicyClassName <em>Reference Connection Edit Policy Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getCanonicalEditPolicyClassName <em>Canonical Edit Policy Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getElementTypesClassName <em>Element Types Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getSemanticHintsClassName <em>Semantic Hints Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getNotationViewProviderClassName <em>Notation View Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getEditPartProviderClassName <em>Edit Part Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getMetamodelSupportProviderClassName <em>Metamodel Support Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getModelingAssistantProviderClassName <em>Modeling Assistant Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getPropertyProviderClassName <em>Property Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getIconProviderClassName <em>Icon Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getStructuralFeatureParserClassName <em>Structural Feature Parser Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getActionBarContributorClassName <em>Action Bar Contributor Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getCreationWizardClassName <em>Creation Wizard Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getCreationWizardPageClassName <em>Creation Wizard Page Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getDiagramEditorUtilClassName <em>Diagram Editor Util Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getDiagramFileCreatorClassName <em>Diagram File Creator Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getDocumentProviderClassName <em>Document Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getEditorClassName <em>Editor Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getInitDiagramFileActionClassName <em>Init Diagram File Action Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getMatchingStrategyClassName <em>Matching Strategy Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getPluginClassName <em>Plugin Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getPreferenceInitializerClassName <em>Preference Initializer Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getVisualIDRegistryClassName <em>Visual ID Registry Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getCreateShortcutActionClassName <em>Create Shortcut Action Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getElementChooserClassName <em>Element Chooser Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getContainsShortcutsTo <em>Contains Shortcuts To</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenDiagramImpl#getShortcutsProvidedFor <em>Shortcuts Provided For</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenDiagramImpl extends GenCommonBaseImpl implements GenDiagram {

	/**
	 * The cached value of the '{@link #getDomainMetaModel() <em>Domain Meta Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainMetaModel()
	 * @generated
	 * @ordered
	 */
	protected GenPackage domainMetaModel = null;

	/**
	 * The cached value of the '{@link #getDomainDiagramElement() <em>Domain Diagram Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainDiagramElement()
	 * @generated
	 * @ordered
	 */
	protected GenClass domainDiagramElement = null;

	/**
	 * The cached value of the '{@link #getChildNodes() <em>Child Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildNodes()
	 * @generated
	 * @ordered
	 */
	protected EList childNodes = null;

	/**
	 * The cached value of the '{@link #getTopLevelNodes() <em>Top Level Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopLevelNodes()
	 * @generated
	 * @ordered
	 */
	protected EList topLevelNodes = null;

	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
	protected EList links = null;

	/**
	 * The cached value of the '{@link #getCompartments() <em>Compartments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompartments()
	 * @generated
	 * @ordered
	 */
	protected EList compartments = null;

	/**
	 * The cached value of the '{@link #getAudits() <em>Audits</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAudits()
	 * @generated
	 * @ordered
	 */
	protected GenAuditContainer audits = null;

	/**
	 * The cached value of the '{@link #getPalette() <em>Palette</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPalette()
	 * @generated
	 * @ordered
	 */
	protected Palette palette = null;

	/**
	 * The default value of the '{@link #getEditCommandsPackageName() <em>Edit Commands Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditCommandsPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_COMMANDS_PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditCommandsPackageName() <em>Edit Commands Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditCommandsPackageName()
	 * @generated
	 * @ordered
	 */
	protected String editCommandsPackageName = EDIT_COMMANDS_PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditPartsPackageName() <em>Edit Parts Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPartsPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_PARTS_PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditPartsPackageName() <em>Edit Parts Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPartsPackageName()
	 * @generated
	 * @ordered
	 */
	protected String editPartsPackageName = EDIT_PARTS_PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditPoliciesPackageName() <em>Edit Policies Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPoliciesPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_POLICIES_PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditPoliciesPackageName() <em>Edit Policies Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPoliciesPackageName()
	 * @generated
	 * @ordered
	 */
	protected String editPoliciesPackageName = EDIT_POLICIES_PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditorPackageName() <em>Editor Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDITOR_PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditorPackageName() <em>Editor Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorPackageName()
	 * @generated
	 * @ordered
	 */
	protected String editorPackageName = EDITOR_PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getProvidersPackageName() <em>Providers Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidersPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDERS_PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProvidersPackageName() <em>Providers Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidersPackageName()
	 * @generated
	 * @ordered
	 */
	protected String providersPackageName = PROVIDERS_PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNotationViewFactoriesPackageName() <em>Notation View Factories Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotationViewFactoriesPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTATION_VIEW_FACTORIES_PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotationViewFactoriesPackageName() <em>Notation View Factories Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotationViewFactoriesPackageName()
	 * @generated
	 * @ordered
	 */
	protected String notationViewFactoriesPackageName = NOTATION_VIEW_FACTORIES_PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPluginID() <em>Plugin ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginID()
	 * @generated
	 * @ordered
	 */
	protected static final String PLUGIN_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPluginID() <em>Plugin ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginID()
	 * @generated
	 * @ordered
	 */
	protected String pluginID = PLUGIN_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPluginName() <em>Plugin Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginName()
	 * @generated
	 * @ordered
	 */
	protected static final String PLUGIN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPluginName() <em>Plugin Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginName()
	 * @generated
	 * @ordered
	 */
	protected String pluginName = PLUGIN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getProviderName() <em>Provider Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_NAME_EDEFAULT = "Sample Plugin Provider, Inc";

	/**
	 * The cached value of the '{@link #getProviderName() <em>Provider Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProviderName()
	 * @generated
	 * @ordered
	 */
	protected String providerName = PROVIDER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isSameFileForDiagramAndModel() <em>Same File For Diagram And Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSameFileForDiagramAndModel()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SAME_FILE_FOR_DIAGRAM_AND_MODEL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSameFileForDiagramAndModel() <em>Same File For Diagram And Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSameFileForDiagramAndModel()
	 * @generated
	 * @ordered
	 */
	protected boolean sameFileForDiagramAndModel = SAME_FILE_FOR_DIAGRAM_AND_MODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrintingEnabled() <em>Printing Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrintingEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRINTING_ENABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrintingEnabled() <em>Printing Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrintingEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean printingEnabled = PRINTING_ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getDiagramFileExtension() <em>Diagram File Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramFileExtension()
	 * @generated
	 * @ordered
	 */
	protected static final String DIAGRAM_FILE_EXTENSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDiagramFileExtension() <em>Diagram File Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramFileExtension()
	 * @generated
	 * @ordered
	 */
	protected String diagramFileExtension = DIAGRAM_FILE_EXTENSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getReorientConnectionViewCommandClassName() <em>Reorient Connection View Command Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReorientConnectionViewCommandClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReorientConnectionViewCommandClassName() <em>Reorient Connection View Command Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReorientConnectionViewCommandClassName()
	 * @generated
	 * @ordered
	 */
	protected String reorientConnectionViewCommandClassName = REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditPartFactoryClassName() <em>Edit Part Factory Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPartFactoryClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_PART_FACTORY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditPartFactoryClassName() <em>Edit Part Factory Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPartFactoryClassName()
	 * @generated
	 * @ordered
	 */
	protected String editPartFactoryClassName = EDIT_PART_FACTORY_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getBaseExternalNodeLabelEditPartClassName() <em>Base External Node Label Edit Part Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseExternalNodeLabelEditPartClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseExternalNodeLabelEditPartClassName() <em>Base External Node Label Edit Part Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseExternalNodeLabelEditPartClassName()
	 * @generated
	 * @ordered
	 */
	protected String baseExternalNodeLabelEditPartClassName = BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getBaseItemSemanticEditPolicyClassName() <em>Base Item Semantic Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseItemSemanticEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseItemSemanticEditPolicyClassName() <em>Base Item Semantic Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseItemSemanticEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected String baseItemSemanticEditPolicyClassName = BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getBaseGraphicalNodeEditPolicyClassName() <em>Base Graphical Node Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseGraphicalNodeEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseGraphicalNodeEditPolicyClassName() <em>Base Graphical Node Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseGraphicalNodeEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected String baseGraphicalNodeEditPolicyClassName = BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getReferenceConnectionEditPolicyClassName() <em>Reference Connection Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceConnectionEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferenceConnectionEditPolicyClassName() <em>Reference Connection Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceConnectionEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected String referenceConnectionEditPolicyClassName = REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCanonicalEditPolicyClassName() <em>Canonical Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCanonicalEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CANONICAL_EDIT_POLICY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCanonicalEditPolicyClassName() <em>Canonical Edit Policy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCanonicalEditPolicyClassName()
	 * @generated
	 * @ordered
	 */
	protected String canonicalEditPolicyClassName = CANONICAL_EDIT_POLICY_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementTypesClassName() <em>Element Types Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementTypesClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_TYPES_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementTypesClassName() <em>Element Types Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementTypesClassName()
	 * @generated
	 * @ordered
	 */
	protected String elementTypesClassName = ELEMENT_TYPES_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSemanticHintsClassName() <em>Semantic Hints Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSemanticHintsClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String SEMANTIC_HINTS_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSemanticHintsClassName() <em>Semantic Hints Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSemanticHintsClassName()
	 * @generated
	 * @ordered
	 */
	protected String semanticHintsClassName = SEMANTIC_HINTS_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNotationViewProviderClassName() <em>Notation View Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotationViewProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTATION_VIEW_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotationViewProviderClassName() <em>Notation View Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotationViewProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String notationViewProviderClassName = NOTATION_VIEW_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditPartProviderClassName() <em>Edit Part Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPartProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_PART_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditPartProviderClassName() <em>Edit Part Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPartProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String editPartProviderClassName = EDIT_PART_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMetamodelSupportProviderClassName() <em>Metamodel Support Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodelSupportProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String METAMODEL_SUPPORT_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetamodelSupportProviderClassName() <em>Metamodel Support Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodelSupportProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String metamodelSupportProviderClassName = METAMODEL_SUPPORT_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelingAssistantProviderClassName() <em>Modeling Assistant Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelingAssistantProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODELING_ASSISTANT_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModelingAssistantProviderClassName() <em>Modeling Assistant Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelingAssistantProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String modelingAssistantProviderClassName = MODELING_ASSISTANT_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPropertyProviderClassName() <em>Property Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROPERTY_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPropertyProviderClassName() <em>Property Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String propertyProviderClassName = PROPERTY_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getIconProviderClassName() <em>Icon Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIconProviderClassName() <em>Icon Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String iconProviderClassName = ICON_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getStructuralFeatureParserClassName() <em>Structural Feature Parser Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructuralFeatureParserClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String STRUCTURAL_FEATURE_PARSER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStructuralFeatureParserClassName() <em>Structural Feature Parser Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructuralFeatureParserClassName()
	 * @generated
	 * @ordered
	 */
	protected String structuralFeatureParserClassName = STRUCTURAL_FEATURE_PARSER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getActionBarContributorClassName() <em>Action Bar Contributor Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionBarContributorClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActionBarContributorClassName() <em>Action Bar Contributor Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionBarContributorClassName()
	 * @generated
	 * @ordered
	 */
	protected String actionBarContributorClassName = ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationWizardClassName() <em>Creation Wizard Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationWizardClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATION_WIZARD_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationWizardClassName() <em>Creation Wizard Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationWizardClassName()
	 * @generated
	 * @ordered
	 */
	protected String creationWizardClassName = CREATION_WIZARD_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationWizardPageClassName() <em>Creation Wizard Page Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationWizardPageClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATION_WIZARD_PAGE_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationWizardPageClassName() <em>Creation Wizard Page Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationWizardPageClassName()
	 * @generated
	 * @ordered
	 */
	protected String creationWizardPageClassName = CREATION_WIZARD_PAGE_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDiagramEditorUtilClassName() <em>Diagram Editor Util Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramEditorUtilClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String DIAGRAM_EDITOR_UTIL_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDiagramEditorUtilClassName() <em>Diagram Editor Util Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramEditorUtilClassName()
	 * @generated
	 * @ordered
	 */
	protected String diagramEditorUtilClassName = DIAGRAM_EDITOR_UTIL_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDiagramFileCreatorClassName() <em>Diagram File Creator Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramFileCreatorClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String DIAGRAM_FILE_CREATOR_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDiagramFileCreatorClassName() <em>Diagram File Creator Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramFileCreatorClassName()
	 * @generated
	 * @ordered
	 */
	protected String diagramFileCreatorClassName = DIAGRAM_FILE_CREATOR_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDocumentProviderClassName() <em>Document Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENT_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDocumentProviderClassName() <em>Document Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String documentProviderClassName = DOCUMENT_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditorClassName() <em>Editor Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDITOR_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditorClassName() <em>Editor Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorClassName()
	 * @generated
	 * @ordered
	 */
	protected String editorClassName = EDITOR_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitDiagramFileActionClassName() <em>Init Diagram File Action Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitDiagramFileActionClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String INIT_DIAGRAM_FILE_ACTION_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitDiagramFileActionClassName() <em>Init Diagram File Action Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitDiagramFileActionClassName()
	 * @generated
	 * @ordered
	 */
	protected String initDiagramFileActionClassName = INIT_DIAGRAM_FILE_ACTION_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMatchingStrategyClassName() <em>Matching Strategy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchingStrategyClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String MATCHING_STRATEGY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMatchingStrategyClassName() <em>Matching Strategy Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchingStrategyClassName()
	 * @generated
	 * @ordered
	 */
	protected String matchingStrategyClassName = MATCHING_STRATEGY_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPluginClassName() <em>Plugin Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String PLUGIN_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPluginClassName() <em>Plugin Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginClassName()
	 * @generated
	 * @ordered
	 */
	protected String pluginClassName = PLUGIN_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreferenceInitializerClassName() <em>Preference Initializer Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferenceInitializerClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFERENCE_INITIALIZER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferenceInitializerClassName() <em>Preference Initializer Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferenceInitializerClassName()
	 * @generated
	 * @ordered
	 */
	protected String preferenceInitializerClassName = PREFERENCE_INITIALIZER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVisualIDRegistryClassName() <em>Visual ID Registry Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualIDRegistryClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String VISUAL_ID_REGISTRY_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVisualIDRegistryClassName() <em>Visual ID Registry Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualIDRegistryClassName()
	 * @generated
	 * @ordered
	 */
	protected String visualIDRegistryClassName = VISUAL_ID_REGISTRY_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreateShortcutActionClassName() <em>Create Shortcut Action Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateShortcutActionClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATE_SHORTCUT_ACTION_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreateShortcutActionClassName() <em>Create Shortcut Action Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateShortcutActionClassName()
	 * @generated
	 * @ordered
	 */
	protected String createShortcutActionClassName = CREATE_SHORTCUT_ACTION_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementChooserClassName() <em>Element Chooser Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementChooserClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_CHOOSER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementChooserClassName() <em>Element Chooser Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementChooserClassName()
	 * @generated
	 * @ordered
	 */
	protected String elementChooserClassName = ELEMENT_CHOOSER_CLASS_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContainsShortcutsTo() <em>Contains Shortcuts To</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainsShortcutsTo()
	 * @generated
	 * @ordered
	 */
	protected EList containsShortcutsTo = null;

	/**
	 * The cached value of the '{@link #getShortcutsProvidedFor() <em>Shortcuts Provided For</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortcutsProvidedFor()
	 * @generated
	 * @ordered
	 */
	protected EList shortcutsProvidedFor = null;

	private static final String DIAGRAM_EDITOR_TOKEN = "gmf.editor";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenDiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GMFGenPackage.eINSTANCE.getGenDiagram();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getContainedNodes() {
		return getTopLevelNodes();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenPackage getDomainMetaModel() {
		if (domainMetaModel != null && domainMetaModel.eIsProxy()) {
			InternalEObject oldDomainMetaModel = (InternalEObject)domainMetaModel;
			domainMetaModel = (GenPackage)eResolveProxy(oldDomainMetaModel);
			if (domainMetaModel != oldDomainMetaModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GMFGenPackage.GEN_DIAGRAM__DOMAIN_META_MODEL, oldDomainMetaModel, domainMetaModel));
			}
		}
		return domainMetaModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenPackage basicGetDomainMetaModel() {
		return domainMetaModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainMetaModel(GenPackage newDomainMetaModel) {
		GenPackage oldDomainMetaModel = domainMetaModel;
		domainMetaModel = newDomainMetaModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__DOMAIN_META_MODEL, oldDomainMetaModel, domainMetaModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenClass getDomainDiagramElement() {
		if (domainDiagramElement != null && domainDiagramElement.eIsProxy()) {
			InternalEObject oldDomainDiagramElement = (InternalEObject)domainDiagramElement;
			domainDiagramElement = (GenClass)eResolveProxy(oldDomainDiagramElement);
			if (domainDiagramElement != oldDomainDiagramElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GMFGenPackage.GEN_DIAGRAM__DOMAIN_DIAGRAM_ELEMENT, oldDomainDiagramElement, domainDiagramElement));
			}
		}
		return domainDiagramElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenClass basicGetDomainDiagramElement() {
		return domainDiagramElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainDiagramElement(GenClass newDomainDiagramElement) {
		GenClass oldDomainDiagramElement = domainDiagramElement;
		domainDiagramElement = newDomainDiagramElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__DOMAIN_DIAGRAM_ELEMENT, oldDomainDiagramElement, domainDiagramElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getChildNodes() {
		if (childNodes == null) {
			childNodes = new EObjectContainmentWithInverseEList(GenChildNode.class, this, GMFGenPackage.GEN_DIAGRAM__CHILD_NODES, GMFGenPackage.GEN_CHILD_NODE__DIAGRAM);
		}
		return childNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTopLevelNodes() {
		if (topLevelNodes == null) {
			topLevelNodes = new EObjectContainmentWithInverseEList(GenTopLevelNode.class, this, GMFGenPackage.GEN_DIAGRAM__TOP_LEVEL_NODES, GMFGenPackage.GEN_TOP_LEVEL_NODE__DIAGRAM);
		}
		return topLevelNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLinks() {
		if (links == null) {
			links = new EObjectContainmentWithInverseEList(GenLink.class, this, GMFGenPackage.GEN_DIAGRAM__LINKS, GMFGenPackage.GEN_LINK__DIAGRAM);
		}
		return links;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCompartments() {
		if (compartments == null) {
			compartments = new EObjectContainmentWithInverseEList(GenCompartment.class, this, GMFGenPackage.GEN_DIAGRAM__COMPARTMENTS, GMFGenPackage.GEN_COMPARTMENT__DIAGRAM);
		}
		return compartments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenAuditContainer getAudits() {
		return audits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAudits(GenAuditContainer newAudits, NotificationChain msgs) {
		GenAuditContainer oldAudits = audits;
		audits = newAudits;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__AUDITS, oldAudits, newAudits);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAudits(GenAuditContainer newAudits) {
		if (newAudits != audits) {
			NotificationChain msgs = null;
			if (audits != null)
				msgs = ((InternalEObject)audits).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GMFGenPackage.GEN_DIAGRAM__AUDITS, null, msgs);
			if (newAudits != null)
				msgs = ((InternalEObject)newAudits).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GMFGenPackage.GEN_DIAGRAM__AUDITS, null, msgs);
			msgs = basicSetAudits(newAudits, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__AUDITS, newAudits, newAudits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Palette getPalette() {
		return palette;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPalette(Palette newPalette, NotificationChain msgs) {
		Palette oldPalette = palette;
		palette = newPalette;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PALETTE, oldPalette, newPalette);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPalette(Palette newPalette) {
		if (newPalette != palette) {
			NotificationChain msgs = null;
			if (palette != null)
				msgs = ((InternalEObject)palette).eInverseRemove(this, GMFGenPackage.PALETTE__DIAGRAM, Palette.class, msgs);
			if (newPalette != null)
				msgs = ((InternalEObject)newPalette).eInverseAdd(this, GMFGenPackage.PALETTE__DIAGRAM, Palette.class, msgs);
			msgs = basicSetPalette(newPalette, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PALETTE, newPalette, newPalette));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditCommandsPackageNameGen() {
		return editCommandsPackageName;
	}

	public String getEditCommandsPackageName() {
		String value = getEditCommandsPackageNameGen();
		if (isEmpty(value)) {
			value = getPackageNamePrefix() + ".edit.commands";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditCommandsPackageName(String newEditCommandsPackageName) {
		String oldEditCommandsPackageName = editCommandsPackageName;
		editCommandsPackageName = newEditCommandsPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__EDIT_COMMANDS_PACKAGE_NAME, oldEditCommandsPackageName, editCommandsPackageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditPartsPackageNameGen() {
		return editPartsPackageName;
	}

	public String getEditPartsPackageName() {
		String value = getEditPartsPackageNameGen();
		if (isEmpty(value)) {
			value = getPackageNamePrefix() + ".edit.parts";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditPartsPackageName(String newEditPartsPackageName) {
		String oldEditPartsPackageName = editPartsPackageName;
		editPartsPackageName = newEditPartsPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__EDIT_PARTS_PACKAGE_NAME, oldEditPartsPackageName, editPartsPackageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditPoliciesPackageNameGen() {
		return editPoliciesPackageName;
	}

	public String getEditPoliciesPackageName() {
		String value = getEditPoliciesPackageNameGen();
		if (isEmpty(value)) {
			value = getPackageNamePrefix() + ".edit.policies";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditPoliciesPackageName(String newEditPoliciesPackageName) {
		String oldEditPoliciesPackageName = editPoliciesPackageName;
		editPoliciesPackageName = newEditPoliciesPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__EDIT_POLICIES_PACKAGE_NAME, oldEditPoliciesPackageName, editPoliciesPackageName));
	}

	public String getClassNamePrefix() {
		String prefix;
		if (getDomainDiagramElement() != null) {
			prefix = getDomainDiagramElement().getName();
		} else {
			char[] v = getDomainMetaModel().getEcorePackage().getName().toCharArray();
			v[0] = Character.toUpperCase(v[0]);
			prefix = new String(v);
		}
		return prefix;
	}

	public String getClassNameSuffux() {
		return "Diagram";
	}

	public GenDiagram getDiagram() {
		return this;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPluginName() {
		return pluginName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPluginName(String newPluginName) {
		String oldPluginName = pluginName;
		pluginName = newPluginName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PLUGIN_NAME, oldPluginName, pluginName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProviderName(String newProviderName) {
		String oldProviderName = providerName;
		providerName = newProviderName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PROVIDER_NAME, oldProviderName, providerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPluginIDGen() {
		return pluginID;
	}

	public String getPluginID() {
		String value = getPluginIDGen();
		if (value == null || value.length() == 0) {
			return getEMFGenModel().getModelPluginID() + '.' + DIAGRAM_EDITOR_TOKEN;
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPluginID(String newPluginID) {
		String oldPluginID = pluginID;
		pluginID = newPluginID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PLUGIN_ID, oldPluginID, pluginID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPluginClassNameGen() {
		return pluginClassName;
	}

	public String getPluginClassName() {
		String value = getPluginClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "DiagramEditorPlugin";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPluginClassName(String newPluginClassName) {
		String oldPluginClassName = pluginClassName;
		pluginClassName = newPluginClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PLUGIN_CLASS_NAME, oldPluginClassName, pluginClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPreferenceInitializerClassNameGen() {
		return preferenceInitializerClassName;
	}

	public String getPreferenceInitializerClassName() {
		String value = getPreferenceInitializerClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "DiagramPreferenceInitializer"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferenceInitializerClassName(String newPreferenceInitializerClassName) {
		String oldPreferenceInitializerClassName = preferenceInitializerClassName;
		preferenceInitializerClassName = newPreferenceInitializerClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PREFERENCE_INITIALIZER_CLASS_NAME, oldPreferenceInitializerClassName, preferenceInitializerClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVisualIDRegistryClassNameGen() {
		return visualIDRegistryClassName;
	}

	public String getVisualIDRegistryClassName() {
		String value = getVisualIDRegistryClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "VisualIDRegistry"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisualIDRegistryClassName(String newVisualIDRegistryClassName) {
		String oldVisualIDRegistryClassName = visualIDRegistryClassName;
		visualIDRegistryClassName = newVisualIDRegistryClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__VISUAL_ID_REGISTRY_CLASS_NAME, oldVisualIDRegistryClassName, visualIDRegistryClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreateShortcutActionClassNameGen() {
		return createShortcutActionClassName;
	}

	public String getCreateShortcutActionClassName() {
		String value = getCreateShortcutActionClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "CreateShortcutAction"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateShortcutActionClassName(String newCreateShortcutActionClassName) {
		String oldCreateShortcutActionClassName = createShortcutActionClassName;
		createShortcutActionClassName = newCreateShortcutActionClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__CREATE_SHORTCUT_ACTION_CLASS_NAME, oldCreateShortcutActionClassName, createShortcutActionClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementChooserClassNameGen() {
		return elementChooserClassName;
	}

	public String getElementChooserClassName() {
		String value = getElementChooserClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "ElementChooserDialog"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementChooserClassName(String newElementChooserClassName) {
		String oldElementChooserClassName = elementChooserClassName;
		elementChooserClassName = newElementChooserClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__ELEMENT_CHOOSER_CLASS_NAME, oldElementChooserClassName, elementChooserClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContainsShortcutsTo() {
		if (containsShortcutsTo == null) {
			containsShortcutsTo = new EDataTypeUniqueEList(String.class, this, GMFGenPackage.GEN_DIAGRAM__CONTAINS_SHORTCUTS_TO);
		}
		return containsShortcutsTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getShortcutsProvidedFor() {
		if (shortcutsProvidedFor == null) {
			shortcutsProvidedFor = new EDataTypeUniqueEList(String.class, this, GMFGenPackage.GEN_DIAGRAM__SHORTCUTS_PROVIDED_FOR);
		}
		return shortcutsProvidedFor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditorPackageNameGen() {
		return editorPackageName;
	}

	public String getEditorPackageName() {
		String value = getEditorPackageNameGen();
		if (isEmpty(value)) {
			value = getPackageNamePrefix() + ".part";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditorPackageName(String newEditorPackageName) {
		String oldEditorPackageName = editorPackageName;
		editorPackageName = newEditorPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__EDITOR_PACKAGE_NAME, oldEditorPackageName, editorPackageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProvidersPackageNameGen() {
		return providersPackageName;
	}

	public String getProvidersPackageName() {
		String value = getProvidersPackageNameGen();
		if (isEmpty(value)) {
			value = getPackageNamePrefix() + ".providers";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvidersPackageName(String newProvidersPackageName) {
		String oldProvidersPackageName = providersPackageName;
		providersPackageName = newProvidersPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PROVIDERS_PACKAGE_NAME, oldProvidersPackageName, providersPackageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotationViewFactoriesPackageNameGen() {
		return notationViewFactoriesPackageName;
	}

	public String getNotationViewFactoriesPackageName() {
		String value = getNotationViewFactoriesPackageNameGen();
		if (isEmpty(value)) {
			value = getPackageNamePrefix() + ".view.factories";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotationViewFactoriesPackageName(String newNotationViewFactoriesPackageName) {
		String oldNotationViewFactoriesPackageName = notationViewFactoriesPackageName;
		notationViewFactoriesPackageName = newNotationViewFactoriesPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_FACTORIES_PACKAGE_NAME, oldNotationViewFactoriesPackageName, notationViewFactoriesPackageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditorClassNameGen() {
		return editorClassName;
	}

	public String getEditorClassName() {
		String value = getEditorClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "DiagramEditor"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditorClassName(String newEditorClassName) {
		String oldEditorClassName = editorClassName;
		editorClassName = newEditorClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__EDITOR_CLASS_NAME, oldEditorClassName, editorClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentProviderClassNameGen() {
		return documentProviderClassName;
	}

	public String getDocumentProviderClassName() {
		String value = getDocumentProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "DocumentProvider"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentProviderClassName(String newDocumentProviderClassName) {
		String oldDocumentProviderClassName = documentProviderClassName;
		documentProviderClassName = newDocumentProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__DOCUMENT_PROVIDER_CLASS_NAME, oldDocumentProviderClassName, documentProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditPartFactoryClassNameGen() {
		return editPartFactoryClassName;
	}

	public String getEditPartFactoryClassName() {
		String value = getEditPartFactoryClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "EditPartFactory"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditPartFactoryClassName(String newEditPartFactoryClassName) {
		String oldEditPartFactoryClassName = editPartFactoryClassName;
		editPartFactoryClassName = newEditPartFactoryClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__EDIT_PART_FACTORY_CLASS_NAME, oldEditPartFactoryClassName, editPartFactoryClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBaseExternalNodeLabelEditPartClassNameGen() {
		return baseExternalNodeLabelEditPartClassName;
	}

	public String getBaseExternalNodeLabelEditPartClassName() {
		String value = getBaseExternalNodeLabelEditPartClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "ExternalNodeLabelEditPart"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseExternalNodeLabelEditPartClassName(String newBaseExternalNodeLabelEditPartClassName) {
		String oldBaseExternalNodeLabelEditPartClassName = baseExternalNodeLabelEditPartClassName;
		baseExternalNodeLabelEditPartClassName = newBaseExternalNodeLabelEditPartClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME, oldBaseExternalNodeLabelEditPartClassName, baseExternalNodeLabelEditPartClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBaseItemSemanticEditPolicyClassNameGen() {
		return baseItemSemanticEditPolicyClassName;
	}

	public String getBaseItemSemanticEditPolicyClassName() {
		String value = getBaseItemSemanticEditPolicyClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "BaseItemSemanticEditPolicy"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseItemSemanticEditPolicyClassName(String newBaseItemSemanticEditPolicyClassName) {
		String oldBaseItemSemanticEditPolicyClassName = baseItemSemanticEditPolicyClassName;
		baseItemSemanticEditPolicyClassName = newBaseItemSemanticEditPolicyClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME, oldBaseItemSemanticEditPolicyClassName, baseItemSemanticEditPolicyClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBaseGraphicalNodeEditPolicyClassNameGen() {
		return baseGraphicalNodeEditPolicyClassName;
	}

	public String getBaseGraphicalNodeEditPolicyClassName() {
		String value = getBaseGraphicalNodeEditPolicyClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "GraphicalNodeEditPolicy"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseGraphicalNodeEditPolicyClassName(String newBaseGraphicalNodeEditPolicyClassName) {
		String oldBaseGraphicalNodeEditPolicyClassName = baseGraphicalNodeEditPolicyClassName;
		baseGraphicalNodeEditPolicyClassName = newBaseGraphicalNodeEditPolicyClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME, oldBaseGraphicalNodeEditPolicyClassName, baseGraphicalNodeEditPolicyClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReferenceConnectionEditPolicyClassNameGen() {
		return referenceConnectionEditPolicyClassName;
	}

	public String getReferenceConnectionEditPolicyClassName() {
		String value = getReferenceConnectionEditPolicyClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "ReferenceConnectionEditPolicy"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceConnectionEditPolicyClassName(String newReferenceConnectionEditPolicyClassName) {
		String oldReferenceConnectionEditPolicyClassName = referenceConnectionEditPolicyClassName;
		referenceConnectionEditPolicyClassName = newReferenceConnectionEditPolicyClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME, oldReferenceConnectionEditPolicyClassName, referenceConnectionEditPolicyClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCanonicalEditPolicyClassNameGen() {
		return canonicalEditPolicyClassName;
	}

	public String getCanonicalEditPolicyClassName() {
		String value = getCanonicalEditPolicyClassNameGen();
		if (isEmpty(value)) {
			value = getClassNamePart() + GenChildContainer.CANONICAL_EDIT_POLICY_SUFFIX;
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanonicalEditPolicyClassName(String newCanonicalEditPolicyClassName) {
		String oldCanonicalEditPolicyClassName = canonicalEditPolicyClassName;
		canonicalEditPolicyClassName = newCanonicalEditPolicyClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__CANONICAL_EDIT_POLICY_CLASS_NAME, oldCanonicalEditPolicyClassName, canonicalEditPolicyClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementTypesClassNameGen() {
		return elementTypesClassName;
	}

	public String getElementTypesClassName() {
		String value = getElementTypesClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "ElementTypes"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementTypesClassName(String newElementTypesClassName) {
		String oldElementTypesClassName = elementTypesClassName;
		elementTypesClassName = newElementTypesClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__ELEMENT_TYPES_CLASS_NAME, oldElementTypesClassName, elementTypesClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSemanticHintsClassNameGen() {
		return semanticHintsClassName;
	}

	public String getSemanticHintsClassName() {
		String value = getSemanticHintsClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "SemanticHints"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSemanticHintsClassName(String newSemanticHintsClassName) {
		String oldSemanticHintsClassName = semanticHintsClassName;
		semanticHintsClassName = newSemanticHintsClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__SEMANTIC_HINTS_CLASS_NAME, oldSemanticHintsClassName, semanticHintsClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotationViewProviderClassNameGen() {
		return notationViewProviderClassName;
	}

	public String getNotationViewProviderClassName() {
		String value = getNotationViewProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "ViewProvider"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotationViewProviderClassName(String newNotationViewProviderClassName) {
		String oldNotationViewProviderClassName = notationViewProviderClassName;
		notationViewProviderClassName = newNotationViewProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_PROVIDER_CLASS_NAME, oldNotationViewProviderClassName, notationViewProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDiagramFileExtensionGen() {
		return diagramFileExtension;
	}

	public String getDiagramFileExtension() {
		String value = getDiagramFileExtensionGen();
		if (value == null || value.length() == 0) {
			return getEMFGenModel().getModelName().toLowerCase() + "_diagram";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagramFileExtension(String newDiagramFileExtension) {
		String oldDiagramFileExtension = diagramFileExtension;
		diagramFileExtension = newDiagramFileExtension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_EXTENSION, oldDiagramFileExtension, diagramFileExtension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReorientConnectionViewCommandClassNameGen() {
		return reorientConnectionViewCommandClassName;
	}

	public String getReorientConnectionViewCommandClassName() {
		String value = getReorientConnectionViewCommandClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "ReorientConnectionViewCommand"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReorientConnectionViewCommandClassName(String newReorientConnectionViewCommandClassName) {
		String oldReorientConnectionViewCommandClassName = reorientConnectionViewCommandClassName;
		reorientConnectionViewCommandClassName = newReorientConnectionViewCommandClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME, oldReorientConnectionViewCommandClassName, reorientConnectionViewCommandClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSameFileForDiagramAndModel() {
		return sameFileForDiagramAndModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSameFileForDiagramAndModel(boolean newSameFileForDiagramAndModel) {
		boolean oldSameFileForDiagramAndModel = sameFileForDiagramAndModel;
		sameFileForDiagramAndModel = newSameFileForDiagramAndModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__SAME_FILE_FOR_DIAGRAM_AND_MODEL, oldSameFileForDiagramAndModel, sameFileForDiagramAndModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrintingEnabled() {
		return printingEnabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrintingEnabled(boolean newPrintingEnabled) {
		boolean oldPrintingEnabled = printingEnabled;
		printingEnabled = newPrintingEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PRINTING_ENABLED, oldPrintingEnabled, printingEnabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInitDiagramFileActionClassNameGen() {
		return initDiagramFileActionClassName;
	}

	public String getInitDiagramFileActionClassName() {
		String value = getInitDiagramFileActionClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "InitDiagramFileAction"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitDiagramFileActionClassName(String newInitDiagramFileActionClassName) {
		String oldInitDiagramFileActionClassName = initDiagramFileActionClassName;
		initDiagramFileActionClassName = newInitDiagramFileActionClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__INIT_DIAGRAM_FILE_ACTION_CLASS_NAME, oldInitDiagramFileActionClassName, initDiagramFileActionClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActionBarContributorClassNameGen() {
		return actionBarContributorClassName;
	}

	public String getActionBarContributorClassName() {
		String value = getActionBarContributorClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "DiagramActionBarContributor"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionBarContributorClassName(String newActionBarContributorClassName) {
		String oldActionBarContributorClassName = actionBarContributorClassName;
		actionBarContributorClassName = newActionBarContributorClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__ACTION_BAR_CONTRIBUTOR_CLASS_NAME, oldActionBarContributorClassName, actionBarContributorClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreationWizardClassNameGen() {
		return creationWizardClassName;
	}

	public String getCreationWizardClassName() {
		String value = getCreationWizardClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "CreationWizard"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationWizardClassName(String newCreationWizardClassName) {
		String oldCreationWizardClassName = creationWizardClassName;
		creationWizardClassName = newCreationWizardClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_CLASS_NAME, oldCreationWizardClassName, creationWizardClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreationWizardPageClassNameGen() {
		return creationWizardPageClassName;
	}

	public String getCreationWizardPageClassName() {
		String value = getCreationWizardPageClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "CreationWizardPage"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationWizardPageClassName(String newCreationWizardPageClassName) {
		String oldCreationWizardPageClassName = creationWizardPageClassName;
		creationWizardPageClassName = newCreationWizardPageClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_PAGE_CLASS_NAME, oldCreationWizardPageClassName, creationWizardPageClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDiagramEditorUtilClassNameGen() {
		return diagramEditorUtilClassName;
	}

	public String getDiagramEditorUtilClassName() {
		String value = getDiagramEditorUtilClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "DiagramEditorUtil"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagramEditorUtilClassName(String newDiagramEditorUtilClassName) {
		String oldDiagramEditorUtilClassName = diagramEditorUtilClassName;
		diagramEditorUtilClassName = newDiagramEditorUtilClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__DIAGRAM_EDITOR_UTIL_CLASS_NAME, oldDiagramEditorUtilClassName, diagramEditorUtilClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDiagramFileCreatorClassNameGen() {
		return diagramFileCreatorClassName;
	}

	public String getDiagramFileCreatorClassName() {
		String value = getDiagramFileCreatorClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "DiagramFileCreator"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagramFileCreatorClassName(String newDiagramFileCreatorClassName) {
		String oldDiagramFileCreatorClassName = diagramFileCreatorClassName;
		diagramFileCreatorClassName = newDiagramFileCreatorClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_CREATOR_CLASS_NAME, oldDiagramFileCreatorClassName, diagramFileCreatorClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMatchingStrategyClassNameGen() {
		return matchingStrategyClassName;
	}

	public String getMatchingStrategyClassName() {
		String value = getMatchingStrategyClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "MatchingStrategy"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMatchingStrategyClassName(String newMatchingStrategyClassName) {
		String oldMatchingStrategyClassName = matchingStrategyClassName;
		matchingStrategyClassName = newMatchingStrategyClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__MATCHING_STRATEGY_CLASS_NAME, oldMatchingStrategyClassName, matchingStrategyClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditPartProviderClassNameGen() {
		return editPartProviderClassName;
	}

	public String getEditPartProviderClassName() {
		String value = getEditPartProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "EditPartProvider"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditPartProviderClassName(String newEditPartProviderClassName) {
		String oldEditPartProviderClassName = editPartProviderClassName;
		editPartProviderClassName = newEditPartProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__EDIT_PART_PROVIDER_CLASS_NAME, oldEditPartProviderClassName, editPartProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMetamodelSupportProviderClassNameGen() {
		return metamodelSupportProviderClassName;
	}

	public String getMetamodelSupportProviderClassName() {
		String value = getMetamodelSupportProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "MetaModelSupportProvider"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetamodelSupportProviderClassName(String newMetamodelSupportProviderClassName) {
		String oldMetamodelSupportProviderClassName = metamodelSupportProviderClassName;
		metamodelSupportProviderClassName = newMetamodelSupportProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__METAMODEL_SUPPORT_PROVIDER_CLASS_NAME, oldMetamodelSupportProviderClassName, metamodelSupportProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModelingAssistantProviderClassNameGen() {
		return modelingAssistantProviderClassName;
	}

	public String getModelingAssistantProviderClassName() {
		String value = getModelingAssistantProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "ModelingAssistantProvider"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelingAssistantProviderClassName(String newModelingAssistantProviderClassName) {
		String oldModelingAssistantProviderClassName = modelingAssistantProviderClassName;
		modelingAssistantProviderClassName = newModelingAssistantProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__MODELING_ASSISTANT_PROVIDER_CLASS_NAME, oldModelingAssistantProviderClassName, modelingAssistantProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPropertyProviderClassNameGen() {
		return propertyProviderClassName;
	}

	public String getPropertyProviderClassName() {
		String value = getPropertyProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "PropertyProvider"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyProviderClassName(String newPropertyProviderClassName) {
		String oldPropertyProviderClassName = propertyProviderClassName;
		propertyProviderClassName = newPropertyProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__PROPERTY_PROVIDER_CLASS_NAME, oldPropertyProviderClassName, propertyProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIconProviderClassNameGen() {
		return iconProviderClassName;
	}

	public String getIconProviderClassName() {
		String value = getIconProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "IconProvider"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIconProviderClassName(String newIconProviderClassName) {
		String oldIconProviderClassName = iconProviderClassName;
		iconProviderClassName = newIconProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__ICON_PROVIDER_CLASS_NAME, oldIconProviderClassName, iconProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStructuralFeatureParserClassNameGen() {
		return structuralFeatureParserClassName;
	}

	public String getStructuralFeatureParserClassName() {
		String value = getStructuralFeatureParserClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "StructuralFeatureParser"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStructuralFeatureParserClassName(String newStructuralFeatureParserClassName) {
		String oldStructuralFeatureParserClassName = structuralFeatureParserClassName;
		structuralFeatureParserClassName = newStructuralFeatureParserClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_DIAGRAM__STRUCTURAL_FEATURE_PARSER_CLASS_NAME, oldStructuralFeatureParserClassName, structuralFeatureParserClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public GenModel getEMFGenModel() {
		return getDomainMetaModel().getGenModel();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getAllNodes() {
		EList result = new BasicEList();
		result.addAll(getTopLevelNodes());
		result.addAll(getChildNodes());
		return new BasicEList.UnmodifiableEList(result.size(), result.toArray());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getAllChildContainers() {
		EList result = new BasicEList();
		result.addAll(getAllNodes());
		result.addAll(getCompartments());
		return new BasicEList.UnmodifiableEList(result.size(), result.toArray());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getAllContainers() {
		EList result = new BasicEList();
		result.addAll(getAllChildContainers());
		result.add(this);
		return new BasicEList.UnmodifiableEList(result.size(), result.toArray());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getPluginQualifiedClassName() {
		return getEditorPackageName() + '.' + getPluginClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getPreferenceInitializerQualifiedClassName() {
		return getEditorPackageName() + '.' + getPreferenceInitializerClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getVisualIDRegistryQualifiedClassName() {
		return getEditorPackageName() + '.' + getVisualIDRegistryClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getCreateShortcutActionQualifiedClassName() {
		return getEditorPackageName() + '.' + getCreateShortcutActionClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getElementChooserQualifiedClassName() {
		return getEditorPackageName() + '.' + getElementChooserClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean generateCreateShortcutAction() {
		return getContainsShortcutsTo().size() > 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean generateShortcutIcon() {
		return getShortcutsProvidedFor().size() > 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.GEN_DIAGRAM__CHILD_NODES:
				return ((InternalEList)getChildNodes()).basicAdd(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__TOP_LEVEL_NODES:
				return ((InternalEList)getTopLevelNodes()).basicAdd(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__LINKS:
				return ((InternalEList)getLinks()).basicAdd(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__COMPARTMENTS:
				return ((InternalEList)getCompartments()).basicAdd(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__PALETTE:
				if (palette != null)
					msgs = ((InternalEObject)palette).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GMFGenPackage.GEN_DIAGRAM__PALETTE, null, msgs);
				return basicSetPalette((Palette)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.GEN_DIAGRAM__CHILD_NODES:
				return ((InternalEList)getChildNodes()).basicRemove(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__TOP_LEVEL_NODES:
				return ((InternalEList)getTopLevelNodes()).basicRemove(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__LINKS:
				return ((InternalEList)getLinks()).basicRemove(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__COMPARTMENTS:
				return ((InternalEList)getCompartments()).basicRemove(otherEnd, msgs);
			case GMFGenPackage.GEN_DIAGRAM__AUDITS:
				return basicSetAudits(null, msgs);
			case GMFGenPackage.GEN_DIAGRAM__PALETTE:
				return basicSetPalette(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GMFGenPackage.GEN_DIAGRAM__CONTAINED_NODES:
				return getContainedNodes();
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_META_MODEL:
				if (resolve) return getDomainMetaModel();
				return basicGetDomainMetaModel();
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_DIAGRAM_ELEMENT:
				if (resolve) return getDomainDiagramElement();
				return basicGetDomainDiagramElement();
			case GMFGenPackage.GEN_DIAGRAM__CHILD_NODES:
				return getChildNodes();
			case GMFGenPackage.GEN_DIAGRAM__TOP_LEVEL_NODES:
				return getTopLevelNodes();
			case GMFGenPackage.GEN_DIAGRAM__LINKS:
				return getLinks();
			case GMFGenPackage.GEN_DIAGRAM__COMPARTMENTS:
				return getCompartments();
			case GMFGenPackage.GEN_DIAGRAM__AUDITS:
				return getAudits();
			case GMFGenPackage.GEN_DIAGRAM__PALETTE:
				return getPalette();
			case GMFGenPackage.GEN_DIAGRAM__EDIT_COMMANDS_PACKAGE_NAME:
				return getEditCommandsPackageName();
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PARTS_PACKAGE_NAME:
				return getEditPartsPackageName();
			case GMFGenPackage.GEN_DIAGRAM__EDIT_POLICIES_PACKAGE_NAME:
				return getEditPoliciesPackageName();
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_PACKAGE_NAME:
				return getEditorPackageName();
			case GMFGenPackage.GEN_DIAGRAM__PROVIDERS_PACKAGE_NAME:
				return getProvidersPackageName();
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_FACTORIES_PACKAGE_NAME:
				return getNotationViewFactoriesPackageName();
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_ID:
				return getPluginID();
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_NAME:
				return getPluginName();
			case GMFGenPackage.GEN_DIAGRAM__PROVIDER_NAME:
				return getProviderName();
			case GMFGenPackage.GEN_DIAGRAM__SAME_FILE_FOR_DIAGRAM_AND_MODEL:
				return isSameFileForDiagramAndModel() ? Boolean.TRUE : Boolean.FALSE;
			case GMFGenPackage.GEN_DIAGRAM__PRINTING_ENABLED:
				return isPrintingEnabled() ? Boolean.TRUE : Boolean.FALSE;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_EXTENSION:
				return getDiagramFileExtension();
			case GMFGenPackage.GEN_DIAGRAM__REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME:
				return getReorientConnectionViewCommandClassName();
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_FACTORY_CLASS_NAME:
				return getEditPartFactoryClassName();
			case GMFGenPackage.GEN_DIAGRAM__BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME:
				return getBaseExternalNodeLabelEditPartClassName();
			case GMFGenPackage.GEN_DIAGRAM__BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME:
				return getBaseItemSemanticEditPolicyClassName();
			case GMFGenPackage.GEN_DIAGRAM__BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME:
				return getBaseGraphicalNodeEditPolicyClassName();
			case GMFGenPackage.GEN_DIAGRAM__REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME:
				return getReferenceConnectionEditPolicyClassName();
			case GMFGenPackage.GEN_DIAGRAM__CANONICAL_EDIT_POLICY_CLASS_NAME:
				return getCanonicalEditPolicyClassName();
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_TYPES_CLASS_NAME:
				return getElementTypesClassName();
			case GMFGenPackage.GEN_DIAGRAM__SEMANTIC_HINTS_CLASS_NAME:
				return getSemanticHintsClassName();
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_PROVIDER_CLASS_NAME:
				return getNotationViewProviderClassName();
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_PROVIDER_CLASS_NAME:
				return getEditPartProviderClassName();
			case GMFGenPackage.GEN_DIAGRAM__METAMODEL_SUPPORT_PROVIDER_CLASS_NAME:
				return getMetamodelSupportProviderClassName();
			case GMFGenPackage.GEN_DIAGRAM__MODELING_ASSISTANT_PROVIDER_CLASS_NAME:
				return getModelingAssistantProviderClassName();
			case GMFGenPackage.GEN_DIAGRAM__PROPERTY_PROVIDER_CLASS_NAME:
				return getPropertyProviderClassName();
			case GMFGenPackage.GEN_DIAGRAM__ICON_PROVIDER_CLASS_NAME:
				return getIconProviderClassName();
			case GMFGenPackage.GEN_DIAGRAM__STRUCTURAL_FEATURE_PARSER_CLASS_NAME:
				return getStructuralFeatureParserClassName();
			case GMFGenPackage.GEN_DIAGRAM__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				return getActionBarContributorClassName();
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_CLASS_NAME:
				return getCreationWizardClassName();
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_PAGE_CLASS_NAME:
				return getCreationWizardPageClassName();
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_EDITOR_UTIL_CLASS_NAME:
				return getDiagramEditorUtilClassName();
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_CREATOR_CLASS_NAME:
				return getDiagramFileCreatorClassName();
			case GMFGenPackage.GEN_DIAGRAM__DOCUMENT_PROVIDER_CLASS_NAME:
				return getDocumentProviderClassName();
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_CLASS_NAME:
				return getEditorClassName();
			case GMFGenPackage.GEN_DIAGRAM__INIT_DIAGRAM_FILE_ACTION_CLASS_NAME:
				return getInitDiagramFileActionClassName();
			case GMFGenPackage.GEN_DIAGRAM__MATCHING_STRATEGY_CLASS_NAME:
				return getMatchingStrategyClassName();
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_CLASS_NAME:
				return getPluginClassName();
			case GMFGenPackage.GEN_DIAGRAM__PREFERENCE_INITIALIZER_CLASS_NAME:
				return getPreferenceInitializerClassName();
			case GMFGenPackage.GEN_DIAGRAM__VISUAL_ID_REGISTRY_CLASS_NAME:
				return getVisualIDRegistryClassName();
			case GMFGenPackage.GEN_DIAGRAM__CREATE_SHORTCUT_ACTION_CLASS_NAME:
				return getCreateShortcutActionClassName();
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_CHOOSER_CLASS_NAME:
				return getElementChooserClassName();
			case GMFGenPackage.GEN_DIAGRAM__CONTAINS_SHORTCUTS_TO:
				return getContainsShortcutsTo();
			case GMFGenPackage.GEN_DIAGRAM__SHORTCUTS_PROVIDED_FOR:
				return getShortcutsProvidedFor();
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
			case GMFGenPackage.GEN_DIAGRAM__CONTAINED_NODES:
				getContainedNodes().clear();
				getContainedNodes().addAll((Collection)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_META_MODEL:
				setDomainMetaModel((GenPackage)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_DIAGRAM_ELEMENT:
				setDomainDiagramElement((GenClass)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CHILD_NODES:
				getChildNodes().clear();
				getChildNodes().addAll((Collection)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__TOP_LEVEL_NODES:
				getTopLevelNodes().clear();
				getTopLevelNodes().addAll((Collection)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__COMPARTMENTS:
				getCompartments().clear();
				getCompartments().addAll((Collection)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__AUDITS:
				setAudits((GenAuditContainer)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PALETTE:
				setPalette((Palette)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_COMMANDS_PACKAGE_NAME:
				setEditCommandsPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PARTS_PACKAGE_NAME:
				setEditPartsPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_POLICIES_PACKAGE_NAME:
				setEditPoliciesPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_PACKAGE_NAME:
				setEditorPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PROVIDERS_PACKAGE_NAME:
				setProvidersPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_FACTORIES_PACKAGE_NAME:
				setNotationViewFactoriesPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_ID:
				setPluginID((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_NAME:
				setPluginName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PROVIDER_NAME:
				setProviderName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__SAME_FILE_FOR_DIAGRAM_AND_MODEL:
				setSameFileForDiagramAndModel(((Boolean)newValue).booleanValue());
				return;
			case GMFGenPackage.GEN_DIAGRAM__PRINTING_ENABLED:
				setPrintingEnabled(((Boolean)newValue).booleanValue());
				return;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_EXTENSION:
				setDiagramFileExtension((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME:
				setReorientConnectionViewCommandClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_FACTORY_CLASS_NAME:
				setEditPartFactoryClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME:
				setBaseExternalNodeLabelEditPartClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME:
				setBaseItemSemanticEditPolicyClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME:
				setBaseGraphicalNodeEditPolicyClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME:
				setReferenceConnectionEditPolicyClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CANONICAL_EDIT_POLICY_CLASS_NAME:
				setCanonicalEditPolicyClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_TYPES_CLASS_NAME:
				setElementTypesClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__SEMANTIC_HINTS_CLASS_NAME:
				setSemanticHintsClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_PROVIDER_CLASS_NAME:
				setNotationViewProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_PROVIDER_CLASS_NAME:
				setEditPartProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__METAMODEL_SUPPORT_PROVIDER_CLASS_NAME:
				setMetamodelSupportProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__MODELING_ASSISTANT_PROVIDER_CLASS_NAME:
				setModelingAssistantProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PROPERTY_PROVIDER_CLASS_NAME:
				setPropertyProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ICON_PROVIDER_CLASS_NAME:
				setIconProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__STRUCTURAL_FEATURE_PARSER_CLASS_NAME:
				setStructuralFeatureParserClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				setActionBarContributorClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_CLASS_NAME:
				setCreationWizardClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_PAGE_CLASS_NAME:
				setCreationWizardPageClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_EDITOR_UTIL_CLASS_NAME:
				setDiagramEditorUtilClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_CREATOR_CLASS_NAME:
				setDiagramFileCreatorClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DOCUMENT_PROVIDER_CLASS_NAME:
				setDocumentProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_CLASS_NAME:
				setEditorClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__INIT_DIAGRAM_FILE_ACTION_CLASS_NAME:
				setInitDiagramFileActionClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__MATCHING_STRATEGY_CLASS_NAME:
				setMatchingStrategyClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_CLASS_NAME:
				setPluginClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PREFERENCE_INITIALIZER_CLASS_NAME:
				setPreferenceInitializerClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__VISUAL_ID_REGISTRY_CLASS_NAME:
				setVisualIDRegistryClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CREATE_SHORTCUT_ACTION_CLASS_NAME:
				setCreateShortcutActionClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_CHOOSER_CLASS_NAME:
				setElementChooserClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CONTAINS_SHORTCUTS_TO:
				getContainsShortcutsTo().clear();
				getContainsShortcutsTo().addAll((Collection)newValue);
				return;
			case GMFGenPackage.GEN_DIAGRAM__SHORTCUTS_PROVIDED_FOR:
				getShortcutsProvidedFor().clear();
				getShortcutsProvidedFor().addAll((Collection)newValue);
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
			case GMFGenPackage.GEN_DIAGRAM__CONTAINED_NODES:
				getContainedNodes().clear();
				return;
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_META_MODEL:
				setDomainMetaModel((GenPackage)null);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_DIAGRAM_ELEMENT:
				setDomainDiagramElement((GenClass)null);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CHILD_NODES:
				getChildNodes().clear();
				return;
			case GMFGenPackage.GEN_DIAGRAM__TOP_LEVEL_NODES:
				getTopLevelNodes().clear();
				return;
			case GMFGenPackage.GEN_DIAGRAM__LINKS:
				getLinks().clear();
				return;
			case GMFGenPackage.GEN_DIAGRAM__COMPARTMENTS:
				getCompartments().clear();
				return;
			case GMFGenPackage.GEN_DIAGRAM__AUDITS:
				setAudits((GenAuditContainer)null);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PALETTE:
				setPalette((Palette)null);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_COMMANDS_PACKAGE_NAME:
				setEditCommandsPackageName(EDIT_COMMANDS_PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PARTS_PACKAGE_NAME:
				setEditPartsPackageName(EDIT_PARTS_PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_POLICIES_PACKAGE_NAME:
				setEditPoliciesPackageName(EDIT_POLICIES_PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_PACKAGE_NAME:
				setEditorPackageName(EDITOR_PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PROVIDERS_PACKAGE_NAME:
				setProvidersPackageName(PROVIDERS_PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_FACTORIES_PACKAGE_NAME:
				setNotationViewFactoriesPackageName(NOTATION_VIEW_FACTORIES_PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_ID:
				setPluginID(PLUGIN_ID_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_NAME:
				setPluginName(PLUGIN_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PROVIDER_NAME:
				setProviderName(PROVIDER_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__SAME_FILE_FOR_DIAGRAM_AND_MODEL:
				setSameFileForDiagramAndModel(SAME_FILE_FOR_DIAGRAM_AND_MODEL_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PRINTING_ENABLED:
				setPrintingEnabled(PRINTING_ENABLED_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_EXTENSION:
				setDiagramFileExtension(DIAGRAM_FILE_EXTENSION_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME:
				setReorientConnectionViewCommandClassName(REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_FACTORY_CLASS_NAME:
				setEditPartFactoryClassName(EDIT_PART_FACTORY_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME:
				setBaseExternalNodeLabelEditPartClassName(BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME:
				setBaseItemSemanticEditPolicyClassName(BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME:
				setBaseGraphicalNodeEditPolicyClassName(BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME:
				setReferenceConnectionEditPolicyClassName(REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CANONICAL_EDIT_POLICY_CLASS_NAME:
				setCanonicalEditPolicyClassName(CANONICAL_EDIT_POLICY_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_TYPES_CLASS_NAME:
				setElementTypesClassName(ELEMENT_TYPES_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__SEMANTIC_HINTS_CLASS_NAME:
				setSemanticHintsClassName(SEMANTIC_HINTS_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_PROVIDER_CLASS_NAME:
				setNotationViewProviderClassName(NOTATION_VIEW_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_PROVIDER_CLASS_NAME:
				setEditPartProviderClassName(EDIT_PART_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__METAMODEL_SUPPORT_PROVIDER_CLASS_NAME:
				setMetamodelSupportProviderClassName(METAMODEL_SUPPORT_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__MODELING_ASSISTANT_PROVIDER_CLASS_NAME:
				setModelingAssistantProviderClassName(MODELING_ASSISTANT_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PROPERTY_PROVIDER_CLASS_NAME:
				setPropertyProviderClassName(PROPERTY_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ICON_PROVIDER_CLASS_NAME:
				setIconProviderClassName(ICON_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__STRUCTURAL_FEATURE_PARSER_CLASS_NAME:
				setStructuralFeatureParserClassName(STRUCTURAL_FEATURE_PARSER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				setActionBarContributorClassName(ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_CLASS_NAME:
				setCreationWizardClassName(CREATION_WIZARD_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_PAGE_CLASS_NAME:
				setCreationWizardPageClassName(CREATION_WIZARD_PAGE_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_EDITOR_UTIL_CLASS_NAME:
				setDiagramEditorUtilClassName(DIAGRAM_EDITOR_UTIL_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_CREATOR_CLASS_NAME:
				setDiagramFileCreatorClassName(DIAGRAM_FILE_CREATOR_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__DOCUMENT_PROVIDER_CLASS_NAME:
				setDocumentProviderClassName(DOCUMENT_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_CLASS_NAME:
				setEditorClassName(EDITOR_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__INIT_DIAGRAM_FILE_ACTION_CLASS_NAME:
				setInitDiagramFileActionClassName(INIT_DIAGRAM_FILE_ACTION_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__MATCHING_STRATEGY_CLASS_NAME:
				setMatchingStrategyClassName(MATCHING_STRATEGY_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_CLASS_NAME:
				setPluginClassName(PLUGIN_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__PREFERENCE_INITIALIZER_CLASS_NAME:
				setPreferenceInitializerClassName(PREFERENCE_INITIALIZER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__VISUAL_ID_REGISTRY_CLASS_NAME:
				setVisualIDRegistryClassName(VISUAL_ID_REGISTRY_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CREATE_SHORTCUT_ACTION_CLASS_NAME:
				setCreateShortcutActionClassName(CREATE_SHORTCUT_ACTION_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_CHOOSER_CLASS_NAME:
				setElementChooserClassName(ELEMENT_CHOOSER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_DIAGRAM__CONTAINS_SHORTCUTS_TO:
				getContainsShortcutsTo().clear();
				return;
			case GMFGenPackage.GEN_DIAGRAM__SHORTCUTS_PROVIDED_FOR:
				getShortcutsProvidedFor().clear();
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
			case GMFGenPackage.GEN_DIAGRAM__CONTAINED_NODES:
				return !getContainedNodes().isEmpty();
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_META_MODEL:
				return domainMetaModel != null;
			case GMFGenPackage.GEN_DIAGRAM__DOMAIN_DIAGRAM_ELEMENT:
				return domainDiagramElement != null;
			case GMFGenPackage.GEN_DIAGRAM__CHILD_NODES:
				return childNodes != null && !childNodes.isEmpty();
			case GMFGenPackage.GEN_DIAGRAM__TOP_LEVEL_NODES:
				return topLevelNodes != null && !topLevelNodes.isEmpty();
			case GMFGenPackage.GEN_DIAGRAM__LINKS:
				return links != null && !links.isEmpty();
			case GMFGenPackage.GEN_DIAGRAM__COMPARTMENTS:
				return compartments != null && !compartments.isEmpty();
			case GMFGenPackage.GEN_DIAGRAM__AUDITS:
				return audits != null;
			case GMFGenPackage.GEN_DIAGRAM__PALETTE:
				return palette != null;
			case GMFGenPackage.GEN_DIAGRAM__EDIT_COMMANDS_PACKAGE_NAME:
				return EDIT_COMMANDS_PACKAGE_NAME_EDEFAULT == null ? editCommandsPackageName != null : !EDIT_COMMANDS_PACKAGE_NAME_EDEFAULT.equals(editCommandsPackageName);
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PARTS_PACKAGE_NAME:
				return EDIT_PARTS_PACKAGE_NAME_EDEFAULT == null ? editPartsPackageName != null : !EDIT_PARTS_PACKAGE_NAME_EDEFAULT.equals(editPartsPackageName);
			case GMFGenPackage.GEN_DIAGRAM__EDIT_POLICIES_PACKAGE_NAME:
				return EDIT_POLICIES_PACKAGE_NAME_EDEFAULT == null ? editPoliciesPackageName != null : !EDIT_POLICIES_PACKAGE_NAME_EDEFAULT.equals(editPoliciesPackageName);
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_PACKAGE_NAME:
				return EDITOR_PACKAGE_NAME_EDEFAULT == null ? editorPackageName != null : !EDITOR_PACKAGE_NAME_EDEFAULT.equals(editorPackageName);
			case GMFGenPackage.GEN_DIAGRAM__PROVIDERS_PACKAGE_NAME:
				return PROVIDERS_PACKAGE_NAME_EDEFAULT == null ? providersPackageName != null : !PROVIDERS_PACKAGE_NAME_EDEFAULT.equals(providersPackageName);
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_FACTORIES_PACKAGE_NAME:
				return NOTATION_VIEW_FACTORIES_PACKAGE_NAME_EDEFAULT == null ? notationViewFactoriesPackageName != null : !NOTATION_VIEW_FACTORIES_PACKAGE_NAME_EDEFAULT.equals(notationViewFactoriesPackageName);
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_ID:
				return PLUGIN_ID_EDEFAULT == null ? pluginID != null : !PLUGIN_ID_EDEFAULT.equals(pluginID);
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_NAME:
				return PLUGIN_NAME_EDEFAULT == null ? pluginName != null : !PLUGIN_NAME_EDEFAULT.equals(pluginName);
			case GMFGenPackage.GEN_DIAGRAM__PROVIDER_NAME:
				return PROVIDER_NAME_EDEFAULT == null ? providerName != null : !PROVIDER_NAME_EDEFAULT.equals(providerName);
			case GMFGenPackage.GEN_DIAGRAM__SAME_FILE_FOR_DIAGRAM_AND_MODEL:
				return sameFileForDiagramAndModel != SAME_FILE_FOR_DIAGRAM_AND_MODEL_EDEFAULT;
			case GMFGenPackage.GEN_DIAGRAM__PRINTING_ENABLED:
				return printingEnabled != PRINTING_ENABLED_EDEFAULT;
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_EXTENSION:
				return DIAGRAM_FILE_EXTENSION_EDEFAULT == null ? diagramFileExtension != null : !DIAGRAM_FILE_EXTENSION_EDEFAULT.equals(diagramFileExtension);
			case GMFGenPackage.GEN_DIAGRAM__REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME:
				return REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME_EDEFAULT == null ? reorientConnectionViewCommandClassName != null : !REORIENT_CONNECTION_VIEW_COMMAND_CLASS_NAME_EDEFAULT.equals(reorientConnectionViewCommandClassName);
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_FACTORY_CLASS_NAME:
				return EDIT_PART_FACTORY_CLASS_NAME_EDEFAULT == null ? editPartFactoryClassName != null : !EDIT_PART_FACTORY_CLASS_NAME_EDEFAULT.equals(editPartFactoryClassName);
			case GMFGenPackage.GEN_DIAGRAM__BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME:
				return BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME_EDEFAULT == null ? baseExternalNodeLabelEditPartClassName != null : !BASE_EXTERNAL_NODE_LABEL_EDIT_PART_CLASS_NAME_EDEFAULT.equals(baseExternalNodeLabelEditPartClassName);
			case GMFGenPackage.GEN_DIAGRAM__BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME:
				return BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME_EDEFAULT == null ? baseItemSemanticEditPolicyClassName != null : !BASE_ITEM_SEMANTIC_EDIT_POLICY_CLASS_NAME_EDEFAULT.equals(baseItemSemanticEditPolicyClassName);
			case GMFGenPackage.GEN_DIAGRAM__BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME:
				return BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME_EDEFAULT == null ? baseGraphicalNodeEditPolicyClassName != null : !BASE_GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME_EDEFAULT.equals(baseGraphicalNodeEditPolicyClassName);
			case GMFGenPackage.GEN_DIAGRAM__REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME:
				return REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME_EDEFAULT == null ? referenceConnectionEditPolicyClassName != null : !REFERENCE_CONNECTION_EDIT_POLICY_CLASS_NAME_EDEFAULT.equals(referenceConnectionEditPolicyClassName);
			case GMFGenPackage.GEN_DIAGRAM__CANONICAL_EDIT_POLICY_CLASS_NAME:
				return CANONICAL_EDIT_POLICY_CLASS_NAME_EDEFAULT == null ? canonicalEditPolicyClassName != null : !CANONICAL_EDIT_POLICY_CLASS_NAME_EDEFAULT.equals(canonicalEditPolicyClassName);
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_TYPES_CLASS_NAME:
				return ELEMENT_TYPES_CLASS_NAME_EDEFAULT == null ? elementTypesClassName != null : !ELEMENT_TYPES_CLASS_NAME_EDEFAULT.equals(elementTypesClassName);
			case GMFGenPackage.GEN_DIAGRAM__SEMANTIC_HINTS_CLASS_NAME:
				return SEMANTIC_HINTS_CLASS_NAME_EDEFAULT == null ? semanticHintsClassName != null : !SEMANTIC_HINTS_CLASS_NAME_EDEFAULT.equals(semanticHintsClassName);
			case GMFGenPackage.GEN_DIAGRAM__NOTATION_VIEW_PROVIDER_CLASS_NAME:
				return NOTATION_VIEW_PROVIDER_CLASS_NAME_EDEFAULT == null ? notationViewProviderClassName != null : !NOTATION_VIEW_PROVIDER_CLASS_NAME_EDEFAULT.equals(notationViewProviderClassName);
			case GMFGenPackage.GEN_DIAGRAM__EDIT_PART_PROVIDER_CLASS_NAME:
				return EDIT_PART_PROVIDER_CLASS_NAME_EDEFAULT == null ? editPartProviderClassName != null : !EDIT_PART_PROVIDER_CLASS_NAME_EDEFAULT.equals(editPartProviderClassName);
			case GMFGenPackage.GEN_DIAGRAM__METAMODEL_SUPPORT_PROVIDER_CLASS_NAME:
				return METAMODEL_SUPPORT_PROVIDER_CLASS_NAME_EDEFAULT == null ? metamodelSupportProviderClassName != null : !METAMODEL_SUPPORT_PROVIDER_CLASS_NAME_EDEFAULT.equals(metamodelSupportProviderClassName);
			case GMFGenPackage.GEN_DIAGRAM__MODELING_ASSISTANT_PROVIDER_CLASS_NAME:
				return MODELING_ASSISTANT_PROVIDER_CLASS_NAME_EDEFAULT == null ? modelingAssistantProviderClassName != null : !MODELING_ASSISTANT_PROVIDER_CLASS_NAME_EDEFAULT.equals(modelingAssistantProviderClassName);
			case GMFGenPackage.GEN_DIAGRAM__PROPERTY_PROVIDER_CLASS_NAME:
				return PROPERTY_PROVIDER_CLASS_NAME_EDEFAULT == null ? propertyProviderClassName != null : !PROPERTY_PROVIDER_CLASS_NAME_EDEFAULT.equals(propertyProviderClassName);
			case GMFGenPackage.GEN_DIAGRAM__ICON_PROVIDER_CLASS_NAME:
				return ICON_PROVIDER_CLASS_NAME_EDEFAULT == null ? iconProviderClassName != null : !ICON_PROVIDER_CLASS_NAME_EDEFAULT.equals(iconProviderClassName);
			case GMFGenPackage.GEN_DIAGRAM__STRUCTURAL_FEATURE_PARSER_CLASS_NAME:
				return STRUCTURAL_FEATURE_PARSER_CLASS_NAME_EDEFAULT == null ? structuralFeatureParserClassName != null : !STRUCTURAL_FEATURE_PARSER_CLASS_NAME_EDEFAULT.equals(structuralFeatureParserClassName);
			case GMFGenPackage.GEN_DIAGRAM__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				return ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT == null ? actionBarContributorClassName != null : !ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT.equals(actionBarContributorClassName);
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_CLASS_NAME:
				return CREATION_WIZARD_CLASS_NAME_EDEFAULT == null ? creationWizardClassName != null : !CREATION_WIZARD_CLASS_NAME_EDEFAULT.equals(creationWizardClassName);
			case GMFGenPackage.GEN_DIAGRAM__CREATION_WIZARD_PAGE_CLASS_NAME:
				return CREATION_WIZARD_PAGE_CLASS_NAME_EDEFAULT == null ? creationWizardPageClassName != null : !CREATION_WIZARD_PAGE_CLASS_NAME_EDEFAULT.equals(creationWizardPageClassName);
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_EDITOR_UTIL_CLASS_NAME:
				return DIAGRAM_EDITOR_UTIL_CLASS_NAME_EDEFAULT == null ? diagramEditorUtilClassName != null : !DIAGRAM_EDITOR_UTIL_CLASS_NAME_EDEFAULT.equals(diagramEditorUtilClassName);
			case GMFGenPackage.GEN_DIAGRAM__DIAGRAM_FILE_CREATOR_CLASS_NAME:
				return DIAGRAM_FILE_CREATOR_CLASS_NAME_EDEFAULT == null ? diagramFileCreatorClassName != null : !DIAGRAM_FILE_CREATOR_CLASS_NAME_EDEFAULT.equals(diagramFileCreatorClassName);
			case GMFGenPackage.GEN_DIAGRAM__DOCUMENT_PROVIDER_CLASS_NAME:
				return DOCUMENT_PROVIDER_CLASS_NAME_EDEFAULT == null ? documentProviderClassName != null : !DOCUMENT_PROVIDER_CLASS_NAME_EDEFAULT.equals(documentProviderClassName);
			case GMFGenPackage.GEN_DIAGRAM__EDITOR_CLASS_NAME:
				return EDITOR_CLASS_NAME_EDEFAULT == null ? editorClassName != null : !EDITOR_CLASS_NAME_EDEFAULT.equals(editorClassName);
			case GMFGenPackage.GEN_DIAGRAM__INIT_DIAGRAM_FILE_ACTION_CLASS_NAME:
				return INIT_DIAGRAM_FILE_ACTION_CLASS_NAME_EDEFAULT == null ? initDiagramFileActionClassName != null : !INIT_DIAGRAM_FILE_ACTION_CLASS_NAME_EDEFAULT.equals(initDiagramFileActionClassName);
			case GMFGenPackage.GEN_DIAGRAM__MATCHING_STRATEGY_CLASS_NAME:
				return MATCHING_STRATEGY_CLASS_NAME_EDEFAULT == null ? matchingStrategyClassName != null : !MATCHING_STRATEGY_CLASS_NAME_EDEFAULT.equals(matchingStrategyClassName);
			case GMFGenPackage.GEN_DIAGRAM__PLUGIN_CLASS_NAME:
				return PLUGIN_CLASS_NAME_EDEFAULT == null ? pluginClassName != null : !PLUGIN_CLASS_NAME_EDEFAULT.equals(pluginClassName);
			case GMFGenPackage.GEN_DIAGRAM__PREFERENCE_INITIALIZER_CLASS_NAME:
				return PREFERENCE_INITIALIZER_CLASS_NAME_EDEFAULT == null ? preferenceInitializerClassName != null : !PREFERENCE_INITIALIZER_CLASS_NAME_EDEFAULT.equals(preferenceInitializerClassName);
			case GMFGenPackage.GEN_DIAGRAM__VISUAL_ID_REGISTRY_CLASS_NAME:
				return VISUAL_ID_REGISTRY_CLASS_NAME_EDEFAULT == null ? visualIDRegistryClassName != null : !VISUAL_ID_REGISTRY_CLASS_NAME_EDEFAULT.equals(visualIDRegistryClassName);
			case GMFGenPackage.GEN_DIAGRAM__CREATE_SHORTCUT_ACTION_CLASS_NAME:
				return CREATE_SHORTCUT_ACTION_CLASS_NAME_EDEFAULT == null ? createShortcutActionClassName != null : !CREATE_SHORTCUT_ACTION_CLASS_NAME_EDEFAULT.equals(createShortcutActionClassName);
			case GMFGenPackage.GEN_DIAGRAM__ELEMENT_CHOOSER_CLASS_NAME:
				return ELEMENT_CHOOSER_CLASS_NAME_EDEFAULT == null ? elementChooserClassName != null : !ELEMENT_CHOOSER_CLASS_NAME_EDEFAULT.equals(elementChooserClassName);
			case GMFGenPackage.GEN_DIAGRAM__CONTAINS_SHORTCUTS_TO:
				return containsShortcutsTo != null && !containsShortcutsTo.isEmpty();
			case GMFGenPackage.GEN_DIAGRAM__SHORTCUTS_PROVIDED_FOR:
				return shortcutsProvidedFor != null && !shortcutsProvidedFor.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean hasLinkCreationConstraints() {
		for (Iterator it = getLinks().iterator(); it.hasNext();) {
			GenLink nextLink = (GenLink) it.next();
			if(nextLink.getCreationConstraints() != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getLinkCreationConstraintsClassName() {
		return "LinkConstraints"; //$NON-NLS-1$		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getLinkCreationConstraintsQualifiedClassName() {
		String owningClass = getBaseItemSemanticEditPolicyQualifiedClassName();
		if(owningClass != null) {
			return owningClass + "." + getLinkCreationConstraintsClassName(); //$NON-NLS-1$
		}
		return getLinkCreationConstraintsClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getEditorQualifiedClassName() {
		return getEditorPackageName() + '.' + getEditorClassName();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getReorientConnectionViewCommandQualifiedClassName() {
		return getEditCommandsPackageName() + '.' + getReorientConnectionViewCommandClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getEditPartFactoryQualifiedClassName() {
		return getEditPartsPackageName() + '.' + getEditPartFactoryClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getBaseExternalNodeLabelEditPartQualifiedClassName() {
		return getEditPartsPackageName() + '.' + getBaseExternalNodeLabelEditPartClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getBaseItemSemanticEditPolicyQualifiedClassName() {
		return getEditPoliciesPackageName() + '.' + getBaseItemSemanticEditPolicyClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getBaseGraphicalNodeEditPolicyQualifiedClassName() {
		return getEditPoliciesPackageName() + '.' + getBaseGraphicalNodeEditPolicyClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getReferenceConnectionEditPolicyQualifiedClassName() {
		return getEditPoliciesPackageName() + '.' + getReferenceConnectionEditPolicyClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getCanonicalEditPolicyQualifiedClassName() {
		return getEditPoliciesPackageName() + '.' + getCanonicalEditPolicyClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getElementTypesQualifiedClassName() {
		return getProvidersPackageName() + '.' + getElementTypesClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getSemanticHintsQualifiedClassName() {
		return getProvidersPackageName() + '.' + getSemanticHintsClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getNotationViewProviderQualifiedClassName() {
		return getProvidersPackageName() + '.' + getNotationViewProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getRequiredPluginIDs() {
		Collection requiredPlugins = new LinkedHashSet();
		if (usesSVGShapes()) {
			requiredPlugins.add("org.eclipse.gmf.diagramrt.gefsvg");
		}
		
		requiredPlugins.addAll(getExpressionsRequiredPluginIDs());
		requiredPlugins.addAll(getAuditRequiredPluginIDs());
		return new BasicEList(requiredPlugins);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getInitDiagramFileActionQualifiedClassName() {
		return getEditorPackageName() + '.' + getInitDiagramFileActionClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getEditPartProviderQualifiedClassName() {
		return getProvidersPackageName() + '.' + getEditPartProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getMetamodelSupportProviderQualifiedClassName() {
		return getProvidersPackageName() + '.' + getMetamodelSupportProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getModelingAssistantProviderQualifiedClassName() {
		return getProvidersPackageName() + '.' + getModelingAssistantProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getPropertyProviderQualifiedClassName() {
		return getProvidersPackageName() + '.' + getPropertyProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getIconProviderQualifiedClassName() {
		return getProvidersPackageName() + '.' + getIconProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getStructuralFeatureParserQualifiedClassName() {
		return getProvidersPackageName() + '.' + getStructuralFeatureParserClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getActionBarContributorQualifiedClassName() {
		return getEditorPackageName() + '.' + getActionBarContributorClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getCreationWizardQualifiedClassName() {
		return getEditorPackageName() + '.' + getCreationWizardClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getCreationWizardPageQualifiedClassName() {
		return getEditorPackageName() + '.' + getCreationWizardPageClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getDiagramEditorUtilQualifiedClassName() {
		return getEditorPackageName() + '.' + getDiagramEditorUtilClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getDiagramFileCreatorQualifiedClassName() {
		return getEditorPackageName() + '.' + getDiagramFileCreatorClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getDocumentProviderQualifiedClassName() {
		return getEditorPackageName() + '.' + getDocumentProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getMatchingStrategyQualifiedClassName() {
		return getEditorPackageName() + '.' + getMatchingStrategyClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getElementInitializersClassName() {
		return "ElementInitializers"; //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getElementInitializersPackageName() {
		return getProvidersPackageName();
	}

	private boolean usesSVGShapes() {
/* couldn't tell now
		for (Iterator it = getNodes().iterator(); it.hasNext();) {
			if (((GenNode) it.next()).getViewmapURI() != null) {
				return true;
			}
		}
*/
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (editCommandsPackageName: ");
		result.append(editCommandsPackageName);
		result.append(", editPartsPackageName: ");
		result.append(editPartsPackageName);
		result.append(", editPoliciesPackageName: ");
		result.append(editPoliciesPackageName);
		result.append(", editorPackageName: ");
		result.append(editorPackageName);
		result.append(", providersPackageName: ");
		result.append(providersPackageName);
		result.append(", notationViewFactoriesPackageName: ");
		result.append(notationViewFactoriesPackageName);
		result.append(", pluginID: ");
		result.append(pluginID);
		result.append(", pluginName: ");
		result.append(pluginName);
		result.append(", providerName: ");
		result.append(providerName);
		result.append(", sameFileForDiagramAndModel: ");
		result.append(sameFileForDiagramAndModel);
		result.append(", printingEnabled: ");
		result.append(printingEnabled);
		result.append(", diagramFileExtension: ");
		result.append(diagramFileExtension);
		result.append(", reorientConnectionViewCommandClassName: ");
		result.append(reorientConnectionViewCommandClassName);
		result.append(", editPartFactoryClassName: ");
		result.append(editPartFactoryClassName);
		result.append(", baseExternalNodeLabelEditPartClassName: ");
		result.append(baseExternalNodeLabelEditPartClassName);
		result.append(", baseItemSemanticEditPolicyClassName: ");
		result.append(baseItemSemanticEditPolicyClassName);
		result.append(", baseGraphicalNodeEditPolicyClassName: ");
		result.append(baseGraphicalNodeEditPolicyClassName);
		result.append(", referenceConnectionEditPolicyClassName: ");
		result.append(referenceConnectionEditPolicyClassName);
		result.append(", canonicalEditPolicyClassName: ");
		result.append(canonicalEditPolicyClassName);
		result.append(", elementTypesClassName: ");
		result.append(elementTypesClassName);
		result.append(", semanticHintsClassName: ");
		result.append(semanticHintsClassName);
		result.append(", notationViewProviderClassName: ");
		result.append(notationViewProviderClassName);
		result.append(", editPartProviderClassName: ");
		result.append(editPartProviderClassName);
		result.append(", metamodelSupportProviderClassName: ");
		result.append(metamodelSupportProviderClassName);
		result.append(", modelingAssistantProviderClassName: ");
		result.append(modelingAssistantProviderClassName);
		result.append(", propertyProviderClassName: ");
		result.append(propertyProviderClassName);
		result.append(", iconProviderClassName: ");
		result.append(iconProviderClassName);
		result.append(", structuralFeatureParserClassName: ");
		result.append(structuralFeatureParserClassName);
		result.append(", actionBarContributorClassName: ");
		result.append(actionBarContributorClassName);
		result.append(", creationWizardClassName: ");
		result.append(creationWizardClassName);
		result.append(", creationWizardPageClassName: ");
		result.append(creationWizardPageClassName);
		result.append(", diagramEditorUtilClassName: ");
		result.append(diagramEditorUtilClassName);
		result.append(", diagramFileCreatorClassName: ");
		result.append(diagramFileCreatorClassName);
		result.append(", documentProviderClassName: ");
		result.append(documentProviderClassName);
		result.append(", editorClassName: ");
		result.append(editorClassName);
		result.append(", initDiagramFileActionClassName: ");
		result.append(initDiagramFileActionClassName);
		result.append(", matchingStrategyClassName: ");
		result.append(matchingStrategyClassName);
		result.append(", pluginClassName: ");
		result.append(pluginClassName);
		result.append(", preferenceInitializerClassName: ");
		result.append(preferenceInitializerClassName);
		result.append(", visualIDRegistryClassName: ");
		result.append(visualIDRegistryClassName);
		result.append(", createShortcutActionClassName: ");
		result.append(createShortcutActionClassName);
		result.append(", elementChooserClassName: ");
		result.append(elementChooserClassName);
		result.append(", containsShortcutsTo: ");
		result.append(containsShortcutsTo);
		result.append(", shortcutsProvidedFor: ");
		result.append(shortcutsProvidedFor);
		result.append(')');
		return result.toString();
	}

	public String getUniqueIdentifier() {
		return getDomainDiagramElement().getInterfaceName() + "_" + getVisualID();
	}

	/**
	 * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getQualifiedPackageName()
	 */
	private String getPackageNamePrefix() {
		String prefix = CodeGenUtil.safeName(getDomainMetaModel().getPackageName());
		String basePackage = getDomainMetaModel().getBasePackage();
		if (basePackage != null && basePackage.length() > 0) {
			prefix = basePackage + '.' + prefix;
		}
		return prefix + '.' + DIAGRAM_EDITOR_TOKEN;
	}

	String getDomainPackageCapName() {
		String name = CodeGenUtil.validJavaIdentifier(getEMFGenModel().getModelName());
		if (name.length() < 2) {
			return name.toUpperCase();
		}
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	private Set getExpressionsRequiredPluginIDs() {
		Set requiredIDs = new HashSet();
		for (Iterator it = getAllNodes().iterator(); it.hasNext();) {
			GenNode nextNode = (GenNode) it.next();
			TypeModelFacet modelFacet = nextNode.getModelFacet();
			if(modelFacet.getModelElementInitializer() != null) {
				requiredIDs.addAll(modelFacet.getModelElementInitializer().getRequiredPluginIDs());
			}
			if(modelFacet.getModelElementSelector() != null) {
				requiredIDs.addAll(modelFacet.getModelElementSelector().getRequiredPluginIDs());				
			}
		}
		
		for (Iterator it = getLinks().iterator(); it.hasNext();) {
			GenLink nextLink = (GenLink) it.next();
			LinkModelFacet modelFacet = nextLink.getModelFacet();
			if(modelFacet instanceof TypeLinkModelFacet) {
				TypeLinkModelFacet  typeModelFacet = (TypeLinkModelFacet)modelFacet;
				if(typeModelFacet.getModelElementInitializer() != null) {
					requiredIDs.addAll(typeModelFacet.getModelElementInitializer().getRequiredPluginIDs());
				}
				if(typeModelFacet.getModelElementSelector() != null) {
					requiredIDs.addAll(typeModelFacet.getModelElementSelector().getRequiredPluginIDs());
				}
			}
		}		
		if(hasLinkCreationConstraints()) {
			requiredIDs.add("org.eclipse.emf.ocl"); //$NON-NLS-1$			
			requiredIDs.add("org.eclipse.emf.query.ocl"); //$NON-NLS-1$			
		}
		return requiredIDs;
	}
	
	private Set getAuditRequiredPluginIDs() {
		if(getAudits() != null && getAudits().getAllAuditRules().size() > 0) {
			return Collections.singleton("org.eclipse.emf.validation"); //$NON-NLS-1$ 
		}
		return Collections.EMPTY_SET;
	}
} //GenDiagramImpl