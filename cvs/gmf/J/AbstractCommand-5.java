/******************************************************************************
 * Copyright (c) 2002, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.common.core.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IOperationApprover;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.common.core.internal.CommonCoreDebugOptions;
import org.eclipse.gmf.runtime.common.core.internal.CommonCorePlugin;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;

/**
 * An abstract superclass for GMF {@link IUndoableOperation}s that do not
 * modify EMF model resources.
 * <p>
 * The operation provides a list of {@link IFile}s that are expected to be modified when
 * the operation is executed, undone or redone. An {@link IOperationApprover} is
 * registered with the {@link OperationHistoryFactory#getOperationHistory()} to
 * validate the modification to these resources.
 * <p>
 * This class is meant to be extended by clients.
 * 
 * @author khussey
 * @author ldamus
 *
 * @see org.eclipse.gmf.runtime.common.core.command.ICommand
 * @canBeSeenBy %partners
 */
public abstract class AbstractCommand extends AbstractOperation
		implements ICommand {

	private final List affectedFiles;

	private CommandResult commandResult;
    
    /**
     * Initializes me with a label.
     * 
     * @param label
     *            the operation label
     */
    public AbstractCommand(String label) {
        this(label, null);
    }

	/**
	 * Initializes me with a label and a list of {@link IFile}s that anticipate modifying
	 * when I am executed, undone or redone.
	 * 
	 * @param label
	 *            the operation label
	 * @param affectedFiles
	 *            the list of affected {@link IFile}s; may be <code>null</code>
	 */
	public AbstractCommand(String label, List affectedFiles) {
		super(label);

		if (affectedFiles == null) {
			this.affectedFiles = new ArrayList();

		} else {
			this.affectedFiles = affectedFiles;
		}
	}

	/**
	 * Returns the {@link IFile}s that may be modified when the operation is
	 * executed, undone or redone.
	 */
	public List getAffectedFiles() {
		return affectedFiles;
	}

	// Documentation copied from the interface
	public CommandResult getCommandResult() {
		return commandResult;
	}

	/**
	 * Sets the command result.
	 * 
	 * @param result
	 *            the new result for this command.
	 */
	protected final void setResult(CommandResult result) {
		this.commandResult = result;
	}

	// Documentation copied from the interface
	public ICommand compose(IUndoableOperation operation) {

		if (operation != null) {

			return new CompositeCommand(getLabel()).compose(this)
					.compose(operation);
		}
		return this;
	}
    
	// Documentation copied from the interface
    public ICommand reduce() {
        return this;
    }

	/**
	 * Delegates to {@link #doExecuteWithResult(IProgressMonitor, IAdaptable)} and sets
	 * the command result.
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {

		CommandResult result = doExecuteWithResult(monitor, info);
		setResult(result);
		return result.getStatus();
	}

	/**
	 * Performs the actual work of executing this command. Subclasses must
	 * implement this method to perform some operation.
	 * 
	 * @param progressMonitor
	 *            the progress monitor provided by the operation history
	 * @param info
	 *            the IAdaptable (or <code>null</code>) provided by the
	 *            caller in order to supply UI information for prompting the
	 *            user if necessary. When this parameter is not
	 *            <code>null</code>, it should minimally contain an adapter
	 *            for the org.eclipse.swt.widgets.Shell.class.
	 * 
	 * @return The result of executing this command.
	 * 
	 * @throws ExecutionException
	 *             if, for some reason, I fail to complete the operation
	 */
	protected abstract CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException;

	/**
	 * Delegates to {@link #doRedoWithResult(IProgressMonitor, IAdaptable)} and sets the
	 * command result.
	 */
	public IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {

		CommandResult result = doRedoWithResult(monitor, info);
		setResult(result);
		return result.getStatus();
	}

	/**
	 * Performs the actual work of redoing this command. Subclasses must
	 * implement this method to perform the redo.
	 * 
	 * @param progressMonitor
	 *            the progress monitor provided by the operation history
	 * @param info
	 *            the IAdaptable (or <code>null</code>) provided by the
	 *            caller in order to supply UI information for prompting the
	 *            user if necessary. When this parameter is not
	 *            <code>null</code>, it should minimally contain an adapter
	 *            for the org.eclipse.swt.widgets.Shell.class.
	 * 
	 * @return The result of redoing this command.
	 * 
	 * @throws ExecutionException
	 *             on failure to redo
	 */
	protected abstract CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException;

	/**
	 * Delegates to {@link #doUndoWithResult(IProgressMonitor, IAdaptable)} and sets the
	 * command result.
	 */
	public IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {

		CommandResult result = doUndoWithResult(monitor, info);
		setResult(result);
		return result.getStatus();
	}

	/**
	 * Performs the actual work of undoing this command. Subclasses must
	 * implement this method to perform the undo.
	 * 
	 * @param progressMonitor
	 *            the progress monitor provided by the operation history
	 * @param info
	 *            the IAdaptable (or <code>null</code>) provided by the
	 *            caller in order to supply UI information for prompting the
	 *            user if necessary. When this parameter is not
	 *            <code>null</code>, it should minimally contain an adapter
	 *            for the org.eclipse.swt.widgets.Shell.class.
	 * 
	 * @return The result of undoing this command.
	 * 
	 * @throws ExecutionException
	 *             on failure to undo
	 */
	protected abstract CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException;

    public void dispose() {
        super.dispose();
        
        // clear my contexts
        IUndoContext[] contexts = getContexts();
        for (int i = 0; i < contexts.length; i++) {
            removeContext(contexts[i]);
        }
    }
    
    //
    // TODO The following methods to be removed when deprecated API on ICommand
    // is removed. Deprecated API must be implemented to comply with deprecated
    // methods on the ICommand interface.
    // 

    /**
     * Returns the affected {@link IFile}s.
     * 
     * @deprecated Implemented for backwards compatibility. Use
     *             {@link #getAffectedFiles()} instead.
     */
    public Collection getAffectedObjects() {
        return getAffectedFiles();
    }

    /**
     * Returns <code>null</code>.
     * 
     * @deprecated File validation is now done through a
     *             {@link IOperationApprover} registered with with the
     *             {@link OperationHistoryFactory#getOperationHistory()}. No
     *             need to return a validator for backwards compatilibity.
     */
    public CMValidator getValidator() {
        return null;
    }

    /**
     * Returns <code>false</code>.
     * 
     * @deprecated File validation is now done through a
     *             {@link IOperationApprover} registered with with the
     *             {@link OperationHistoryFactory#getOperationHistory()}. No
     *             need to calculate the answer for backwards compatilibity.
     */
    public boolean involvesReadOnlyNonWorkSpaceFiles() {
        return false;
    }

    /**
     * Delegates to {@link #canExecute()}.
     * 
     * @deprecated Implemented for backwards compatibility. Use
     *             {@link #canExecute()} instead.
     */
    public boolean isExecutable() {
        return canExecute();
    }

    /**
     * Delegates to {@link #canRedo()}.
     * 
     * @deprecated Implemented for backwards compatibility. Use
     *             {@link #canRedo()} instead.
     */
    public boolean isRedoable() {
        return canRedo();
    }

    /**
     * Delegates to {@link #canUndo()}.
     * 
     * @deprecated Implemented for backwards compatibility. Use
     *             {@link #canUndo()} instead.
     */
    public boolean isUndoable() {
        return canUndo();
    }

    /**
     * Delegates to {@link #execute(IProgressMonitor, IAdaptable)} and logs any
     * {@link ExecutionException} that occurs.
     * 
     * @deprecated Implemented for backwards compatibility. Use
     *             {@link #execute(IProgressMonitor, IAdaptable)} instead.
     */
    public void execute(IProgressMonitor progressMonitor) {

        try {
            execute(progressMonitor, null);

        } catch (ExecutionException e) {
            handle(e);
        }
    }

    /**
     * Delegates to {@link #redo(IProgressMonitor, IAdaptable)} and logs any
     * {@link ExecutionException} that occurs.
     * 
     * @deprecated Implemented for backwards compatibility. Use
     *             {@link #redo(IProgressMonitor, IAdaptable)} instead.
     */
    public void redo() {
        try {
            redo(new NullProgressMonitor(), null);

        } catch (ExecutionException e) {
            handle(e);
        }
    }

    /**
     * Delegates to {@link #undo(IProgressMonitor, IAdaptable)} and logs any
     * {@link ExecutionException} that occurs.
     * 
     * @deprecated Implemented for backwards compatibility. Use
     *             {@link #undo(IProgressMonitor, IAdaptable)} instead.
     */
    public void undo() {
        try {
            undo(new NullProgressMonitor(), null);

        } catch (ExecutionException e) {
            handle(e);
        }
    }

    /**
     * Handles the specified exception by logging, tracing and setting the
     * command result to an error.
     * 
     * @param exception
     *            The exception to be handled.
     * @deprecated Supports deprecated implementation
     */
    protected void handle(Exception exception) {
        Trace.catching(CommonCorePlugin.getDefault(),
                CommonCoreDebugOptions.EXCEPTIONS_CATCHING, getClass(),
                "handle", exception); //$NON-NLS-1$

        setResult(CommandResult.newErrorCommandResult(exception));

        Log.log(CommonCorePlugin.getDefault(), getCommandResult().getStatus());
    }
}