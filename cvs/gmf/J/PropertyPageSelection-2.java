/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.emf.ui.properties.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertySource;

import org.eclipse.gmf.runtime.common.ui.services.action.filter.ActionFilterService;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.ui.properties.internal.l10n.EMFPropertiesResourceManager;

/**
 * A structured selection for use in the property page dialog.  It
 * adapts to the following types
 * <P>
 * <UL>
 * <LI>IElement</LI>
 * <LI>IStructuredSelection</LI>
 * <LI>IActionFilter</LI>
 * <LI>IPropertySource</LI>
 * <LI>IWorkbenchAdapter</LI>
 * <LI>CellEditor</LI>
 * </UL>
 * <P>
 * 
 * @author ldamus
 */
public class PropertyPageSelection
    implements IAdaptable, IStructuredSelection {

    /**
     * The text used in the title of the property dialog when
     * there is more than a single element selected.
     */
    private static final String SELECTED_ELEMENTS = EMFPropertiesResourceManager.getInstance().getString("PropertyPageSelection.PropertyDialog.selectedElementsTitle"); //$NON-NLS-1$

    /**
     * My selection
     */
    private final IStructuredSelection selection;

    /**
     * My workbench adapter 
     */
    private IWorkbenchAdapter workbenchAdapter;

    /**
     * Constructs a new instance with a single selected object
     * 
     * @param selectedObject The selected object
     */
    public PropertyPageSelection(Object selectedObject) {
        this(new ArrayList(Collections.singleton(selectedObject)));
    }

    /**
     * Constructs a new instance with a list of selected objects.
     * 
     * @param selectedObjects The objects that are selected
     */
    public PropertyPageSelection(List selectedObjects) {
        super();
        selection = new StructuredSelection(selectedObjects);
    }

    /**
     * Returns my selection
     * 
     * @return my selection
     */
    private IStructuredSelection getSelection() {
        return selection;
    }

    /**
     * Gets my workbench adapter. If I contain more than a
     * single selection, the label will be "Selected Elements". If
     * I contain a single selection, the label will be my IElement
     * name. This label is used in the title of the
     * {@link org.eclipse.ui.internal.dialogs.PropertyDialog}.
     * 
     * @return my workbench adapter
     */
    private IWorkbenchAdapter getWorkbenchAdapter() {

        if (workbenchAdapter == null) {
            workbenchAdapter = new IWorkbenchAdapter() {

                public Object[] getChildren(Object o) {
                    return null;
                }
                public ImageDescriptor getImageDescriptor(Object object) {
                    return null;
                }
                public String getLabel(Object o) {
                    if (getSelection().size() > 1) {
                        return SELECTED_ELEMENTS;
                    } else {
                        EObject element =
                            (EObject) getAdapter(EObject.class);
                        if (element != null) {
                            return ParserService.getInstance().getPrintString(
                                new EObjectAdapter(element));
                        } else {
                            return SELECTED_ELEMENTS;
                        }

                    }
                }
                public Object getParent(Object o) {
                    return null;
                }
            };
        }

        return workbenchAdapter;
    }

    /**
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(Class)
     */
    public Object getAdapter(Class adapter) {

        if (EObject.class.equals(adapter)) {
            return ((IAdaptable) getSelection().getFirstElement()).getAdapter(
            		EObject.class);

        } else if (IStructuredSelection.class.equals(adapter)) {
            return this;

        } else if (IActionFilter.class.equals(adapter)) {
            return ActionFilterService.getInstance();

        } else if (IPropertySource.class.equals(adapter)) {
            return ((IAdaptable) getSelection().getFirstElement()).getAdapter(
                IPropertySource.class);

        } else if (IWorkbenchAdapter.class.equals(adapter)) {
            return getWorkbenchAdapter();

        } else if (CellEditor.class.equals(adapter)) {
            return ((IAdaptable) getSelection().getFirstElement()).getAdapter(
                CellEditor.class);
        }
        return null;
    }

    /**
     * Returns whether this selection is empty.
     * 
     * @return <code>true</code> if this selection is empty,
     *   and <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return getSelection().isEmpty();
    }

    /**
     * Returns the first element in this selection, or <code>null</code>
     * if the selection is empty.
     *
     * @return an element, or <code>null</code> if none
     */
    public Object getFirstElement() {
        return getSelection().getFirstElement();
    }

    /**
     * Returns an iterator over the elements of this selection.
     *
     * @return an iterator over the selected elements
     */
    public Iterator iterator() {
        return getSelection().iterator();
    }

    /**
     * Returns the number of elements selected in this selection.
     *
     * @return the number of elements selected
     */
    public int size() {
        return getSelection().size();
    }

    /**
     * Returns the elements in this selection as an array.
     *
     * @return the selected elements as an array
     */
    public Object[] toArray() {
        return getSelection().toArray();
    }

    /**
     * Returns the elements in this selection as a <code>List</code>.
     *
     * @return the selected elements as a list
     */
    public List toList() {
        return getSelection().toList();
    }

}