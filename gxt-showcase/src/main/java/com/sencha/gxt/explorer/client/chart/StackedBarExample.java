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
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelProvider;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Movies;
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

@Detail(name = "Stacked Bar Chart", icon = "stackedbarchart", category = "Charts", classes = Movies.class)
public class StackedBarExample implements IsWidget, EntryPoint {

  public interface MoviesPropertyAccess extends PropertyAccess<Movies> {
    ValueProvider<Movies, Double> action();

    ValueProvider<Movies, Double> comedy();

    ValueProvider<Movies, Double> drama();

    @Path("year")
    ModelKeyProvider<Movies> nameKey();

    ValueProvider<Movies, Double> thriller();

    ValueProvider<Movies, Integer> year();
  }

  private static final MoviesPropertyAccess moviesAccess = GWT.create(MoviesPropertyAccess.class);

  private LabelProvider<Number> million = new LabelProvider<Number>() {
    @Override
    public String getLabel(Number item) {
      int value = item.intValue() / 1000000;
      return value + "M";
    }
  };

  @Override
  public Widget asWidget() {
    final ListStore<Movies> store = new ListStore<Movies>(moviesAccess.nameKey());
    store.addAll(TestData.getMovieData(2005, 5, 0, 160000000));

    final Chart<Movies> chart = new Chart<Movies>();
    chart.setStore(store);
    chart.setShadowChart(true);

    NumericAxis<Movies> axis = new NumericAxis<Movies>();
    axis.setPosition(Position.BOTTOM);
    axis.addField(moviesAccess.comedy());
    axis.addField(moviesAccess.action());
    axis.addField(moviesAccess.drama());
    axis.addField(moviesAccess.thriller());
    axis.setDisplayGrid(true);
    axis.setLabelProvider(million);
    chart.addAxis(axis);

    CategoryAxis<Movies, Integer> catAxis = new CategoryAxis<Movies, Integer>();
    catAxis.setPosition(Position.LEFT);
    catAxis.setField(moviesAccess.year());
    chart.addAxis(catAxis);

    final BarSeries<Movies> bar = new BarSeries<Movies>();
    bar.setYAxisPosition(Position.BOTTOM);
    bar.addYField(moviesAccess.comedy());
    bar.addYField(moviesAccess.action());
    bar.addYField(moviesAccess.drama());
    bar.addYField(moviesAccess.thriller());
    bar.addColor(new RGB(148, 174, 10));
    bar.addColor(new RGB(17, 95, 166));
    bar.addColor(new RGB(166, 17, 32));
    bar.addColor(new RGB(255, 136, 9));
    bar.setStacked(true);
    SeriesToolTipConfig<Movies> config = new SeriesToolTipConfig<Movies>();
    config.setLabelProvider(new SeriesLabelProvider<Movies>() {
      @Override
      public String getLabel(Movies item, ValueProvider<? super Movies, ? extends Number> valueProvider) {
        return valueProvider.getValue(item).intValue() / 1000000 + "M";
      }
    });
    config.setDismissDelay(2000);
    bar.setToolTipConfig(config);
    chart.addSeries(bar);

    final Legend<Movies> legend = new Legend<Movies>();
    legend.setPosition(Position.RIGHT);
    legend.setItemHiding(true);
    legend.setItemHighlighting(true);
    chart.setLegend(legend);

    TextButton regenerate = new TextButton("Reload Data");
    regenerate.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        store.clear();
        store.addAll(TestData.getMovieData(2005, 5, 0, 160000000));
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
    panel.setHeadingText("Stacked Bar Chart");
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
