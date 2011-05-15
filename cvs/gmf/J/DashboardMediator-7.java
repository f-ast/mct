/*
 * Copyright (c) 2006 Eclipse.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 */
package org.eclipse.gmf.internal.bridge.ui.dashboard;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.presentation.EcoreModelWizard;
import org.eclipse.emf.importer.ui.EMFModelWizard;
import org.eclipse.emf.importer.ui.GenModelReloadActionDelegate;
import org.eclipse.gmf.codegen.gmfgen.presentation.GMFGenModelWizard;
import org.eclipse.gmf.gmfgraph.presentation.GMFGraphModelWizard;
import org.eclipse.gmf.internal.bridge.wizards.GMFGraphSimpleModelWizard;
import org.eclipse.gmf.internal.bridge.wizards.GMFToolSimpleModelWizard;
import org.eclipse.gmf.internal.bridge.wizards.NewGMFMapModelWizard;
import org.eclipse.gmf.internal.codegen.popup.actions.ExecuteTemplatesAction;
import org.eclipse.gmf.internal.codegen.popup.actions.TransformToGenModel;
import org.eclipse.gmf.internal.common.ui.FileSelector;
import org.eclipse.gmf.mappings.presentation.GMFMapModelWizard;
import org.eclipse.gmf.tooldef.presentation.GMFToolModelWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author dstadnik
 */
public class DashboardMediator {

	private static final boolean STRICT = true;

	private DashboardFigure view;

	private Shell shell;

	private IProject project;

	private DashboardState state;

	public DashboardMediator(Shell shell) {
		state = new DashboardState();
		this.shell = shell;
	}

	public void setView(DashboardFigure view) {
		this.view = view;
		view.getDGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_0, new SelectDGMAction()));
		view.getDGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_1, new EditDGMAction()));
		view.getDGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_2, new ReloadDGMAction()));
		view.getGDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_0, new SelectGDMAction()));
		view.getGDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_1, new EditGDMAction()));
		view.getGDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_3, new CreateGDMAction()));
		view.getDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_0, new SelectDMAction()));
		view.getDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_1, new EditDMAction()));
		view.getDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_3, new CreateDMAction()));
		view.getTDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_0, new SelectTDMAction()));
		view.getTDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_1, new EditTDMAction()));
		view.getTDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_3, new CreateTDMAction()));
		view.getMMFigure().addAction(createLinkFigure(Messages.DashboardMediator_0, new SelectMMAction()));
		view.getMMFigure().addAction(createLinkFigure(Messages.DashboardMediator_1, new EditMMAction()));
		view.getMMFigure().addAction(createLinkFigure(Messages.DashboardMediator_3, new CreateMMAction()));
		view.getGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_0, new SelectGMAction()));
		view.getGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_1, new EditGMAction()));
		view.getGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_3, new CreateGMAction()));
		view.getGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_5, new GenerateDEAction()));
		view.getDM2DGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_4, new DeriveDGMAction()));
		view.getDM2GDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_4, new DeriveGDMAction()));
		view.getDM2TDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_4, new DeriveTDMAction()));
		view.getDM2MMFigure().addAction(createLinkFigure(Messages.DashboardMediator_6, new CombineMMAction()));
		view.getMM2GMFigure().addAction(createLinkFigure(Messages.DashboardMediator_7, new GenerateGMAction()));
		updateStatus();
	}

	public IFigure createLinkFigure(String text, DashboardAction action) {
		HyperlinkFigure linkFigure = new HyperlinkFigure(action);
		linkFigure.setText(text);
		return linkFigure;
	}

	protected IFile getFile(String fileName) {
		return project.getFile(new Path(fileName));
	}

	protected String getName(IFile file) {
		if (file == null) {
			return null;
		}
		return file.getProjectRelativePath().toString();
	}

	public IProject getProject() {
		return project;
	}

	public DashboardState getState() {
		return state;
	}

	public void setProjectAndState(IProject project, DashboardState state) {
		this.project = project;
		this.state = state;
		if (this.state == null) {
			this.state = new DashboardState();
		}
		updateStatus();
	}

	protected void updateStatus() {
		if (project == null) {
			view.getStatusLine(0).setText(Messages.DashboardMediator_8);
			view.getStatusLine(1).setText(""); //$NON-NLS-1$
		} else {
			view.getStatusLine(0).setText(MessageFormat.format(Messages.DashboardMediator_15, new Object[] { project.getName() }));
			double done = (double) state.getSpecifiedModelsCount() / state.getModelsCount();
			view.getStatusLine(1).setText(MessageFormat.format(Messages.DashboardMediator_16, new Object[] { new Double(done) }));
		}
		setModelName(view.getDGMFigure(), state.dgmFileName);
		setModelName(view.getGDMFigure(), state.gdmFileName);
		setModelName(view.getDMFigure(), state.dmFileName);
		setModelName(view.getTDMFigure(), state.tdmFileName);
		setModelName(view.getMMFigure(), state.mmFileName);
		setModelName(view.getGMFigure(), state.gmFileName);
		view.repaint(); // update hyperlinks
	}

	protected void setModelName(ModelFigure figure, String name) {
		figure.setName(name);
	}

	protected abstract class SelectFileAction implements DashboardAction {

		public boolean isEnabled() {
			return project != null;
		}

		public void run() {
			IFile file = null;
			String fileName = getFileName();
			if (fileName != null) {
				file = getFile(fileName);
			}
			file = FileSelector.selectFile(shell, getFigure().getDescription(), project, file, getFileExtension());
			setFileName(getName(file));
			updateStatus();
		}

		protected abstract ModelFigure getFigure();

		protected abstract String getFileName();

		protected abstract void setFileName(String fileName);

		protected abstract String getFileExtension();
	}

	protected abstract class EditFileAction implements DashboardAction {

		public boolean isEnabled() {
			return project != null && getFileName() != null;
		}

		public void run() {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			IFile modelFile = getFile(getFileName());
			try {
				String fileName = modelFile.getFullPath().toString();
				String editorId = workbench.getEditorRegistry().getDefaultEditor(fileName).getId();
				page.openEditor(new FileEditorInput(modelFile), editorId);
			} catch (PartInitException pie) {
				String msg = MessageFormat.format(Messages.DashboardMediator_17, new Object[] { getFileName() });
				MessageDialog.openError(workbenchWindow.getShell(), msg, pie.getMessage());
			}
		}

		protected abstract String getFileName();
	}

	protected abstract class RunWizardAction implements DashboardAction {

		private static final int SIZING_WIZARD_WIDTH = 500;

		private static final int SIZING_WIZARD_HEIGHT = 500;

		public boolean isEnabled() {
			return project != null;
		}

		public void run() {
			final IWizard wizard = createWizard();
			if (wizard instanceof IWorkbenchWizard) {
				((IWorkbenchWizard) wizard).init(PlatformUI.getWorkbench(), getSelection());
			}
			WizardDialog dialog = new WizardDialog(shell, wizard) {

				protected void finishPressed() {
					wizardFinished(wizard);
					super.finishPressed();
				}
			};
			dialog.create();
			dialog.getShell().setSize(Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x), SIZING_WIZARD_HEIGHT);
			dialog.open();
		}

		protected IStructuredSelection getSelection() {
			List<IFile> selection = new ArrayList<IFile>();
			addFile(selection, state.dmFileName);
			addFile(selection, state.dgmFileName);
			addFile(selection, state.gdmFileName);
			addFile(selection, state.tdmFileName);
			addFile(selection, state.mmFileName);
			addFile(selection, state.gmFileName);
			return new StructuredSelection(selection);
		}

		protected void addFile(List<IFile> files, String name) {
			if (name == null) {
				return;
			}
			files.add(project.getFile(name));
		}

		protected abstract IWizard createWizard();

		protected void wizardFinished(IWizard wizard) {
		}
	}

	private class SelectDGMAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return DashboardMediator.this.view.getDGMFigure();
		}

		protected String getFileName() {
			return state.dgmFileName;
		}

		protected void setFileName(String fileName) {
			state.dgmFileName = fileName;
		}

		protected String getFileExtension() {
			return "genmodel"; //$NON-NLS-1$
		}
	}

	private class SelectGDMAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return DashboardMediator.this.view.getGDMFigure();
		}

		protected String getFileName() {
			return state.gdmFileName;
		}

		protected void setFileName(String fileName) {
			state.gdmFileName = fileName;
		}

		protected String getFileExtension() {
			return "gmfgraph"; //$NON-NLS-1$
		}
	}

	private class SelectDMAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return DashboardMediator.this.view.getDMFigure();
		}

		protected String getFileName() {
			return state.dmFileName;
		}

		protected void setFileName(String fileName) {
			state.dmFileName = fileName;
		}

		protected String getFileExtension() {
			return "ecore"; //$NON-NLS-1$
		}
	}

	private class SelectTDMAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return DashboardMediator.this.view.getTDMFigure();
		}

		protected String getFileName() {
			return state.tdmFileName;
		}

		protected void setFileName(String fileName) {
			state.tdmFileName = fileName;
		}

		protected String getFileExtension() {
			return "gmftool"; //$NON-NLS-1$
		}
	}

	private class SelectMMAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return DashboardMediator.this.view.getMMFigure();
		}

		protected String getFileName() {
			return state.mmFileName;
		}

		protected void setFileName(String fileName) {
			state.mmFileName = fileName;
		}

		protected String getFileExtension() {
			return "gmfmap"; //$NON-NLS-1$
		}
	}

	private class SelectGMAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return DashboardMediator.this.view.getGMFigure();
		}

		protected String getFileName() {
			return state.gmFileName;
		}

		protected void setFileName(String fileName) {
			state.gmFileName = fileName;
		}

		protected String getFileExtension() {
			return "gmfgen"; //$NON-NLS-1$
		}
	}

	private class EditDGMAction extends EditFileAction {

		protected String getFileName() {
			return state.dgmFileName;
		}
	}

	private class EditGDMAction extends EditFileAction {

		protected String getFileName() {
			return state.gdmFileName;
		}
	}

	private class EditDMAction extends EditFileAction {

		protected String getFileName() {
			return state.dmFileName;
		}
	}

	private class EditTDMAction extends EditFileAction {

		protected String getFileName() {
			return state.tdmFileName;
		}
	}

	private class EditMMAction extends EditFileAction {

		protected String getFileName() {
			return state.mmFileName;
		}
	}

	private class EditGMAction extends EditFileAction {

		protected String getFileName() {
			return state.gmFileName;
		}
	}

	private class CreateGDMAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new GMFGraphModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((GMFGraphModelWizard) wizard).getModelFile();
			state.gdmFileName = getName(file);
			updateStatus();
		}
	}

	private class CreateDMAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new EcoreModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((EcoreModelWizard) wizard).getModelFile();
			state.dmFileName = getName(file);
			updateStatus();
		}
	}

	private class CreateTDMAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new GMFToolModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((GMFToolModelWizard) wizard).getModelFile();
			state.tdmFileName = getName(file);
			updateStatus();
		}
	}

	private class CreateMMAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new GMFMapModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((GMFMapModelWizard) wizard).getModelFile();
			state.mmFileName = getName(file);
			updateStatus();
		}
	}

	private class CreateGMAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new GMFGenModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((GMFGenModelWizard) wizard).getModelFile();
			state.gmFileName = getName(file);
			updateStatus();
		}
	}

	private class ReloadDGMAction implements DashboardAction {

		public boolean isEnabled() {
			return state.dgmFileName != null;
		}

		public void run() {
			IFile file = getFile(state.dgmFileName);
			GenModelReloadActionDelegate action = new GenModelReloadActionDelegate();
			IAction uiAction = new Action() {
			};
			action.selectionChanged(uiAction, new StructuredSelection(file));
			action.run(uiAction);
			updateStatus();
		}
	}

	private class DeriveDGMAction extends RunWizardAction {

		public boolean isEnabled() {
			return super.isEnabled() && state.dmFileName != null;
		}

		protected IWizard createWizard() {
			return new OpenEMFModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((OpenEMFModelWizard) wizard).getModelFile();
			state.dgmFileName = getName(file);
			updateStatus();
		}

		protected IStructuredSelection getSelection() {
			return new StructuredSelection(getFile(state.dmFileName));
		}
	}

	private static class OpenEMFModelWizard extends EMFModelWizard {

		public IFile getModelFile() {
			IPath path = genModelContainerPath.append(genModelFileName);
		    return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		}
	}

	private class DeriveGDMAction extends RunWizardAction {

		public boolean isEnabled() {
			return super.isEnabled() && state.dmFileName != null;
		}

		protected IWizard createWizard() {
			return new GMFGraphSimpleModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((GMFGraphSimpleModelWizard) wizard).getModelFile();
			state.gdmFileName = getName(file);
			updateStatus();
		}

		protected IStructuredSelection getSelection() {
			return new StructuredSelection(getFile(state.dmFileName));
		}
	}

	private class DeriveTDMAction extends RunWizardAction {

		public boolean isEnabled() {
			return super.isEnabled() && state.dmFileName != null;
		}

		protected IWizard createWizard() {
			return new GMFToolSimpleModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((GMFToolSimpleModelWizard) wizard).getModelFile();
			state.tdmFileName = getName(file);
			updateStatus();
		}

		protected IStructuredSelection getSelection() {
			return new StructuredSelection(getFile(state.dmFileName));
		}
	}

	private class CombineMMAction extends RunWizardAction {

		public boolean isEnabled() {
			return project != null && state.gdmFileName != null && state.dmFileName != null && state.tdmFileName != null;
		}

		protected IWizard createWizard() {
			return new NewGMFMapModelWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			IFile file = ((NewGMFMapModelWizard) wizard).getModelFile();
			state.mmFileName = getName(file);
			updateStatus();
		}
	}

	private class GenerateGMAction implements DashboardAction {

		public boolean isEnabled() {
			if (STRICT) {
				if (state.dmFileName == null || state.dgmFileName == null || state.tdmFileName == null) {
					return false;
				}
			}
			return project != null && state.mmFileName != null;
		}

		public void run() {
			IFile file = getFile(state.mmFileName);
			TransformToGenModel action = new TransformToGenModel();
			IAction uiAction = new Action() {
			};
			uiAction.setText(Messages.DashboardMediator_13);
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			action.setActivePart(uiAction, window.getPartService().getActivePart());
			action.selectionChanged(uiAction, new StructuredSelection(file));
			action.run(uiAction);
			IFile gfile = action.getGenModelFile();
			state.gmFileName = getName(gfile);
			updateStatus();
		}
	}

	private class GenerateDEAction implements DashboardAction {

		public boolean isEnabled() {
			if (STRICT) {
				if (state.dmFileName == null || state.dgmFileName == null) {
					return false;
				}
			}
			return project != null && state.gmFileName != null;
		}

		public void run() {
			IFile file = getFile(state.gmFileName);
			ExecuteTemplatesAction action = new ExecuteTemplatesAction();
			IAction uiAction = new Action() {
			};
			uiAction.setText(Messages.DashboardMediator_14);
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			action.setActivePart(uiAction, window.getPartService().getActivePart());
			action.selectionChanged(uiAction, new StructuredSelection(file));
			action.run(uiAction);
		}
	}
}