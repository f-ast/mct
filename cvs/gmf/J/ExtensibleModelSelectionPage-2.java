package org.eclipse.gmf.internal.common.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Composite;

/**
 * @author dstadnik
 */
public class ExtensibleModelSelectionPage extends ModelSelectionPage {

	private Map extensions; // id -> extension

	public ExtensibleModelSelectionPage(String pageId) {
		super(pageId);
		extensions = new LinkedHashMap();
		addExtensions();
	}

	protected void addExtensions() {
	}

	public void addExtension(String id, ModelSelectionPageExtension extension) {
		assert id != null;
		assert extension != null;
		assert getControl() == null;
		extensions.put(id, extension);
	}

	public ModelSelectionPageExtension getExtension(String id) {
		return (ModelSelectionPageExtension) extensions.get(id);
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		Composite control = (Composite) getControl();
		for (Iterator it = extensions.values().iterator(); it.hasNext();) {
			((ModelSelectionPageExtension) it.next()).createControl(control);
		}
	}

	protected void setResource(Resource resource) {
		super.setResource(resource);
		for (Iterator it = extensions.values().iterator(); it.hasNext();) {
			((ModelSelectionPageExtension) it.next()).setResource(resource);
		}
	}
}