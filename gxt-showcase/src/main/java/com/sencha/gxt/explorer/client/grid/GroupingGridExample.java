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

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Stock;
import com.sencha.gxt.examples.resources.client.model.StockProperties;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GroupingView;

@Detail(name = "Grouping Grid", icon = "grouping", category = "Grid", classes = {Stock.class, StockProperties.class})
public class GroupingGridExample implements EntryPoint, IsWidget {

  @Override
  public Widget asWidget() {
    StockProperties properties = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(properties.key());

    store.addAll(TestData.getCompanies());

    List<ColumnConfig<Stock, ?>> cfgs = new ArrayList<ColumnConfig<Stock, ?>>();
    ColumnConfig<Stock, String> name = new ColumnConfig<Stock, String>(properties.name(), 60);
    name.setHeader("Name");
    name.setCell(new TextCell());
    cfgs.add(name);

    ColumnConfig<Stock, Double> price = new ColumnConfig<Stock, Double>(properties.open(), 20);
    price.setHeader("Price");
    cfgs.add(price);
    ColumnConfig<Stock, Double> change = new ColumnConfig<Stock, Double>(properties.change(), 20);
    change.setHeader("Change");
    cfgs.add(change);

    final ColumnConfig<Stock, String> industry = new ColumnConfig<Stock, String>(properties.industry(), 20);
    industry.setHeader("Industry");
    cfgs.add(industry);
    
    final ColumnConfig<Stock, Date> lastUpdated = new ColumnConfig<Stock, Date>(properties.lastTrans(), 20);
    lastUpdated.setHeader("Last Updated");
    lastUpdated.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));
    cfgs.add(lastUpdated);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(cfgs);

    final GroupingView<Stock> view = new GroupingView<Stock>();
    view.setShowGroupedColumn(false);
    view.setForceFit(true);

    Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setView(view);
    view.groupBy(industry);

    ContentPanel panel = new ContentPanel();
    panel.setHeadingHtml("Grouping Example");
    panel.setSize("700", "450");
    panel.add(grid);
    panel.addStyleName("margin-10");
    panel.setCollapsible(true);
    return panel;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

}
