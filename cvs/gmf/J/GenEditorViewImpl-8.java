/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.impl;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.gmfgen.GenEditorView;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Editor View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenEditorViewImpl#getEditorGen <em>Editor Gen</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenEditorViewImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenEditorViewImpl#getActionBarContributorClassName <em>Action Bar Contributor Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenEditorViewImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenEditorViewImpl#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenEditorViewImpl#getID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenEditorViewImpl#isEclipseEditor <em>Eclipse Editor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenEditorViewImpl extends EObjectImpl implements GenEditorView {
	/**
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconPath()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconPath()
	 * @generated
	 * @ordered
	 */
	protected String iconPath = ICON_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected String iD = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isEclipseEditor() <em>Eclipse Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEclipseEditor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ECLIPSE_EDITOR_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEclipseEditor() <em>Eclipse Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEclipseEditor()
	 * @generated
	 * @ordered
	 */
	protected boolean eclipseEditor = ECLIPSE_EDITOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenEditorViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GMFGenPackage.eINSTANCE.getGenEditorView();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenEditorGenerator getEditorGen() {
		if (eContainerFeatureID != GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN) return null;
		return (GenEditorGenerator)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageNameGen() {
		return packageName;
	}

	public String getPackageName() {
		String value = getPackageNameGen();
		if (value == null) {
			value = getEditorGen().getPackageNamePrefix() + ".part";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EDITOR_VIEW__PACKAGE_NAME, oldPackageName, packageName));
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
		if (GenCommonBaseImpl.isEmpty(value)) {
			value = getDomainModelCapName() + "DiagramActionBarContributor"; //$NON-NLS-1$
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
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EDITOR_VIEW__ACTION_BAR_CONTRIBUTOR_CLASS_NAME, oldActionBarContributorClassName, actionBarContributorClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassNameGen() {
		return className;
	}

	public String getClassName() {
		String value = getClassNameGen();
		if (GenCommonBaseImpl.isEmpty(value)) {
			value = getDomainModelCapName() + "DiagramEditor"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EDITOR_VIEW__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIconPathGen() {
		return iconPath;
	}

	public String getIconPath() {
		String value = getIconPathGen();
		if (GenCommonBaseImpl.isEmpty(value)) {
			if (getEditorGen().getDiagram() != null) {
				return getEditorGen().getDiagram().getCreationWizardIconPath();
			} else {
				return createDefaultIconPath();
			}
		}
		return value;
	}

	public String getIconPathX() {
		String value = getIconPath();
		if (GenDiagramImpl.REUSE_ICON_VALUE.equalsIgnoreCase(value)) {
			if (getEditorGen().getDiagram() != null && getEditorGen().getDiagram().getDomainDiagramElement() != null) {
				GenPackage domainMetaModel = getEditorGen().getDiagram().getDomainDiagramElement().getGenPackage();
				return "../" + getEditorGen().getDomainGenModel().getEditorPluginID() + "/icons/full/obj16/" + domainMetaModel.getPrefix() + "ModelFile.gif";
			} else {
				return createDefaultIconPath();
			}
		}
		return value;
	}

	private static String createDefaultIconPath() {
		// use same default value as in the genDiagram
		return new GenDiagramImpl().getCreationWizardIconPath();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIconPath(String newIconPath) {
		String oldIconPath = iconPath;
		iconPath = newIconPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EDITOR_VIEW__ICON_PATH, oldIconPath, iconPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIDGen() {
		return iD;
	}

	public String getID() {
		String value = getIDGen();
		if (GenCommonBaseImpl.isEmpty(value)) {
			return getQualifiedClassName() + "ID";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setID(String newID) {
		String oldID = iD;
		iD = newID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EDITOR_VIEW__ID, oldID, iD));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEclipseEditor() {
		return eclipseEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEclipseEditor(boolean newEclipseEditor) {
		boolean oldEclipseEditor = eclipseEditor;
		eclipseEditor = newEclipseEditor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_EDITOR_VIEW__ECLIPSE_EDITOR, oldEclipseEditor, eclipseEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getActionBarContributorQualifiedClassName() {
		return getPackageName() + '.' + getActionBarContributorClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getQualifiedClassName() {
		return getPackageName() + '.' + getClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN, msgs);
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
			case GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN:
				return eBasicSetContainer(null, GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN:
				return eInternalContainer().eInverseRemove(this, GMFGenPackage.GEN_EDITOR_GENERATOR__EDITOR, GenEditorGenerator.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN:
				return getEditorGen();
			case GMFGenPackage.GEN_EDITOR_VIEW__PACKAGE_NAME:
				return getPackageName();
			case GMFGenPackage.GEN_EDITOR_VIEW__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				return getActionBarContributorClassName();
			case GMFGenPackage.GEN_EDITOR_VIEW__CLASS_NAME:
				return getClassName();
			case GMFGenPackage.GEN_EDITOR_VIEW__ICON_PATH:
				return getIconPath();
			case GMFGenPackage.GEN_EDITOR_VIEW__ID:
				return getID();
			case GMFGenPackage.GEN_EDITOR_VIEW__ECLIPSE_EDITOR:
				return isEclipseEditor() ? Boolean.TRUE : Boolean.FALSE;
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
			case GMFGenPackage.GEN_EDITOR_VIEW__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				setActionBarContributorClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ICON_PATH:
				setIconPath((String)newValue);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ID:
				setID((String)newValue);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ECLIPSE_EDITOR:
				setEclipseEditor(((Boolean)newValue).booleanValue());
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
			case GMFGenPackage.GEN_EDITOR_VIEW__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				setActionBarContributorClassName(ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ICON_PATH:
				setIconPath(ICON_PATH_EDEFAULT);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ID:
				setID(ID_EDEFAULT);
				return;
			case GMFGenPackage.GEN_EDITOR_VIEW__ECLIPSE_EDITOR:
				setEclipseEditor(ECLIPSE_EDITOR_EDEFAULT);
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
			case GMFGenPackage.GEN_EDITOR_VIEW__EDITOR_GEN:
				return getEditorGen() != null;
			case GMFGenPackage.GEN_EDITOR_VIEW__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
			case GMFGenPackage.GEN_EDITOR_VIEW__ACTION_BAR_CONTRIBUTOR_CLASS_NAME:
				return ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT == null ? actionBarContributorClassName != null : !ACTION_BAR_CONTRIBUTOR_CLASS_NAME_EDEFAULT.equals(actionBarContributorClassName);
			case GMFGenPackage.GEN_EDITOR_VIEW__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case GMFGenPackage.GEN_EDITOR_VIEW__ICON_PATH:
				return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
			case GMFGenPackage.GEN_EDITOR_VIEW__ID:
				return ID_EDEFAULT == null ? iD != null : !ID_EDEFAULT.equals(iD);
			case GMFGenPackage.GEN_EDITOR_VIEW__ECLIPSE_EDITOR:
				return eclipseEditor != ECLIPSE_EDITOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (packageName: ");
		result.append(packageName);
		result.append(", actionBarContributorClassName: ");
		result.append(actionBarContributorClassName);
		result.append(", className: ");
		result.append(className);
		result.append(", iconPath: ");
		result.append(iconPath);
		result.append(", iD: ");
		result.append(iD);
		result.append(", eclipseEditor: ");
		result.append(eclipseEditor);
		result.append(')');
		return result.toString();
	}

	/**
	 * delegates to {@link GenEditorGeneratorImpl#getDomainModelCapName()}
	 */
	private String getDomainModelCapName() {
		return ((GenEditorGeneratorImpl) getEditorGen()).getDomainModelCapName();
	}
} //GenEditorViewImpl