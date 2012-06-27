/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.view;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.DateCell;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Stock;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;

@Detail(name = "DateCell ListView", icon = "datecelllistview", category = "Templates & Lists")
public class DateCellListViewExample implements EntryPoint, IsWidget {
  interface StockProperties extends PropertyAccess<Stock> {
    @Path("id")
    ModelKeyProvider<Stock> key();

    @Path("lastTrans")
    ValueProvider<Stock, Date> date();
  }

  @Override
  public Widget asWidget() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("DateCell ListView");
    panel.setPixelSize(300, 300);
    panel.setBodyBorder(false);

    panel.addStyleName("margin-10");

    final StockProperties props = GWT.create(StockProperties.class);

    ListView<Stock, Date> stockList = new ListView<Stock, Date>(new ListStore<Stock>(props.key()), props.date());
    
    DateCell cell = new DateCell();
    cell.setPropertyEditor(new DateTimePropertyEditor(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
    
    stockList.setCell(cell);
    stockList.getSelectionModel().setSelectionMode(SelectionMode.SIMPLE);
    stockList.getSelectionModel().setLocked(true);

    stockList.getStore().addAll(TestData.getStocks());

    panel.add(stockList);
    return panel;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(asWidget());

  }
}
