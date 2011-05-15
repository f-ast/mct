/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.notation.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.provider.EModelElementItemProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmf.runtime.notation.NotationEditPlugin;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
/**
 * This is the item provider adapter for a {@link org.eclipse.gmf.runtime.notation.View} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ViewItemProvider
	extends EModelElementItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addStylesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Visible feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVisiblePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_View_visible_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_visible_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_Visible(),
				 true,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_View_type_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_type_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_Type(),
				 true,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Mutable feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMutablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_View_mutable_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_mutable_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_Mutable(),
				 true,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Source Edges feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceEdgesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_View_sourceEdges_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_sourceEdges_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_SourceEdges(),
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Target Edges feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetEdgesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_View_targetEdges_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_targetEdges_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_TargetEdges(),
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addElementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_View_element_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_element_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_Element(),
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Diagram feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDiagramPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_View_diagram_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_diagram_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_Diagram(),
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(NotationPackage.eINSTANCE.getView_PersistedChildren());
			childrenFeatures.add(NotationPackage.eINSTANCE.getView_Styles());
			childrenFeatures.add(NotationPackage.eINSTANCE.getView_TransientChildren());
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This adds a property descriptor for the Styles feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected void addStylesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getString("_UI_View_styles_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_View_styles_feature", "_UI_View_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 NotationPackage.eINSTANCE.getView_Styles(),
				 false));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		View view = (View)object;
		return getString("_UI_View_type") + " " + view.isVisible(); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(View.class)) {
			case NotationPackage.VIEW__VISIBLE:
			case NotationPackage.VIEW__TYPE:
			case NotationPackage.VIEW__MUTABLE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case NotationPackage.VIEW__PERSISTED_CHILDREN:
			case NotationPackage.VIEW__STYLES:
			case NotationPackage.VIEW__TRANSIENT_CHILDREN:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_PersistedChildren(),
				 NotationFactory.eINSTANCE.createNode()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createFillStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createLineStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createFontStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createTitleStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createSortingStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createDescriptionStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createRoutingStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createCanonicalStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createShapeStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createConnectorStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createPageStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createDrawerStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createGuideStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createFilteringStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createDiagramStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createImageStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_Styles(),
				 NotationFactory.eINSTANCE.createImageBufferStyle()));

		newChildDescriptors.add
			(createChildParameter
				(NotationPackage.eINSTANCE.getView_TransientChildren(),
				 NotationFactory.eINSTANCE.createNode()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreateChildText(Object owner, Object feature, Object child, Collection selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == NotationPackage.eINSTANCE.getView_PersistedChildren() ||
			childFeature == NotationPackage.eINSTANCE.getView_TransientChildren();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2", //$NON-NLS-1$
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return NotationEditPlugin.INSTANCE;
	}

}