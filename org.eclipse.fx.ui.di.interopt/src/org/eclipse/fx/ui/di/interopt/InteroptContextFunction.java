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
package org.eclipse.fx.ui.di.interopt;

import javax.swing.JPanel;

import javafx.embed.swing.JFXPanel;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.ui.services.theme.ThemeManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.osgi.service.component.annotations.Component;

/**
 * Context function who can adapt an {@link Composite} and a {@link JPanel} to a
 * {@link BorderPane}
 */
@Component(service=IContextFunction.class,property="service.context.key:String=javafx.scene.layout.BorderPane")
public class InteroptContextFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		Object comp = context.get("org.eclipse.swt.widgets.Composite"); //$NON-NLS-1$

		if (comp != null) {
			BorderPane pane = new BorderPane();
			FXCanvas canvas = new FXCanvas((Composite) comp, SWT.NONE);
			Scene scene = new Scene(pane);
			integrateInToTheme(context, scene);
			canvas.setScene(scene);

			return pane;
		} else {
			JPanel jpanel = (JPanel) context.get("javax.swing.JPanel"); //$NON-NLS-1$

			if (jpanel != null) {
				BorderPane pane = new BorderPane();
				JFXPanel fxPanel = new JFXPanel();
				Scene scene = new Scene(pane);
				integrateInToTheme(context, scene);
				fxPanel.setScene(scene);
				jpanel.add(fxPanel);

				return pane;
			}
		}

		return null;
	}

	private static void integrateInToTheme(IEclipseContext context, Scene scene) {
		Object object = context.get("org.eclipse.fx.ui.services.theme.ThemeManager"); //$NON-NLS-1$
		if( object != null ) {
			ThemeManager mgr = (ThemeManager) object;
			mgr.registerScene(scene);
			if( mgr.getCurrentTheme() == null ) {
				if( System.getProperty("javafx.theme") != null ) {
					mgr.setCurrentThemeId(mgr.getAvailableThemes().get(System.getProperty("javafx.theme")).getId());
				}
			}
		}
	}
}
