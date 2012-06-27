/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.chart;

import java.util.ArrayList;
import java.util.List;

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
import com.sencha.gxt.chart.client.chart.event.LegendSelectionEvent;
import com.sencha.gxt.chart.client.chart.event.LegendSelectionEvent.LegendSelectionHandler;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.chart.series.ScatterSeries;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
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

@Detail(name = "Mixed Chart", icon = "mixedchart", category = "Charts", classes = Data.class)
public class MixedExample implements IsWidget, EntryPoint {

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
    store.addAll(getData(8, 20, 100));

    final Chart<Data> chart = new Chart<Data>();
    chart.setStore(store);
    chart.setShadowChart(true);

    final NumericAxis<Data> axisLeft = new NumericAxis<Data>();
    axisLeft.setPosition(Position.LEFT);
    axisLeft.addField(dataAccess.data1());
    axisLeft.addField(dataAccess.data3());
    PathSprite pathLeft = new PathSprite();
    pathLeft.setStroke(RGB.BLUE);
    pathLeft.setZIndex(1);
    axisLeft.setAxisConfig(pathLeft);
    axisLeft.setGridDefaultConfig(pathLeft);
    axisLeft.setDisplayGrid(true);
    TextSprite title = new TextSprite("Number of Hits");
    title.setFontSize(18);
    title.setFill(RGB.BLUE);
    axisLeft.setTitleConfig(title);
    title = title.copy();
    title.setFontSize(12);
    axisLeft.setLabelConfig(title);
    axisLeft.setMinimum(0);
    axisLeft.setMaximum(100);
    chart.addAxis(axisLeft);
    
    final NumericAxis<Data> axisRight = new NumericAxis<Data>();
    axisRight.setPosition(Position.RIGHT);
    axisRight.addField(dataAccess.data2());
    PathSprite pathRight = new PathSprite();
    pathRight.setStroke(RGB.RED);
    axisRight.setAxisConfig(pathRight);
    TextSprite sprite = new TextSprite("Number of Views");
    sprite.setFontSize(18);
    sprite.setFill(RGB.RED);
    axisRight.setTitleConfig(sprite);
    sprite = sprite.copy();
    sprite.setFontSize(12);
    axisRight.setLabelConfig(sprite);
    axisRight.setMinimum(0);
    axisRight.setMaximum(50);
    chart.addAxis(axisRight);

    CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
    catAxis.setPosition(Position.BOTTOM);
    catAxis.setField(dataAccess.name());
    title = new TextSprite("Month of the Year");
    title.setFontSize(18);
    catAxis.setTitleConfig(title);
    chart.addAxis(catAxis);

    final BarSeries<Data> bar = new BarSeries<Data>();
    bar.addYField(dataAccess.data1());
    bar.setColumn(true);
    bar.addColor(new RGB(240, 165, 10));
    bar.setLegendTitle("Bar");
    bar.setShownInLegend(false);
    chart.addSeries(bar);
    
    final LineSeries<Data> line = new LineSeries<Data>();
    line.setYField(dataAccess.data3());
    line.setStroke(RGB.BLUE);
    line.setSmooth(true);
    line.setFill(new RGB(32, 68, 186));
    line.setLegendTitle("Line");
    chart.addSeries(line);

    final ScatterSeries<Data> scatter = new ScatterSeries<Data>();
    scatter.setYAxisPosition(Position.RIGHT);
    scatter.setYField(dataAccess.data2());
    Sprite marker = Primitives.circle(0, 0, 6);
    marker.setFill(RGB.RED);
    scatter.setMarkerConfig(marker);
    scatter.setLegendTitle("Scatter");
    chart.addSeries(scatter);

    final Legend<Data> legend = new Legend<Data>();
    legend.setPosition(Position.BOTTOM);
    legend.setItemHiding(true);
    chart.setLegend(legend);
    legend.addLegendSelectionHandler(new LegendSelectionHandler() {
      @Override
      public void onLegendSelection(LegendSelectionEvent event) {
        if(event.getIndex() == 0) {
          if(!event.getItem().isOn()) {
            chart.removeAxis(Position.LEFT);
          } else {
            chart.addAxis(axisLeft);
          }
        } else if(event.getIndex() == 1){
          if(!event.getItem().isOn()) {
            chart.removeAxis(Position.RIGHT);
          } else {
            chart.addAxis(axisRight);
          }
        }
        chart.redrawChart();
      }
    });

    TextButton regenerate = new TextButton("Reload Data");
    regenerate.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        store.clear();
        store.addAll(getData(8, 20, 100));
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
    panel.setHeadingText("Mixed Chart");
    panel.setPixelSize(620, 500);
    panel.setBodyBorder(true);
    
    Resizable resize = new Resizable(panel);
    resize.setMinHeight(400);
    resize.setMinWidth(400);
    new Draggable(panel, panel.getHeader()).setUseProxy(false);

    VerticalLayoutContainer layout = new VerticalLayoutContainer();
    panel.add(layout);

    toolBar.setLayoutData(new VerticalLayoutData(1, -1));
    layout.add(toolBar);

    chart.setLayoutData(new VerticalLayoutData(1, 1));
    layout.add(chart);

    return panel;
  }
  
  private static final String[] monthsFull = new String[] {
    "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
    "December"};
  
  private static List<Data> getData(int size, double min, double scale) {
    List<Data> data = new ArrayList<Data>();
    for (int i = 0; i < size; i++) {
      data.add(new Data(monthsFull[i % monthsFull.length], Math.floor(Math.max(Math.random() * scale, min)), Math.floor(Math.max(Math.random() * scale / 2, min / 2)),
          Math.floor(Math.max(Math.random() * scale, min)), Math.floor(Math.max(Math.random() * scale, min)), Math.floor(Math.max(Math.random() * scale, min)),
          Math.floor(Math.max(Math.random() * scale, min)), Math.floor(Math.max(Math.random() * scale, min)), Math.floor(Math.max(Math.random() * scale, min)),
          Math.floor(Math.max(Math.random() * scale, min))));
    }
    return data;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
