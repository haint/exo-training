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
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.SeriesHighlighter;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.chart.series.SeriesRenderer;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.DrawFx;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Data;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.fx.client.easing.BounceOut;
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

@Detail(name = "Column Renderer Chart", icon = "columnrendererchart", category = "Charts", classes = Data.class)
public class ColumnRendererExample implements IsWidget, EntryPoint {

  public interface DataPropertyAccess extends PropertyAccess<Data> {
    ValueProvider<Data, Double> data1();

    ValueProvider<Data, String> name();

    @Path("name")
    ModelKeyProvider<Data> nameKey();
  }

  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

  @Override
  public Widget asWidget() {
    final Chart<Data> chart = new Chart<Data>();
    chart.setBackground(new RGB(17, 17, 17));
    chart.setAnimationDuration(750);
    chart.setAnimationEasing(new BounceOut());
    chart.setShadowChart(true);

    final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
    store.addAll(TestData.getData(5, 0, 100));
    chart.setStore(store);

    NumericAxis<Data> axis = new NumericAxis<Data>();
    axis.setPosition(Position.LEFT);
    axis.addField(dataAccess.data1());
    PathSprite grid = new PathSprite();
    grid.setStroke(RGB.WHITE);
    axis.setGridDefaultConfig(grid);
    TextSprite title = new TextSprite("Number of Hits");
    title.setFontSize(18);
    title.setFill(RGB.WHITE);
    axis.setTitleConfig(title);
    axis.setDisplayGrid(true);
    PathSprite white = new PathSprite();
    white.setStroke(RGB.WHITE);
    axis.setAxisConfig(white);
    TextSprite whiteText = new TextSprite();
    whiteText.setFill(RGB.WHITE);
    axis.setLabelConfig(whiteText);
    axis.setMinimum(0);
    axis.setMaximum(100);
    chart.addAxis(axis);

    CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
    catAxis.setPosition(Position.BOTTOM);
    catAxis.setField(dataAccess.name());
    title = new TextSprite("Month of the Year");
    title.setFontSize(18);
    title.setFill(RGB.WHITE);
    catAxis.setTitleConfig(title);
    catAxis.setAxisConfig(white);
    catAxis.setLabelConfig(whiteText);
    chart.addAxis(catAxis);

    Gradient grad1 = new Gradient("v-1", 0);
    grad1.addStop(0, new RGB(212, 40, 40));
    grad1.addStop(100, new RGB(117, 14, 14));
    chart.addGradient(grad1);
    Gradient grad2 = new Gradient("v-2", 0);
    grad2.addStop(0, new RGB(180, 216, 42));
    grad2.addStop(100, new RGB(94, 114, 13));
    chart.addGradient(grad2);
    Gradient grad3 = new Gradient("v-3", 0);
    grad3.addStop(0, new RGB(43, 221, 115));
    grad3.addStop(100, new RGB(14, 117, 56));
    chart.addGradient(grad3);
    Gradient grad4 = new Gradient("v-4", 0);
    grad4.addStop(0, new RGB(45, 117, 226));
    grad4.addStop(100, new RGB(14, 56, 117));
    chart.addGradient(grad4);
    Gradient grad5 = new Gradient("v-5", 0);
    grad5.addStop(0, new RGB(187, 45, 222));
    grad5.addStop(100, new RGB(85, 10, 103));
    chart.addGradient(grad5);

    final Color[] colors = {grad1, grad2, grad3, grad4, grad5};

    final BarSeries<Data> column = new BarSeries<Data>();
    column.setYAxisPosition(Position.LEFT);
    column.addYField(dataAccess.data1());
    TextSprite sprite = new TextSprite();
    sprite.setFill(RGB.WHITE);
    sprite.setFontSize(18);
    sprite.setTextAnchor(TextAnchor.MIDDLE);
    SeriesLabelConfig<Data> labelConfig = new SeriesLabelConfig<Data>();
    labelConfig.setSpriteConfig(sprite);
    column.setLabelConfig(labelConfig);
    column.setColumn(true);
    column.setHighlighting(true);
    column.setRenderer(new SeriesRenderer<Data>() {
      @Override
      public void spriteRenderer(Sprite sprite, int index, ListStore<Data> store) {
        sprite.setFill(colors[index % colors.length]);
        sprite.redraw();
      }
    });
    column.setHighlighter(new SeriesHighlighter() {
      @Override
      public void highlight(Sprite sprite) {
        sprite.setStroke(new RGB(85, 85, 204));
        DrawFx.createStrokeWidthAnimator(sprite, 3).run(250);
      }

      @Override
      public void unHighlight(Sprite sprite) {
        sprite.setStroke(Color.NONE);
        DrawFx.createStrokeWidthAnimator(sprite, 0).run(250);
      }
    });
    chart.addSeries(column);

    TextButton regenerate = new TextButton("Reload Data");
    regenerate.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        store.clear();
        store.addAll(TestData.getData(5, 0, 100));
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
        column.clear();
        column.drawSeries();
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
    panel.setHeadingText("Column Renderer Chart");
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
