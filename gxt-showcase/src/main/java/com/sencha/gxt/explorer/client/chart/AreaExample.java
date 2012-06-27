/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.chart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.AreaSeries;
import com.sencha.gxt.chart.client.chart.series.SeriesRenderer;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.path.MoveTo;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Data;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

@Detail(name = "Area Chart", icon = "areachart", category = "Charts", classes = Data.class)
public class AreaExample implements IsWidget, EntryPoint {

  public interface DataPropertyAccess extends PropertyAccess<Data> {
    ValueProvider<Data, Double> data1();

    ValueProvider<Data, Double> data2();

    ValueProvider<Data, Double> data3();

    ValueProvider<Data, Double> data4();

    ValueProvider<Data, Double> data5();

    ValueProvider<Data, Double> data6();

    ValueProvider<Data, Double> data7();

    ValueProvider<Data, String> name();

    @Path("name")
    ModelKeyProvider<Data> nameKey();
  }

  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

  @Override
  public Widget asWidget() {

    final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
    store.addAll(TestData.getData(12, 20, 100));

    final Chart<Data> chart = new Chart<Data>();
    chart.setStore(store);
    //Allow room for rotated labels
    chart.setDefaultInsets(20);

    NumericAxis<Data> axis = new NumericAxis<Data>();
    axis.setPosition(Position.LEFT);
    axis.addField(dataAccess.data1());
    axis.addField(dataAccess.data2());
    axis.addField(dataAccess.data3());
    axis.addField(dataAccess.data4());
    axis.addField(dataAccess.data5());
    axis.addField(dataAccess.data6());
    axis.addField(dataAccess.data7());
    PathSprite gridConfig = new PathSprite();
    gridConfig.setStroke(new RGB("#bbb"));
    gridConfig.setFill(new RGB("#ddd"));
    gridConfig.setZIndex(1);
    gridConfig.setStrokeWidth(1);
    axis.setGridOddConfig(gridConfig);
    axis.setDisplayGrid(true);
    TextSprite title = new TextSprite("Number of Hits");
    title.setFontSize(18);
    axis.setTitleConfig(title);
    axis.setMinorTickSteps(2);
    chart.addAxis(axis);

    CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
    catAxis.setPosition(Position.BOTTOM);
    catAxis.setField(dataAccess.name());
    title = new TextSprite("Month of the Year");
    title.setFontSize(18);
    catAxis.setTitleConfig(title);
    catAxis.setDisplayGrid(true);
    TextSprite sprite = new TextSprite();
    sprite.setRotation(315);
    catAxis.setLabelConfig(sprite);
    catAxis.setLabelPadding(-10);
    catAxis.setMinorTickSteps(5);
    catAxis.setLabelTolerance(20);
    chart.addAxis(catAxis);

    AreaSeries<Data> series = new AreaSeries<Data>();
    series.setHighlighting(true);
    series.setYAxisPosition(Position.LEFT);
    series.addYField(dataAccess.data1());
    series.addYField(dataAccess.data2());
    series.addYField(dataAccess.data3());
    series.addYField(dataAccess.data4());
    series.addYField(dataAccess.data5());
    series.addYField(dataAccess.data6());
    series.addYField(dataAccess.data7());
    series.addColor(new RGB(148, 174, 10));
    series.addColor(new RGB(17, 95, 166));
    series.addColor(new RGB(166, 17, 32));
    series.addColor(new RGB(255, 136, 9));
    series.addColor(new RGB(255, 209, 62));
    series.addColor(new RGB(166, 17, 135));
    series.addColor(new RGB(36, 173, 154));
    PathSprite highlightLine = new PathSprite();
    highlightLine.setHidden(true);
    highlightLine.addCommand(new MoveTo(0, 0));
    highlightLine.setZIndex(1000);
    highlightLine.setStrokeWidth(5);
    highlightLine.setStroke(new RGB("#444"));
    highlightLine.setOpacity(0.3);
    series.setHighlightLineConfig(highlightLine);
    series.setRenderer(new SeriesRenderer<Data>() {
      @Override
      public void spriteRenderer(Sprite sprite, int index, ListStore<Data> store) {
        sprite.setOpacity(0.93);
        sprite.redraw();
      }
    });
    chart.addSeries(series);

    Legend<Data> legend = new Legend<Data>();
    legend.setPosition(Position.BOTTOM);
    legend.setItemHighlighting(true);
    legend.setItemHiding(true);
    chart.setLegend(legend);

    TextButton regenerate = new TextButton("Reload Data");
    regenerate.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        store.clear();
        store.addAll(TestData.getData(12, 20, 100));
        chart.redrawChart();
      }
    });
    ToggleButton animation = new ToggleButton("Animate");
    animation.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        chart.setAnimated(event.getValue());
      }
    });
    animation.setValue(true, true);

    ToolBar toolBar = new ToolBar();
    toolBar.add(regenerate);
    toolBar.add(animation);

    ContentPanel panel = new FramedPanel();
    panel.getElement().getStyle().setMargin(10, Unit.PX);
    panel.setCollapsible(true);
    panel.setHeadingText("Area Chart");
    panel.setPixelSize(620, 500);
    
    Resizable resize = new Resizable(panel);
    resize.setMinHeight(400);
    resize.setMinWidth(500);
    new Draggable(panel, panel.getHeader()).setUseProxy(false);
    panel.setBodyBorder(true);

    VerticalLayoutContainer layout = new VerticalLayoutContainer();
    panel.add(layout);

    toolBar.setLayoutData(new VerticalLayoutData(1, -1));
    layout.add(toolBar);

    chart.setLayoutData(new VerticalLayoutData(1, 1));
    layout.add(chart);

    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
