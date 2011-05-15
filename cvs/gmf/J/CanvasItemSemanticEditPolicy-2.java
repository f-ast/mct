package org.eclipse.gmf.graphdef.editor.edit.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;

import org.eclipse.gmf.graphdef.editor.providers.GMFGraphElementTypes;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

/**
 * @generated
 */
public class CanvasItemSemanticEditPolicy extends GMFGraphBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (GMFGraphElementTypes.Compartment_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(GMFGraphPackage.eINSTANCE.getCanvas_Compartments());
			}
			return getMSLWrapper(new CreateCompartment_1001Command(req));
		}
		if (GMFGraphElementTypes.Node_1002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(GMFGraphPackage.eINSTANCE.getCanvas_Nodes());
			}
			return getMSLWrapper(new CreateNode_1002Command(req));
		}
		if (GMFGraphElementTypes.Connection_1003 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(GMFGraphPackage.eINSTANCE.getCanvas_Connections());
			}
			return getMSLWrapper(new CreateConnection_1003Command(req));
		}
		if (GMFGraphElementTypes.FigureGallery_1004 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(GMFGraphPackage.eINSTANCE.getCanvas_Figures());
			}
			return getMSLWrapper(new CreateFigureGallery_1004Command(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	private static class CreateCompartment_1001Command extends CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateCompartment_1001Command(CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return GMFGraphPackage.eINSTANCE.getCanvas();
		};

		/**
		 * @generated
		 */
		protected EObject getElementToEdit() {
			EObject container = ((CreateElementRequest) getRequest()).getContainer();
			if (container instanceof View) {
				container = ((View) container).getElement();
			}
			return container;
		}
	}

	/**
	 * @generated
	 */
	private static class CreateNode_1002Command extends CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateNode_1002Command(CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return GMFGraphPackage.eINSTANCE.getCanvas();
		};

		/**
		 * @generated
		 */
		protected EObject getElementToEdit() {
			EObject container = ((CreateElementRequest) getRequest()).getContainer();
			if (container instanceof View) {
				container = ((View) container).getElement();
			}
			return container;
		}
	}

	/**
	 * @generated
	 */
	private static class CreateConnection_1003Command extends CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateConnection_1003Command(CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return GMFGraphPackage.eINSTANCE.getCanvas();
		};

		/**
		 * @generated
		 */
		protected EObject getElementToEdit() {
			EObject container = ((CreateElementRequest) getRequest()).getContainer();
			if (container instanceof View) {
				container = ((View) container).getElement();
			}
			return container;
		}
	}

	/**
	 * @generated
	 */
	private static class CreateFigureGallery_1004Command extends CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateFigureGallery_1004Command(CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return GMFGraphPackage.eINSTANCE.getCanvas();
		};

		/**
		 * @generated
		 */
		protected EObject getElementToEdit() {
			EObject container = ((CreateElementRequest) getRequest()).getContainer();
			if (container instanceof View) {
				container = ((View) container).getElement();
			}
			return container;
		}
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getMSLWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}
	}
}