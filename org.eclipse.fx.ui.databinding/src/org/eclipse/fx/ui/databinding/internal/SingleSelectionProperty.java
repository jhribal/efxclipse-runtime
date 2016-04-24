/*******************************************************************************
 * Copyright (c) 2013 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.fx.ui.databinding.internal;

import javafx.beans.property.ReadOnlyProperty;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;

/**
 * Property for single selection
 */
public class SingleSelectionProperty extends ControlReadOnlyPropertyValueProperty<Object> {

	@Override
	public Object getValueType() {
		return null;
	} 

	@SuppressWarnings("null")
	@Override
	protected ReadOnlyProperty<Object> getProperty(Object source) {
		SelectionModel<Object> model = getSelectionModel(source);
		if( model == null ) {
			throw new IllegalStateException("Unable to find selection model for '"+source+"'");  //$NON-NLS-1$//$NON-NLS-2$
		}
		return model.selectedItemProperty();
	}
	
	@SuppressWarnings("unchecked")
	static SelectionModel<Object> getSelectionModel(Object source) {
		SelectionModel<Object> m = null;
		if( source instanceof ListView<?> ) {
			m = ((ListView<Object>) source).getSelectionModel();
		} else if( source instanceof ComboBox<?> ) {
			m = ((ComboBox<Object>) source).getSelectionModel();
		} else if( source instanceof ChoiceBox<?> ) {
			m = ((ChoiceBox<Object>) source).getSelectionModel();
		} else if( source instanceof TableView<?> ) {
			m = ((TableView<Object>) source).getSelectionModel();
		}
		return m;
	}
	
	@Override
	protected void doSetValue(Object source, Object value) {
		SelectionModel<Object> o = getSelectionModel(source);
		if( o != null ) {
			if( value == null ) {
				o.clearSelection();
			} else {
				o.select(value);
			}
		}
	}
}
