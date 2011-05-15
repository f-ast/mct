/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2005.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.common.ui.internal.dialogs;

import java.util.Hashtable;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.common.ui.dialogs.SelectableElement;
import org.eclipse.gmf.runtime.common.ui.dialogs.SelectedType;
import org.eclipse.gmf.runtime.common.ui.internal.CommonUIDebugOptions;
import org.eclipse.gmf.runtime.common.ui.internal.CommonUIPlugin;
import org.eclipse.gmf.runtime.common.ui.internal.CommonUIStatusCodes;
import org.eclipse.gmf.runtime.common.ui.internal.l10n.ResourceManager;
import org.eclipse.gmf.runtime.common.ui.util.OverlayImageDescriptor;

/**
 * This label provider overlays a selected or unselected icon on another icon.
 * It supports three states as defined in SelectedType, which are SELECTED,
 * UNSELECTED. and LEAVE. It decorates SELECTED with a green plus sign. It
 * decorates UNSELECTED with a red x. It doesn't do anything with LEAVE.
 * 
 * A mix of SELECTED, UNSELECTED, and LEAVE gives a greyed out parent. You could
 * call this this a fourth state, but it is not a real state.
 * 
 * @author wdiu, Wayne Diu
 */

public class SelectableElementsTriStateLabelProvider
	extends SelectableElementsLabelProvider {

	/**
	 * Image pool, this is not reusing the superclass' image pool because they
	 * are supposed to be different.
	 */
	private Hashtable imagePool = new Hashtable();

	/**
	 * Green plus file
	 */
	private static final String SELECTED_ICON = "checkboxselected.gif"; //$NON-NLS-1$

	/**
	 * Red x file
	 */
	private static final String UNSELECTED_ICON = "checkboxunselected.gif"; //$NON-NLS-1$

	/**
	 * Greyed file
	 */
	private static final String GREYED_ICON = "checkboxgreyed.gif"; //$NON-NLS-1$

	/**
	 * Cleared checkbox file
	 */
	private static final String CHECKBOX_ICON = "checkboxcleared.gif"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		Image image = super.getImage(element);

		//overlay if image is not null
		if (image != null) {

			SelectableElement selectableElement = (SelectableElement) element;
			//super asserted on this

			if ((selectableElement.getNumberOfChildren() > 0 && areAllChildrenOfType(
				selectableElement, SelectedType.SELECTED))
				|| (selectableElement.getNumberOfChildren() == 0 && selectableElement
					.getSelectedType() == SelectedType.SELECTED)) {

				String key = "s" + image.hashCode(); //$NON-NLS-1$ 
				Image overlayImage = (Image) imagePool.get(key);

				if (overlayImage == null) {
					try {
						overlayImage = overlayImage(image, ResourceManager
							.getInstance().getImageDescriptor(SELECTED_ICON));
						imagePool.put(key, overlayImage);
					} catch (Exception e) {
						Trace.catching(CommonUIPlugin.getDefault(),
							CommonUIDebugOptions.EXCEPTIONS_CATCHING,
							getClass(), "getImage", e); //$NON-NLS-1$
						//don't just return the image,
						//that will be more confusing in case of failure
						Log.error(CommonUIPlugin.getDefault(),
							CommonUIStatusCodes.RESOURCE_FAILURE,
							"Failed to load SELECTED_ICON overlay", e); //$NON-NLS-1$
						return null;
					}
				}
				return overlayImage;

			}

			else if ((selectableElement.getNumberOfChildren() > 0 && areAllChildrenOfType(
				selectableElement, SelectedType.UNSELECTED))
				|| (selectableElement.getNumberOfChildren() == 0 && selectableElement
					.getSelectedType() == SelectedType.UNSELECTED)) {
				String key = "u" + image.hashCode(); //$NON-NLS-1$ 
				Image overlayImage = (Image) imagePool.get(key);

				if (overlayImage == null) {
					try {
						overlayImage = overlayImage(image, ResourceManager
							.getInstance().getImageDescriptor(UNSELECTED_ICON));
						imagePool.put(key, overlayImage);
					} catch (Exception e) {
						//don't just return the image,
						//that will be more confusing in case of failure
						Trace.catching(CommonUIPlugin.getDefault(),
							CommonUIDebugOptions.EXCEPTIONS_CATCHING,
							getClass(), "getImage", e); //$NON-NLS-1$
						Log.error(CommonUIPlugin.getDefault(),
							CommonUIStatusCodes.RESOURCE_FAILURE,
							"Failed to load UNSELECTED_ICON overlay", e); //$NON-NLS-1$
						return null;
					}
				}
				return overlayImage;

			}

			else if ((selectableElement.getNumberOfChildren() > 0 && areAllChildrenOfType(
				selectableElement, SelectedType.LEAVE))
				|| (selectableElement.getNumberOfChildren() == 0 && selectableElement
					.getSelectedType() == SelectedType.LEAVE)) {

				String key = "c" + image.hashCode(); //$NON-NLS-1$ 
				Image overlayImage = (Image) imagePool.get(key);

				if (overlayImage == null) {
					try {
						overlayImage = overlayImage(image, ResourceManager
							.getInstance().getImageDescriptor(CHECKBOX_ICON));
						imagePool.put(key, overlayImage);
					} catch (Exception e) {
						//don't just return the image,
						//that will be more confusing in case of failure
						Trace.catching(CommonUIPlugin.getDefault(),
							CommonUIDebugOptions.EXCEPTIONS_CATCHING,
							getClass(), "getImage", e); //$NON-NLS-1$
						Log.error(CommonUIPlugin.getDefault(),
							CommonUIStatusCodes.RESOURCE_FAILURE,
							"Failed to load CHECKBOX_ICON overlay", e); //$NON-NLS-1$
						return null;
					}
				}
				return overlayImage;
			}

			else {//mix, since has children

				assert (selectableElement.getNumberOfChildren() > 0);

				String key = "g" + image.hashCode(); //$NON-NLS-1$ 
				Image overlayImage = (Image) imagePool.get(key);

				if (overlayImage == null) {
					try {
						overlayImage = overlayImage(image, ResourceManager
							.getInstance().getImageDescriptor(GREYED_ICON));
						imagePool.put(key, overlayImage);
					} catch (Exception e) {
						//don't just return the image,
						//that will be more confusing in case of failure
						Trace.catching(CommonUIPlugin.getDefault(),
							CommonUIDebugOptions.EXCEPTIONS_CATCHING,
							getClass(), "getImage", e); //$NON-NLS-1$
						Log.error(CommonUIPlugin.getDefault(),
							CommonUIStatusCodes.RESOURCE_FAILURE,
							"Failed to load GREYED_ICON overlay", e); //$NON-NLS-1$
						return null;
					}
				}
				return overlayImage;

			}
		}

		return image;
	}

	/**
	 * Overlays given base image with given overlay image ImageDescriptor
	 * 
	 * Based on overlayImage from ModelExplorerDecorator
	 * 
	 * @param srcImage
	 *            base image
	 * @param imageDesc
	 *            overlay ImageDescriptor
	 * @return Image the new overlay image
	 */
	private Image overlayImage(Image srcImage, ImageDescriptor imageDesc) {
		OverlayImageDescriptor overlayDesc = new OverlayImageDescriptor(
			srcImage, imageDesc, srcImage.getImageData().width, srcImage
				.getImageData().height);

		Image destImage = overlayDesc.createImage();
		assert null != destImage;

		return destImage;
	}

	/**
	 * Returns if the SelectableElement's children all have the given type. This
	 * function is recursive.
	 * 
	 * @param selectableElement
	 *            the element to check if all children have the selectedType
	 * @param selectedType
	 *            the SelectedType that all children must have to return true
	 * @return boolean true if all children are selectedType, false if they are
	 *         not
	 */
	private boolean areAllChildrenOfType(SelectableElement selectableElement,
			SelectedType selectedType) {
		int numberOfChildren = selectableElement.getNumberOfChildren();
		assert (numberOfChildren > 0);
		for (int i = 0; i < numberOfChildren; i++) {
			SelectableElement element = selectableElement.getChild(i);
			if ((element.getNumberOfChildren() == 0 && element
				.getSelectedType() != selectedType)
				|| (element.getNumberOfChildren() > 0 && !areAllChildrenOfType(
					element, selectedType)))
				return false;
		}
		return true;
	}

	/**
	 * Also frees up the images that were created
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#dispose()
	 */
	public void dispose() {
		dispose(imagePool);
		super.dispose();
	}

	/**
	 * Constructor that calls its superclass
	 */
	public SelectableElementsTriStateLabelProvider() {
		//randomly select an image, assume they're all the same size
		//if your icon size is > short, then that would be very strange
		//change it to int if you think that it will be a problem
		super(
			(ResourceManager.getInstance().getImageDescriptor(SELECTED_ICON) == null) ? (short) 0
				: (short) (ResourceManager.getInstance().getImageDescriptor(
					SELECTED_ICON).getImageData().width), (short) 0);
	}
}