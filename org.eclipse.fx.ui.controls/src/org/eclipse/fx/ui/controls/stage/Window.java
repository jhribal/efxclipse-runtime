/*******************************************************************************
 * Copyright (c) 2015 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.fx.ui.controls.stage;

import javafx.scene.Node;

/**
 * A pane representing a window
 * 
 * @since 2.0
 */
public interface Window extends Frame {

	/**
	 * Set menu bar
	 * 
	 * @param n
	 *            the menu bar node
	 */
	public abstract void setMenuBar(Node n);

}