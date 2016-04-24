/*******************************************************************************
 * Copyright (c) 2012 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.fx.ui.workbench3;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * ViewPart base class to embed JavaFX into an RCP View
 */
public abstract class FXViewPart extends ViewPart {
	private FXCanvas canvas;

	@Override
	public void createPartControl(Composite parent) {
		this.canvas = new FXCanvas(parent, SWT.NONE);
		this.canvas.setScene(createFxScene());
	}

	/**
	 * Create a scene, called as part of {@link #createPartControl(Composite)}
	 * 
	 * @return the scene to show
	 */
	protected abstract Scene createFxScene();

	@Override
	public void setFocus() {
		this.canvas.setFocus();
		setFxFocus();
	}

	/**
	 * Set the focus call when {@link #setFocus()} is called
	 */
	protected abstract void setFxFocus();
}
