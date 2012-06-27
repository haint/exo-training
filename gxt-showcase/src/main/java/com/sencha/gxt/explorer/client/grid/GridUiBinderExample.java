/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Stock;
import com.sencha.gxt.examples.resources.client.model.StockProperties;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.state.client.CookieProvider;
import com.sencha.gxt.state.client.StateManager;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

@Detail(name = "Basic UiBinder Grid", icon = "basicgrid", category = "Grid", classes = {Stock.class, StockProperties.class})
public class GridUiBinderExample implements IsWidget, EntryPoint {

  private static final StockProperties props = GWT.create(StockProperties.class);

  interface MyUiBinder extends UiBinder<Widget, GridUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  private ColumnModel<Stock> cm;
  private ListStore<Stock> store;
  
  @UiField
  GridView<Stock> view;

  @Override
  public Widget asWidget() {
    final NumberFormat number = NumberFormat.getFormat("0.00");

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 50, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    ColumnConfig<Stock, Double> changeCol = new ColumnConfig<Stock, Double>(props.change(), 100, "Change");
    changeCol.setCell(new AbstractCell<Double>() {

      @Override
      public void render(Context context, Double value, SafeHtmlBuilder sb) {
        String style = "style='color: " + (value < 0 ? "red" : "green") + "'";
        String v = number.format(value);
        sb.appendHtmlConstant("<span " + style + " qtitle='Change' qtip='" + v + "'>" + v + "</span>");
      }
    });

    ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(props.lastTrans(), 100, "Last Updated");
    lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);
    l.add(changeCol);
    l.add(lastTransCol);
    cm = new ColumnModel<Stock>(l);

    store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getStocks());

    Widget component = uiBinder.createAndBindUi(this);
    
    view.setAutoExpandColumn(nameCol);

    return component;
  }

  @UiFactory
  Grid<Stock> createGrid() {
    return new Grid<Stock>(store, cm, view);
  }

  @Override
  public void onModuleLoad() {
    StateManager.get().setProvider(new CookieProvider("/", null, null, GXT.isSecure()));
    RootPanel.get().add(asWidget());
  }
}
