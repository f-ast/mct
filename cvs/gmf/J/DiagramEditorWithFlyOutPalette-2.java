/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2005.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.parts;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteContextMenuProvider;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.views.palette.PalettePage;
import org.eclipse.gef.ui.views.palette.PaletteViewerPage;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.gmf.runtime.diagram.ui.internal.parts.ImageFileDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.tools.ConnectorCreationTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.CreationTool;

/**
 * A generic diagram editor with a palette.  This supports the palette moved from
 * the diagram to a palette view.  If the palette view is open than the
 * palette in the diagram will be removed.
 * @author choang
 */
public abstract class DiagramEditorWithFlyOutPalette
	extends DiagramEditor
{

	boolean fHasFlyoutPalette = true;
	public DiagramEditorWithFlyOutPalette() {
	}
	
	public DiagramEditorWithFlyOutPalette(boolean hasFlyout) {
		fHasFlyoutPalette = hasFlyout;
	}

	/**
	 * uncollapsed pinned palette state constant
	 */
	protected static final int UNCOLLAPSED_PINNED = 4;
	/**
	 * collapsed pinned palette state constant
	 */
	protected static final int COLLAPSED = 2;

	// Provider that creates palette viewer

	private PaletteViewerProvider provider;
	// Splitter the supports the moving of the palette in and out of the diagram
	// from a palette view.
	private FlyoutPaletteComposite splitter;
	// Page that supports the slurping in and out of the palette from the diagram to
	// and from a palette view.  The palette view is like any other view it can be moved
	// around.  Similiar in docking functionality as the outline view.
	private CustomPalettePage page;

	protected void initializeGraphicalViewer() {
		if(fHasFlyoutPalette) {
			splitter.hookDropTargetListener(getGraphicalViewer());
			
			super.initializeGraphicalViewer();

			/* Add a drop target listener for ME drop events */
			getDiagramGraphicalViewer().addDropTargetListener(
				(TransferDropTargetListener) new ImageFileDropTargetListener(
					getDiagramGraphicalViewer()));
		} else {
			super.initializeGraphicalViewer();
		}
	}

	/**
	 * Creates a PaletteViewerProvider.
	 * @return PaletteViewerProvider that provides for the palette viewer for the diagram
	 */
	protected PaletteViewerProvider createPaletteViewerProvider() {
		assert fHasFlyoutPalette == true;
		getEditDomain().setPaletteRoot(getPaletteRoot());
		return new PaletteViewerProvider(getEditDomain()){

			/**
			 * Override to provide the additional behavior for the tools.
			 * Will intialize with a PaletteEditPartFactory that has a TrackDragger that
			 * understand how to handle the mouseDoubleClick event for shape creation tools.
			 * Also will initialize the palette with a defaultTool that is the SelectToolEx that undestands
			 * how to handle the enter key which will result in the creation of the shape also.
			 */
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);

				viewer.getKeyHandler().setParent(getPaletteKeyHandler());
				viewer.getControl().addMouseListener(getPaletteMouseListener());

			}


			/**
			 * @return Palette Key Handler for the palette
			 */
			private KeyHandler getPaletteKeyHandler() {

				if (paletteKeyHandler == null) {

					paletteKeyHandler = new KeyHandler() {

						/**
						 * Processes a <i>key released </i> event. This method
						 * is called by the Tool whenever a key is released, and
						 * the Tool is in the proper state. Override to support
						 * pressing the enter key to create a shape or connector
						 * (between two selected shapes)
						 *
						 * @param event
						 *            the KeyEvent
						 * @return <code>true</code> if KeyEvent was handled
						 *         in some way
						 */
						public boolean keyReleased(KeyEvent event) {

							if (event.keyCode == SWT.Selection) {

								Tool tool =
									getPaletteViewer()
										.getActiveTool()
										.createTool();

								if (tool instanceof CreationTool
									|| tool instanceof ConnectorCreationTool) {

									tool.keyUp(event, getDiagramGraphicalViewer());

									// 	deactivate current selection
									getPaletteViewer().setActiveTool(null);

									return true;
								}

							}
							return super.keyReleased(event);
						}

					};

				}
				return paletteKeyHandler;
			}

			/**
			 * @return Palette Mouse listener for the palette
			 */
			private MouseListener getPaletteMouseListener() {

				if (paletteMouseListener == null) {

					paletteMouseListener = new MouseListener() {

						/**
						 * Flag to indicate that the current active tool should
						 * be cleared after a mouse double-click event.
						 */
						private boolean clearActiveTool = false;

						/**
						 * Override to support double-clicking a palette tool
						 * entry to create a shape or connector (between two
						 * selected shapes).
						 *
						 * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
						 */
						public void mouseDoubleClick(MouseEvent e) {
							Tool tool = getPaletteViewer().getActiveTool()
								.createTool();

							if (tool instanceof CreationTool
								|| tool instanceof ConnectorCreationTool) {

								tool.setViewer(getDiagramGraphicalViewer());
								tool.setEditDomain(getDiagramGraphicalViewer()
									.getEditDomain());
								tool.mouseDoubleClick(e,
									getDiagramGraphicalViewer());

								// Current active tool should be deactivated,
								// but if it is down here it will get
								// reactivated deep in GEF palette code after
								// receiving mouse up events.
								clearActiveTool = true;
							}
						}

						public void mouseDown(MouseEvent e) {
							// do nothing
						}

						public void mouseUp(MouseEvent e) {
							// 	Deactivate current active tool here if a
							// double-click was handled.
							if (clearActiveTool) {
								getPaletteViewer().setActiveTool(null);
								clearActiveTool = false;
							}

						}
					};

				}
				return paletteMouseListener;
			}

		};
	}

	/** Key Handler for the palette */
	KeyHandler paletteKeyHandler = null;

	/** Mouse listener for the palette */
	MouseListener paletteMouseListener = null;

	/**
	 * @see org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	public void setFocus() {
		if (getGraphicalControl()!=null)
			getGraphicalControl().setFocus();
	}


	/**
	 * Creates a splitter composite that will contain
	 * 2 parts one is for the diagram and the other is for the palette.
	 * @see org.eclipse.ui.IWorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {

		if(fHasFlyoutPalette) {
			FlyoutPaletteComposite.FlyoutPreferences flyoutPrefs = new FlyoutPreferencesImpl();
			flyoutPrefs.setPaletteState(getInitialPaletteState());
			flyoutPrefs.setPaletteWidth(getInitialPaletteSize());

			splitter = new FlyoutPaletteComposite(parent, SWT.NONE, getSite().getPage(), getPaletteViewerProvider(), flyoutPrefs);
			super.createPartControl(splitter);
			splitter.setGraphicalControl(getGraphicalControl());
			if (page != null) {
				splitter.setExternalViewer(getPaletteViewer());
				page = null;
			}

			// CWD: FlyoutPaletteComposite no longer implements the Java Beans
			//      property change API, nor does it even have a 'default state'
			//      property in the IES M9 drop
			//TODO(M9):  Review whether this listener is needed
//			splitter.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				if (evt.getPropertyName().equals(FlyoutPaletteCompositeEx.PROPERTY_DEFAULT_STATE))
//					handlePaletteDefaultStateChanged(((Integer)evt.getNewValue()).intValue());
//				else
//					handlePaletteResized(((Integer)evt.getNewValue()).intValue());
//				}
//			});
		} else {
			super.createPartControl(parent);
		}

	}

	/**
	 * Adapts to
	 * <LI> PalettePage.class
	 * <LI> PaletteViewer.class
	 * other wise delegates to super.getAdapter()
	 */
	public Object getAdapter(Class type) {

		if(fHasFlyoutPalette) {
			if (type == PalettePage.class) {
				if (splitter == null) {
					page = new CustomPalettePage(getPaletteViewerProvider());
					return page;
				}
				return new CustomPalettePage(getPaletteViewerProvider());
			}
			// Please do not remove PaletteViewer adapter
			// QE automation depends on it
			if (type == PaletteViewer.class){
	
				return getPaletteViewer();
			}
		}
		return super.getAdapter(type);
	}

	/**
	 * Returns the PaletteRoot for the palette viewer.
	 * @return the palette root
	 */
	protected abstract PaletteRoot getPaletteRoot();

	/**
	 * Returns the palette view provider that is reponsible for
	 * creating and palette view.
	 * @return PaletteViewerProvider
	 */
	protected final PaletteViewerProvider getPaletteViewerProvider() {
		if (provider == null)
			provider = createPaletteViewerProvider();
		return provider;
	}

	/**
	 * Returns the initial palette size in pixels. Subclasses may override this method to
	 * return a persisted value.
	 * @see #handlePaletteResized(int)
	 * @return the initial size of the palette in pixels.
	 */
	protected int getInitialPaletteSize() {
		// '125' is the value of the FlyoutPaletteComposite.DEFAULT_PALETTE_SIZE constant
		//   which was formerly public but is now private
		return 125;
	}

	/**
	 * gets the initial palette state, it could be 
	 * UNCOLLAPSED_PINNED or COLLAPSED
	 * @return int
	 */
	protected int getInitialPaletteState() {
		// '4' is the value of the FlyoutPaletteComposite.STATE_UNCOLLAPSED constant
		//   which is private.
		return UNCOLLAPSED_PINNED;
	}

	protected void setEditDomain(DefaultEditDomain ed) {
		super.setEditDomain(ed);

	}

	/**
	 * Called to configure the viewer before it receives its contents.
	 */
	protected void configurePaletteViewer() {
		assert fHasFlyoutPalette == true;
		PaletteViewer viewer = getPaletteViewer();

		if (viewer == null) return;

		ContextMenuProvider paletteContextProvider = new PaletteContextMenuProvider(viewer);
		getPaletteViewer().setContextMenu(paletteContextProvider);
		viewer.setCustomizer(new PaletteCustomizer() {
			public void revertToSaved() {
			    //
			}

			public void save() {
			    //
			}
		});
	}

	/**
	 * Helper method to returns the PaletteViewer from the
	 * page.
	 * @return the palette viewer
	 */
	private PaletteViewer getPaletteViewer() {

		return getEditDomain().getPaletteViewer();

	}

	/**
	 * CustomPalettePage that helps with the switching of the palette
	 * from the diagram to the palette view.
	 * @author choang
	 */
	protected class CustomPalettePage extends PaletteViewerPage {
		/**
		 * constructor
		 * @param provider
		 */
		public CustomPalettePage(PaletteViewerProvider provider) {
			super(provider);
		}
		public void createControl(Composite parent) {
			super.createControl(parent);
			if (splitter != null)
				splitter.setExternalViewer(viewer);
		}
		public void dispose() {
			if (splitter != null)
				splitter.setExternalViewer(null);
			super.dispose();
		}
		/**
		 * gets the palette viewer
		 * @return <code>PaletteViewer</code>
		 */
		public PaletteViewer getPaletteViewer() {
			return viewer;
		}
	}

	/**
	 * Called whenever the user resizes the palette.  Sub-classes can store the new palette
	 * size.  May want to use this to store preferences as a perferences.
	 * @param newSize the new size in pixels
	 */
	protected void handlePaletteResized(int newSize){
		//
	}
	/**
	 * Called whenever the user updates the default palette state.  Sub-classes can store
	 * the new palette state.  May want to use this to store preferences as a perferences.
	 * @param newState the new state
	 */
	protected void handlePaletteDefaultStateChanged(int newState){
		//
	}

	/**
	 * An implementation of the fly-out palette preferences.
	 */
	private static final class FlyoutPreferencesImpl
			implements FlyoutPaletteComposite.FlyoutPreferences {

		// 'EAST' is the default dock location
		private int dockLocation = PositionConstants.EAST;

		// '4' is the value of the FlyoutPaletteComposite.STATE_UNCOLLAPSED constant
		//   which is private.
		private int paletteState = UNCOLLAPSED_PINNED;

		// '125' is the value of the FlyoutPaletteComposite.DEFAULT_PALETTE_SIZE constant
		//   which was formerly public but is now private
		private int paletteWidth = 125;

		/* (non-Javadoc)
		 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences#getDockLocation()
		 */
		public int getDockLocation() {
			return dockLocation;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences#getPaletteState()
		 */
		public int getPaletteState() {
			return paletteState;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences#getPaletteWidth()
		 */
		public int getPaletteWidth() {
			return paletteWidth;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences#setDockLocation(int)
		 */
		public void setDockLocation(int location) {
			dockLocation = location;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences#setPaletteState(int)
		 */
		public void setPaletteState(int state) {
			paletteState = state;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences#setPaletteWidth(int)
		 */
		public void setPaletteWidth(int width) {
			paletteWidth = width;
		}
	}
}