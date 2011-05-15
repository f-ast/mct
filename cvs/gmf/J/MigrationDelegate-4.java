/*
 * Copyright (c) 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Borland - initial API and implementation
 */
package org.eclipse.gmf.internal.map.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.internal.common.migrate.MigrationHelperDelegateImpl;
import org.eclipse.gmf.mappings.FeatureLabelMapping;
import org.eclipse.gmf.mappings.GMFMapPackage;
import org.eclipse.gmf.mappings.LabelMapping;
import org.eclipse.gmf.mappings.MappingEntry;

class MigrationDelegate extends MigrationHelperDelegateImpl {
	private Collection<FeatureLabelMapping> myFeatureLabelMappings;
	private Collection<String> myBackwardSupportedURIs;
	
	MigrationDelegate() {
	}

	void init() {
		registerNarrowReferenceType(GMFMapPackage.eINSTANCE.getFeatureSeqInitializer_Initializers(), GMFMapPackage.eINSTANCE.getFeatureValueSpec());
		registerNarrowReferenceType(GMFMapPackage.eINSTANCE.getMappingEntry_LabelMappings(), GMFMapPackage.eINSTANCE.getFeatureLabelMapping());
		myFeatureLabelMappings = null;
	}

	@Override
	public boolean isOldVersionDetected(String uriString) {
		return !getMetamodelNsURI().equals(uriString) && getBackwardSupportedURIs().contains(uriString);
	}

	@Override
	public void postProcess() {
		if (myFeatureLabelMappings == null) {
			return;
		}
		for (FeatureLabelMapping mapping : getSavedFeatureLabelMappings()) {
			if (mapping.getFeatures().isEmpty()) {
				MappingEntry entry = mapping.getMapEntry();
				EList<LabelMapping> labelMappings = entry.getLabelMappings();
				int originalIndex = labelMappings.indexOf(mapping);
				if (originalIndex != -1) {
					LabelMapping newMapping = GMFMapPackage.eINSTANCE.getGMFMapFactory().createLabelMapping();
					newMapping.setDiagramLabel(mapping.getDiagramLabel());
					if (mapping.isReadOnly()) {
						newMapping.setReadOnly(true);
					}
					labelMappings.set(originalIndex, newMapping);
				}
			}
		}
	}

	@Override
	public void processObject(EObject result) {
		if (result instanceof FeatureLabelMapping) {
			getSavedFeatureLabelMappings().add((FeatureLabelMapping) result);
		}
	}
	
	private Collection<FeatureLabelMapping> getSavedFeatureLabelMappings() {
		if (myFeatureLabelMappings == null) {
			myFeatureLabelMappings = new ArrayList<FeatureLabelMapping>();
		}
		return myFeatureLabelMappings;
	}

	protected Collection<String> getBackwardSupportedURIs() {
		if (myBackwardSupportedURIs == null) {
			myBackwardSupportedURIs = Arrays.asList(new String[] {
					"http://www.eclipse.org/gmf/2005/mappings", //$NON-NLS-1$
					"http://www.eclipse.org/gmf/2005/mappings/2.0" //$NON-NLS-1$
			});
		}
		return myBackwardSupportedURIs;
	}

	protected String getMetamodelNsURI() {
		return GMFMapPackage.eNS_URI;
	}
}