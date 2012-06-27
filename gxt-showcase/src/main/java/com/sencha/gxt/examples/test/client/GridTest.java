/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.cell.core.client.form.DateCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Plant;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GridTest implements EntryPoint {

  interface PlaceProperties extends PropertyAccess<Plant> {
    ValueProvider<Plant, Date> available();

    @Path("name")
    ModelKeyProvider<Plant> key();

    ValueProvider<Plant, String> name();
  }

  private static final PlaceProperties properties = GWT.create(PlaceProperties.class);
  
  @Override
  public void onModuleLoad() {
    ColumnConfig<Plant, String> cc1 = new ColumnConfig<Plant, String>(properties.name(), 100, "Name");
    cc1.setCell(new TextButtonCell());

    DateCell dateCell = new DateCell();
    dateCell.setPropertyEditor(new DateTimePropertyEditor(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));

    ColumnConfig<Plant, Date> cc2 = new ColumnConfig<Plant, Date>(properties.available(), 100, "Date");

    cc2.setCell(dateCell);

    List<ColumnConfig<Plant, ?>> l = new ArrayList<ColumnConfig<Plant, ?>>();
    l.add(cc1);
    l.add(cc2);
    ColumnModel<Plant> cm = new ColumnModel<Plant>(l);

    ListStore<Plant> store = new ListStore<Plant>(properties.key());
    store.addAll(TestData.getPlants());

    Grid<Plant> view = new Grid<Plant>(store, cm);
    view.getView().setForceFit(true);

    ContentPanel cp = new ContentPanel();
    cp.setWidget(view);
    cp.setPixelSize(500, 400);
    cp.addStyleName("margin-10");
    
    RootPanel.get().add(cp);
  }

}
