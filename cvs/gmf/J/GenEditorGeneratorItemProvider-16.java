/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmf.codegen.gmfgen.GMFGenFactory;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;

import org.eclipse.gmf.codegen.gmfgen.presentation.EditorPlugin;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenEditorGeneratorItemProvider
	extends ItemProviderAdapter
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
	public GenEditorGeneratorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addDomainGenModelPropertyDescriptor(object);
			addPackageNamePrefixPropertyDescriptor(object);
			addModelIDPropertyDescriptor(object);
			addSameFileForDiagramAndModelPropertyDescriptor(object);
			addDiagramFileExtensionPropertyDescriptor(object);
			addDomainFileExtensionPropertyDescriptor(object);
			addDynamicTemplatesPropertyDescriptor(object);
			addTemplateDirectoryPropertyDescriptor(object);
			addCopyrightTextPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Domain Gen Model feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDomainGenModelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_domainGenModel_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_domainGenModel_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_DomainGenModel(),
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Package Name Prefix feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPackageNamePrefixPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_packageNamePrefix_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_packageNamePrefix_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_PackageNamePrefix(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Model ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModelIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_modelID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_modelID_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_ModelID(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Same File For Diagram And Model feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSameFileForDiagramAndModelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_sameFileForDiagramAndModel_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_sameFileForDiagramAndModel_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_SameFileForDiagramAndModel(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Diagram File Extension feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDiagramFileExtensionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_diagramFileExtension_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_diagramFileExtension_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_DiagramFileExtension(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Domain File Extension feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDomainFileExtensionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_domainFileExtension_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_domainFileExtension_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_DomainFileExtension(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dynamic Templates feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDynamicTemplatesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_dynamicTemplates_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_dynamicTemplates_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_DynamicTemplates(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Template Directory feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTemplateDirectoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_templateDirectory_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_templateDirectory_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_TemplateDirectory(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Copyright Text feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCopyrightTextPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenEditorGenerator_copyrightText_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenEditorGenerator_copyrightText_feature", "_UI_GenEditorGenerator_type"),
				 GMFGenPackage.eINSTANCE.getGenEditorGenerator_CopyrightText(),
				 true,
				 true,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Audits());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Metrics());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Diagram());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Plugin());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Editor());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Navigator());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_PropertySheet());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Application());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenEditorGenerator_ExpressionProviders());
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns GenEditorGenerator.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GenEditorGenerator"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((GenEditorGenerator)object).getPackageNamePrefix();
		return label == null || label.length() == 0 ?
			getString("_UI_GenEditorGenerator_type") :
			getString("_UI_GenEditorGenerator_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(GenEditorGenerator.class)) {
			case GMFGenPackage.GEN_EDITOR_GENERATOR__PACKAGE_NAME_PREFIX:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__MODEL_ID:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__SAME_FILE_FOR_DIAGRAM_AND_MODEL:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__DIAGRAM_FILE_EXTENSION:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__DOMAIN_FILE_EXTENSION:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__DYNAMIC_TEMPLATES:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__TEMPLATE_DIRECTORY:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__COPYRIGHT_TEXT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case GMFGenPackage.GEN_EDITOR_GENERATOR__AUDITS:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__METRICS:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__DIAGRAM:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__PLUGIN:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__EDITOR:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__NAVIGATOR:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__PROPERTY_SHEET:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__APPLICATION:
			case GMFGenPackage.GEN_EDITOR_GENERATOR__EXPRESSION_PROVIDERS:
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
	@Override
	protected void collectNewChildDescriptors(Collection<CommandParameter> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Audits(),
				 GMFGenFactory.eINSTANCE.createGenAuditRoot()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Metrics(),
				 GMFGenFactory.eINSTANCE.createGenMetricContainer()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Diagram(),
				 GMFGenFactory.eINSTANCE.createGenDiagram()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Plugin(),
				 GMFGenFactory.eINSTANCE.createGenPlugin()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Editor(),
				 GMFGenFactory.eINSTANCE.createGenEditorView()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Navigator(),
				 GMFGenFactory.eINSTANCE.createGenNavigator()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_PropertySheet(),
				 GMFGenFactory.eINSTANCE.createGenPropertySheet()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_Application(),
				 GMFGenFactory.eINSTANCE.createGenApplication()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenEditorGenerator_ExpressionProviders(),
				 GMFGenFactory.eINSTANCE.createGenExpressionProviderContainer()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return EditorPlugin.INSTANCE;
	}

}