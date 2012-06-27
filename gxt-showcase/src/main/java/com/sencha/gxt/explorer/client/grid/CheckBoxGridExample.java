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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.examples.resources.client.model.Stock;
import com.sencha.gxt.examples.resources.client.model.StockProperties;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

@Detail(name = "CheckBox Grid", icon = "checkboxgrid", category = "Grid", classes = {Stock.class, StockProperties.class})
public class CheckBoxGridExample implements IsWidget, EntryPoint {

  private static final StockProperties props = GWT.create(StockProperties.class);

  @Override
  public Widget asWidget() {
    final NumberFormat number = NumberFormat.getFormat("0.00");

    IdentityValueProvider<Stock> identity = new IdentityValueProvider<Stock>();


    final CheckBoxSelectionModel<Stock> sm = new CheckBoxSelectionModel<Stock>(identity);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
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
    l.add(sm.getColumn());
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);
    l.add(changeCol);
    l.add(lastTransCol);
    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getStocks());

    ContentPanel cp = new ContentPanel();
    cp.setHeadingText("CheckBox Grid");
    cp.getHeader().setIcon(ExampleImages.INSTANCE.table());
    cp.setPixelSize(600, 320);
    cp.addStyleName("margin-10");

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setSelectionModel(sm);
    grid.getView().setAutoExpandColumn(nameCol);
    grid.setBorders(false);
    grid.getView().setStripeRows(true);
    grid.getView().setColumnLines(true);

    ToolBar toolBar = new ToolBar();
    toolBar.add(new LabelToolItem("Selection Mode: "));
    
    SimpleComboBox<String> type = new SimpleComboBox<String>(new StringLabelProvider<String>());
    type.setTriggerAction(TriggerAction.ALL);
    type.setEditable(false);
    type.setWidth(100);
    type.add("Multi");
    type.add("Simple");
    type.setValue("Multi");
    type.addValueChangeHandler(new ValueChangeHandler<String>() {

      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        boolean simple = event.getValue().equals("Simple");
        sm.deselectAll();
        sm.setSelectionMode(simple ? SelectionMode.SIMPLE : SelectionMode.MULTI);
      }
    });
    toolBar.add(type);
    
    VerticalLayoutContainer con = new VerticalLayoutContainer();
    cp.setWidget(con);
    
    con.add(toolBar, new VerticalLayoutData(1, -1));
    con.add(grid, new VerticalLayoutData(1, 1));

    return cp;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
