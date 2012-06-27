/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.chart;

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
import com.sencha.gxt.chart.client.chart.axis.RadialAxis;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.chart.series.RadarSeries;
import com.sencha.gxt.chart.client.chart.series.Series;
import com.sencha.gxt.chart.client.chart.series.SeriesRenderer;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
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

@Detail(name = "Radar Chart", icon = "radarchart", category = "Charts")
public class RadarExample implements IsWidget, EntryPoint {

  public interface DataPropertyAccess extends PropertyAccess<Data> {
    ValueProvider<Data, Double> data1();

    ValueProvider<Data, Double> data2();

    ValueProvider<Data, Double> data3();

    ValueProvider<Data, String> name();

    @Path("name")
    ModelKeyProvider<Data> nameKey();
  }

  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);
  private Color[] colors = {new RGB("#6d9824"), new RGB("#87146e"), new RGB("#2a9196")};
  private Color[] currentColor = {Color.NONE, Color.NONE, Color.NONE};
  private double strokeWidth = 2;
  private double opacity = 1;

  public Widget asWidget() {
    final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
    store.addAll(TestData.getData(12, 20, 100));

    final Chart<Data> chart = new Chart<Data>(600, 400);
    chart.setStore(store);
    chart.setDefaultInsets(20);

    RadialAxis<Data, String> axis = new RadialAxis<Data, String>();
    axis.setCategoryField(dataAccess.name());
    axis.setDisplayGrid(true);
    chart.addAxis(axis);

    final RadarSeries<Data> radar = new RadarSeries<Data>();
    radar.setYField(dataAccess.data1());
    radar.setStroke(colors[0]);
    radar.setShowMarkers(true);
    Sprite marker = Primitives.circle(0, 0, 4);
    marker.setFill(colors[0]);
    radar.setMarkerConfig(marker);
    radar.setLineRenderer(createRenderer(0));
    chart.addSeries(radar);

    final RadarSeries<Data> radar2 = new RadarSeries<Data>();
    radar2.setYField(dataAccess.data2());
    radar2.setStroke(colors[1]);
    radar2.setShowMarkers(true);
    marker = Primitives.diamond(0, 0, 4);
    marker.setFill(colors[1]);
    radar2.setMarkerConfig(marker);
    radar2.setLineRenderer(createRenderer(1));
    chart.addSeries(radar2);

    final RadarSeries<Data> radar3 = new RadarSeries<Data>();
    radar3.setYField(dataAccess.data3());
    radar3.setStroke(colors[2]);
    radar3.setShowMarkers(true);
    marker = Primitives.square(0, 0, 4);
    marker.setFill(colors[2]);
    radar3.setMarkerConfig(marker);
    radar3.setLineRenderer(createRenderer(2));
    chart.addSeries(radar3);

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
    ToggleButton shadow = new ToggleButton("Shadow");
    shadow.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        chart.setShadowChart(event.getValue());
        chart.redrawChart();
      }
    });
    shadow.setValue(false);

    ToggleButton fill = new ToggleButton("Fill");
    fill.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          opacity = 0.4;
          currentColor[0] = colors[0];
          currentColor[1] = colors[1];
          currentColor[2] = colors[2];
          strokeWidth = Double.NaN;
        } else {
          opacity = 1;
          currentColor[0] = Color.NONE;
          currentColor[1] = Color.NONE;
          currentColor[2] = Color.NONE;
          strokeWidth = 2;
        }
        List<Series<Data>> series = chart.getSeries();
        for (int i = 0; i < series.size(); i++) {
          RadarSeries<Data> radar = (RadarSeries<Data>) series.get(i);
          radar.setShowMarkers(!event.getValue());

        }
        chart.redrawChart();
      }
    });
    fill.setValue(true, true);

    ToolBar toolBar = new ToolBar();
    toolBar.add(regenerate);
    toolBar.add(animation);
    toolBar.add(shadow);
    toolBar.add(fill);

    ContentPanel panel = new FramedPanel();
    panel.getElement().getStyle().setMargin(10, Unit.PX);
    panel.setCollapsible(true);
    panel.setHeadingText("Radar Chart");
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

  public SeriesRenderer<Data> createRenderer(final int seriesIndex) {
    return new SeriesRenderer<Data>() {
      @Override
      public void spriteRenderer(Sprite sprite, int index, ListStore<Data> store) {
        sprite.setStrokeWidth(strokeWidth);
        sprite.setOpacity(opacity);
        sprite.setFill(currentColor[seriesIndex]);
        sprite.redraw();
      }
    };
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
