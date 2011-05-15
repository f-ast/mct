/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenCommonBase;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.gmfgen.GenNavigator;
import org.eclipse.gmf.codegen.gmfgen.GenNavigatorChildReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Navigator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getEditorGen <em>Editor Gen</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getContentExtensionID <em>Content Extension ID</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getContentExtensionName <em>Content Extension Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getContentExtensionPriority <em>Content Extension Priority</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getContentProviderClassName <em>Content Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getLabelProviderClassName <em>Label Provider Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getAbstractNavigatorItemClassName <em>Abstract Navigator Item Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getNavigatorGroupClassName <em>Navigator Group Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getNavigatorItemClassName <em>Navigator Item Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.GenNavigatorImpl#getChildReferences <em>Child References</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenNavigatorImpl extends EObjectImpl implements GenNavigator {
	/**
	 * The default value of the '{@link #getContentExtensionID() <em>Content Extension ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentExtensionID()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EXTENSION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContentExtensionID() <em>Content Extension ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentExtensionID()
	 * @generated
	 * @ordered
	 */
	protected String contentExtensionID = CONTENT_EXTENSION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getContentExtensionName() <em>Content Extension Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentExtensionName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EXTENSION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContentExtensionName() <em>Content Extension Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentExtensionName()
	 * @generated
	 * @ordered
	 */
	protected String contentExtensionName = CONTENT_EXTENSION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getContentExtensionPriority() <em>Content Extension Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentExtensionPriority()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EXTENSION_PRIORITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContentExtensionPriority() <em>Content Extension Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentExtensionPriority()
	 * @generated
	 * @ordered
	 */
	protected String contentExtensionPriority = CONTENT_EXTENSION_PRIORITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getContentProviderClassName() <em>Content Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContentProviderClassName() <em>Content Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String contentProviderClassName = CONTENT_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabelProviderClassName() <em>Label Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_PROVIDER_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabelProviderClassName() <em>Label Provider Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelProviderClassName()
	 * @generated
	 * @ordered
	 */
	protected String labelProviderClassName = LABEL_PROVIDER_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAbstractNavigatorItemClassName() <em>Abstract Navigator Item Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractNavigatorItemClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAbstractNavigatorItemClassName() <em>Abstract Navigator Item Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractNavigatorItemClassName()
	 * @generated
	 * @ordered
	 */
	protected String abstractNavigatorItemClassName = ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNavigatorGroupClassName() <em>Navigator Group Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigatorGroupClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAVIGATOR_GROUP_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNavigatorGroupClassName() <em>Navigator Group Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigatorGroupClassName()
	 * @generated
	 * @ordered
	 */
	protected String navigatorGroupClassName = NAVIGATOR_GROUP_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNavigatorItemClassName() <em>Navigator Item Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigatorItemClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNavigatorItemClassName() <em>Navigator Item Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigatorItemClassName()
	 * @generated
	 * @ordered
	 */
	protected String navigatorItemClassName = NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getChildReferences() <em>Child References</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildReferences()
	 * @generated
	 * @ordered
	 */
	protected EList childReferences = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenNavigatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GMFGenPackage.eINSTANCE.getGenNavigator();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenEditorGenerator getEditorGen() {
		if (eContainerFeatureID != GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN) return null;
		return (GenEditorGenerator)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContentExtensionIDGen() {
		return contentExtensionID;
	}

	public String getContentExtensionID() {
		String value = getContentExtensionIDGen();
		if (isEmpty(value)) {
			value = getEditorGen().getPlugin().getID() + ".resourceContent";
		}
		return value;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContentExtensionID(String newContentExtensionID) {
		String oldContentExtensionID = contentExtensionID;
		contentExtensionID = newContentExtensionID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_ID, oldContentExtensionID, contentExtensionID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContentExtensionNameGen() {
		return contentExtensionName;
	}

	public String getContentExtensionName() {
		String value = getContentExtensionNameGen();
		if (isEmpty(value)) {
			value = "%navigatorContentName";
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContentExtensionName(String newContentExtensionName) {
		String oldContentExtensionName = contentExtensionName;
		contentExtensionName = newContentExtensionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_NAME, oldContentExtensionName, contentExtensionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContentExtensionPriorityGen() {
		return contentExtensionPriority;
	}

	public String getContentExtensionPriority() {
		String value = getContentExtensionPriorityGen();
		if (isEmpty(value)) {
			value = "normal"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContentExtensionPriority(String newContentExtensionPriority) {
		String oldContentExtensionPriority = contentExtensionPriority;
		contentExtensionPriority = newContentExtensionPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_PRIORITY, oldContentExtensionPriority, contentExtensionPriority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContentProviderClassNameGen() {
		return contentProviderClassName;
	}

	public String getContentProviderClassName() {
		String value = getContentProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "NavigatorContentProvider"; //$NON-NLS-1$
		}
		return value;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContentProviderClassName(String newContentProviderClassName) {
		String oldContentProviderClassName = contentProviderClassName;
		contentProviderClassName = newContentProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__CONTENT_PROVIDER_CLASS_NAME, oldContentProviderClassName, contentProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabelProviderClassNameGen() {
		return labelProviderClassName;
	}

	public String getLabelProviderClassName() {
		String value = getLabelProviderClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "NavigatorLabelProvider"; //$NON-NLS-1$
		}
		return value;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelProviderClassName(String newLabelProviderClassName) {
		String oldLabelProviderClassName = labelProviderClassName;
		labelProviderClassName = newLabelProviderClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__LABEL_PROVIDER_CLASS_NAME, oldLabelProviderClassName, labelProviderClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAbstractNavigatorItemClassNameGen() {
		return abstractNavigatorItemClassName;
	}

	public String getAbstractNavigatorItemClassName() {
		String value = getAbstractNavigatorItemClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "AbstractNavigatorItem"; //$NON-NLS-1$
		}
		return value;

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstractNavigatorItemClassName(String newAbstractNavigatorItemClassName) {
		String oldAbstractNavigatorItemClassName = abstractNavigatorItemClassName;
		abstractNavigatorItemClassName = newAbstractNavigatorItemClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME, oldAbstractNavigatorItemClassName, abstractNavigatorItemClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNavigatorGroupClassNameGen() {
		return navigatorGroupClassName;
	}

	public String getNavigatorGroupClassName() {
		String value = getNavigatorGroupClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "NavigatorGroup"; //$NON-NLS-1$
		}
		return value;	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigatorGroupClassName(String newNavigatorGroupClassName) {
		String oldNavigatorGroupClassName = navigatorGroupClassName;
		navigatorGroupClassName = newNavigatorGroupClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_GROUP_CLASS_NAME, oldNavigatorGroupClassName, navigatorGroupClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNavigatorItemClassNameGen() {
		return navigatorItemClassName;
	}

	public String getNavigatorItemClassName() {
		String value = getNavigatorItemClassNameGen();
		if (isEmpty(value)) {
			value = getDomainPackageCapName() + "NavigatorItem"; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigatorItemClassName(String newNavigatorItemClassName) {
		String oldNavigatorItemClassName = navigatorItemClassName;
		navigatorItemClassName = newNavigatorItemClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_ITEM_CLASS_NAME, oldNavigatorItemClassName, navigatorItemClassName));
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
			value = getEditorGen().getPackageNamePrefix() + ".navigator";
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
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.GEN_NAVIGATOR__PACKAGE_NAME, oldPackageName, packageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getChildReferences() {
		if (childReferences == null) {
			childReferences = new EObjectContainmentEList(GenNavigatorChildReference.class, this, GMFGenPackage.GEN_NAVIGATOR__CHILD_REFERENCES);
		}
		return childReferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getContentProviderQualifiedClassName() {
		return getPackageName() + '.' + getContentProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getLabelProviderQualifiedClassName() {
		return getPackageName() + '.' + getLabelProviderClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getAbstractNavigatorItemQualifiedClassName() {
		return getPackageName() + '.' + getAbstractNavigatorItemClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getNavigatorGroupQualifiedClassName() {
		return getPackageName() + '.' + getNavigatorGroupClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getNavigatorItemQualifiedClassName() {
		return getPackageName() + '.' + getNavigatorItemClassName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getChildReferencesFrom(GenCommonBase parent) {
		Collection result = new ArrayList();
		for (Iterator it = getChildReferences().iterator(); it.hasNext();) {
			GenNavigatorChildReference nextReference = (GenNavigatorChildReference) it.next();
			if (parent == nextReference.getParent()) {
				result.add(nextReference);
			}
		}
		return new BasicEList.UnmodifiableEList(result.size(), result.toArray());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getChildReferencesTo(GenCommonBase child) {
		Collection result = new ArrayList();
		for (Iterator it = getChildReferences().iterator(); it.hasNext();) {
			GenNavigatorChildReference nextReference = (GenNavigatorChildReference) it.next();
			if (child == nextReference.getChild()) {
				result.add(nextReference);
			}
		}
		return new BasicEList.UnmodifiableEList(result.size(), result.toArray());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN, msgs);
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
			case GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN:
				return eBasicSetContainer(null, GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN, msgs);
			case GMFGenPackage.GEN_NAVIGATOR__CHILD_REFERENCES:
				return ((InternalEList)getChildReferences()).basicRemove(otherEnd, msgs);
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
			case GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN:
				return eInternalContainer().eInverseRemove(this, GMFGenPackage.GEN_EDITOR_GENERATOR__NAVIGATOR, GenEditorGenerator.class, msgs);
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
			case GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN:
				return getEditorGen();
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_ID:
				return getContentExtensionID();
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_NAME:
				return getContentExtensionName();
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_PRIORITY:
				return getContentExtensionPriority();
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_PROVIDER_CLASS_NAME:
				return getContentProviderClassName();
			case GMFGenPackage.GEN_NAVIGATOR__LABEL_PROVIDER_CLASS_NAME:
				return getLabelProviderClassName();
			case GMFGenPackage.GEN_NAVIGATOR__ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME:
				return getAbstractNavigatorItemClassName();
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_GROUP_CLASS_NAME:
				return getNavigatorGroupClassName();
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_ITEM_CLASS_NAME:
				return getNavigatorItemClassName();
			case GMFGenPackage.GEN_NAVIGATOR__PACKAGE_NAME:
				return getPackageName();
			case GMFGenPackage.GEN_NAVIGATOR__CHILD_REFERENCES:
				return getChildReferences();
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
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_ID:
				setContentExtensionID((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_NAME:
				setContentExtensionName((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_PRIORITY:
				setContentExtensionPriority((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_PROVIDER_CLASS_NAME:
				setContentProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__LABEL_PROVIDER_CLASS_NAME:
				setLabelProviderClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME:
				setAbstractNavigatorItemClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_GROUP_CLASS_NAME:
				setNavigatorGroupClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_ITEM_CLASS_NAME:
				setNavigatorItemClassName((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CHILD_REFERENCES:
				getChildReferences().clear();
				getChildReferences().addAll((Collection)newValue);
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
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_ID:
				setContentExtensionID(CONTENT_EXTENSION_ID_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_NAME:
				setContentExtensionName(CONTENT_EXTENSION_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_PRIORITY:
				setContentExtensionPriority(CONTENT_EXTENSION_PRIORITY_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_PROVIDER_CLASS_NAME:
				setContentProviderClassName(CONTENT_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__LABEL_PROVIDER_CLASS_NAME:
				setLabelProviderClassName(LABEL_PROVIDER_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME:
				setAbstractNavigatorItemClassName(ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_GROUP_CLASS_NAME:
				setNavigatorGroupClassName(NAVIGATOR_GROUP_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_ITEM_CLASS_NAME:
				setNavigatorItemClassName(NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
			case GMFGenPackage.GEN_NAVIGATOR__CHILD_REFERENCES:
				getChildReferences().clear();
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
			case GMFGenPackage.GEN_NAVIGATOR__EDITOR_GEN:
				return getEditorGen() != null;
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_ID:
				return CONTENT_EXTENSION_ID_EDEFAULT == null ? contentExtensionID != null : !CONTENT_EXTENSION_ID_EDEFAULT.equals(contentExtensionID);
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_NAME:
				return CONTENT_EXTENSION_NAME_EDEFAULT == null ? contentExtensionName != null : !CONTENT_EXTENSION_NAME_EDEFAULT.equals(contentExtensionName);
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_EXTENSION_PRIORITY:
				return CONTENT_EXTENSION_PRIORITY_EDEFAULT == null ? contentExtensionPriority != null : !CONTENT_EXTENSION_PRIORITY_EDEFAULT.equals(contentExtensionPriority);
			case GMFGenPackage.GEN_NAVIGATOR__CONTENT_PROVIDER_CLASS_NAME:
				return CONTENT_PROVIDER_CLASS_NAME_EDEFAULT == null ? contentProviderClassName != null : !CONTENT_PROVIDER_CLASS_NAME_EDEFAULT.equals(contentProviderClassName);
			case GMFGenPackage.GEN_NAVIGATOR__LABEL_PROVIDER_CLASS_NAME:
				return LABEL_PROVIDER_CLASS_NAME_EDEFAULT == null ? labelProviderClassName != null : !LABEL_PROVIDER_CLASS_NAME_EDEFAULT.equals(labelProviderClassName);
			case GMFGenPackage.GEN_NAVIGATOR__ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME:
				return ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT == null ? abstractNavigatorItemClassName != null : !ABSTRACT_NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT.equals(abstractNavigatorItemClassName);
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_GROUP_CLASS_NAME:
				return NAVIGATOR_GROUP_CLASS_NAME_EDEFAULT == null ? navigatorGroupClassName != null : !NAVIGATOR_GROUP_CLASS_NAME_EDEFAULT.equals(navigatorGroupClassName);
			case GMFGenPackage.GEN_NAVIGATOR__NAVIGATOR_ITEM_CLASS_NAME:
				return NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT == null ? navigatorItemClassName != null : !NAVIGATOR_ITEM_CLASS_NAME_EDEFAULT.equals(navigatorItemClassName);
			case GMFGenPackage.GEN_NAVIGATOR__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
			case GMFGenPackage.GEN_NAVIGATOR__CHILD_REFERENCES:
				return childReferences != null && !childReferences.isEmpty();
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
		result.append(" (contentExtensionID: ");
		result.append(contentExtensionID);
		result.append(", contentExtensionName: ");
		result.append(contentExtensionName);
		result.append(", contentExtensionPriority: ");
		result.append(contentExtensionPriority);
		result.append(", contentProviderClassName: ");
		result.append(contentProviderClassName);
		result.append(", labelProviderClassName: ");
		result.append(labelProviderClassName);
		result.append(", abstractNavigatorItemClassName: ");
		result.append(abstractNavigatorItemClassName);
		result.append(", navigatorGroupClassName: ");
		result.append(navigatorGroupClassName);
		result.append(", navigatorItemClassName: ");
		result.append(navigatorItemClassName);
		result.append(", packageName: ");
		result.append(packageName);
		result.append(')');
		return result.toString();
	}

	private String getDomainPackageCapName() {
		return ((GenEditorGeneratorImpl) getEditorGen()).getDomainModelCapName();
	}

	static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}
	
} //GenNavigatorImpl