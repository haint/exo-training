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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelProvider;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
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

@Detail(name = "Blue Chart", icon = "bluechart", category = "Charts", classes = Data.class)
public class BlueChartExample implements IsWidget, EntryPoint {

  public interface DataPropertyAccess extends PropertyAccess<Data> {
    ValueProvider<Data, Double> data1();

    ValueProvider<Data, Double> data2();

    ValueProvider<Data, String> name();

    @Path("name")
    ModelKeyProvider<Data> nameKey();
  }

  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

  public Widget asWidget() {
    final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
    store.addAll(TestData.getData(12, 20, 100));

    final Chart<Data> chart = new Chart<Data>();
    chart.setStore(store);
    chart.setDefaultInsets(30);
    chart.setShadowChart(true);

    NumericAxis<Data> axis = new NumericAxis<Data>();
    axis.setPosition(Position.LEFT);
    axis.addField(dataAccess.data1());
    axis.addField(dataAccess.data2());
    axis.setDisplayGrid(true);
    axis.setMinimum(0);
    axis.setMaximum(100);
    TextSprite text = new TextSprite();
    text.setFont("Arial");
    text.setFontSize(10);
    axis.setLabelConfig(text);
    chart.addAxis(axis);

    CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
    catAxis.setPosition(Position.BOTTOM);
    catAxis.setField(dataAccess.name());
    text = text.copy();
    text.setFontSize(11);
    catAxis.setDisplayGrid(true);
    catAxis.setLabelConfig(text);
    catAxis.setLabelProvider(new LabelProvider<String>() {
      @Override
      public String getLabel(String item) {
        return item.substring(0, 3);
      }
    });
    chart.addAxis(catAxis);

    Gradient gradient = new Gradient("bar-gradient", 90);
    gradient.addStop(new Stop(0, new RGB("#99BBE8")));
    gradient.addStop(new Stop(70, new RGB("#77AECE")));
    gradient.addStop(new Stop(100, new RGB("#77AECE")));
    chart.addGradient(gradient);

    final BarSeries<Data> bar = new BarSeries<Data>();
    bar.setYAxisPosition(Position.LEFT);
    bar.addYField(dataAccess.data1());
    bar.setColumn(true);
    bar.addColor(gradient);
    chart.addSeries(bar);
    
    final LineSeries<Data> line = new LineSeries<Data>();
    line.setYAxisPosition(Position.LEFT);
    line.setYField(dataAccess.data2());
    line.setStroke(new RGB("#18428E"));
    line.setStrokeWidth(3);
    line.setShowMarkers(true);
    Sprite marker = Primitives.circle(0, 0, 4);
    marker.setFill(new RGB("#18428E"));
    line.setMarkerConfig(marker);
    SeriesToolTipConfig<Data> tooltip = new SeriesToolTipConfig<Data>();
    tooltip.setLabelProvider(new SeriesLabelProvider<Data>() {
      @Override
      public String getLabel(Data item, ValueProvider<? super Data, ? extends Number> valueProvider) {
        return NumberFormat.getFormat("0").format(dataAccess.data2().getValue(item)) + " visits in "
            + dataAccess.name().getValue(item);
      }
    });
    tooltip.setDismissDelay(2000);
    line.setToolTipConfig(tooltip);
    chart.addSeries(line);

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
    shadow.setValue(true);

    ToolBar toolBar = new ToolBar();
    toolBar.add(regenerate);
    toolBar.add(animation);
    toolBar.add(shadow);

    ContentPanel panel = new FramedPanel();
    panel.getElement().getStyle().setMargin(10, Unit.PX);
    panel.setCollapsible(true);
    panel.setHeadingText("Blue Chart");
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

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
