/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.grid;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.examples.resources.client.model.Plant;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

@Detail(name = "Inline Editable Grid", icon = "editablegrid", category = "Grid", classes = {AbstractGridEditingExample.class, Plant.class})
public class InlineEditingGridExample extends AbstractGridEditingExample {

  @Override
  protected GridEditing<Plant> createGridEditing(Grid<Plant> editableGrid) {
    return new GridInlineEditing<Plant>(editableGrid);
  }
  
  @Override
  public Widget asWidget() {
    Widget w = super.asWidget();
    grid.setSelectionModel(new CellSelectionModel<Plant>());
    
    grid.getColumnModel().getColumn(0).setHideable(false);
    
    return w;
  }

}
