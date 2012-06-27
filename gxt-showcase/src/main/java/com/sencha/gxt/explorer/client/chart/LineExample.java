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
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
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

@Detail(name = "Line Chart", icon = "linechart", category = "Charts", classes = Data.class)
public class LineExample implements IsWidget, EntryPoint {

  public interface DataPropertyAccess extends PropertyAccess<Data> {
    ValueProvider<Data, Double> data1();

    ValueProvider<Data, Double> data2();

    ValueProvider<Data, Double> data3();

    ValueProvider<Data, String> name();

    @Path("name")
    ModelKeyProvider<Data> nameKey();
  }

  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

  public Widget asWidget() {
    final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
    store.addAll(TestData.getData(8, 20, 100));

    final Chart<Data> chart = new Chart<Data>();
    chart.setStore(store);
    chart.setShadowChart(true);

    NumericAxis<Data> axis = new NumericAxis<Data>();
    axis.setPosition(Position.LEFT);
    axis.addField(dataAccess.data1());
    axis.addField(dataAccess.data2());
    axis.addField(dataAccess.data3());
    TextSprite title = new TextSprite("Number of Hits");
    title.setFontSize(18);
    axis.setTitleConfig(title);
    axis.setMinorTickSteps(1);
    axis.setDisplayGrid(true);
    PathSprite odd = new PathSprite();
    odd.setOpacity(1);
    odd.setFill(new Color("#ddd"));
    odd.setStroke(new Color("#bbb"));
    odd.setStrokeWidth(0.5);
    axis.setGridOddConfig(odd);
    axis.setMinimum(0);
    axis.setMaximum(100);
    chart.addAxis(axis);

    CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
    catAxis.setPosition(Position.BOTTOM);
    catAxis.setField(dataAccess.name());
    title = new TextSprite("Month of the Year");
    title.setFontSize(18);
    catAxis.setTitleConfig(title);
    catAxis.setLabelProvider(new LabelProvider<String>() {
      @Override
      public String getLabel(String item) {
        return item.substring(0, 3);
      }
    });
    chart.addAxis(catAxis);

    final LineSeries<Data> series = new LineSeries<Data>();
    series.setYAxisPosition(Position.LEFT);
    series.setYField(dataAccess.data1());
    series.setStroke(new RGB(194,0,36));
    series.setShowMarkers(true);
    Sprite marker = Primitives.square(0, 0, 6);
    marker.setFill(new RGB(194,0,36));
    series.setMarkerConfig(marker);
    series.setHighlighting(true);
    chart.addSeries(series);

    final LineSeries<Data> series2 = new LineSeries<Data>();
    series2.setYAxisPosition(Position.LEFT);
    series2.setYField(dataAccess.data2());
    series2.setStroke(new RGB(240,165,10));
    series2.setShowMarkers(true);
    series2.setSmooth(true);
    marker = Primitives.circle(0, 0, 6);
    marker.setFill(new RGB(240,165,10));
    series2.setMarkerConfig(marker);
    series2.setHighlighting(true);
    chart.addSeries(series2);

    final LineSeries<Data> series3 = new LineSeries<Data>();
    series3.setYAxisPosition(Position.LEFT);
    series3.setYField(dataAccess.data3());
    series3.setStroke(new RGB(32, 68, 186));
    series3.setShowMarkers(true);
    series3.setSmooth(true);
    series3.setFill(new RGB(32, 68, 186));
    marker = Primitives.diamond(0, 0, 6);
    marker.setFill(new RGB(32, 68, 186));
    series3.setMarkerConfig(marker);
    series3.setHighlighting(true);
    chart.addSeries(series3);

    final Legend<Data> legend = new Legend<Data>();
    legend.setPosition(Position.RIGHT);
    legend.setItemHighlighting(true);
    legend.setItemHiding(true);
    chart.setLegend(legend);

    TextButton regenerate = new TextButton("Reload Data");
    regenerate.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        store.clear();
        store.addAll(TestData.getData(8, 20, 100));
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
    ToggleButton shadow = new ToggleButton("Shadow");
    shadow.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        chart.setShadowChart(event.getValue());
        chart.redrawChart();
      }
    });
    shadow.setValue(true);

    ToolBar toolBar = new ToolBar();
    toolBar.add(regenerate);
    toolBar.add(animation);
    toolBar.add(shadow);

    ContentPanel panel = new FramedPanel();
    panel.getElement().getStyle().setMargin(10, Unit.PX);
    panel.setCollapsible(true);
    panel.setHeadingText("Line Chart");
    panel.setPixelSize(620, 500);
    panel.setBodyBorder(true);
    
    VerticalLayoutContainer layout = new VerticalLayoutContainer();
    panel.add(layout);
    
    Resizable resize = new Resizable(panel);
    resize.setMinHeight(400);
    resize.setMinWidth(400);
    new Draggable(panel, panel.getHeader()).setUseProxy(false);

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
