/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.chart;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.axis.TimeAxis;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.model.Data;
import com.sencha.gxt.examples.resources.client.model.Site;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

@Detail(name = "Live Chart", icon = "livechart", category = "Charts", classes = Data.class)
public class LiveExample implements IsWidget, EntryPoint {

  public interface SitePropertyAccess extends PropertyAccess<Site> {
    ValueProvider<Site, Date> date();

    @Path("date")
    ModelKeyProvider<Site> nameKey();

    ValueProvider<Site, Double> veins();

    ValueProvider<Site, Double> views();

    ValueProvider<Site, Double> visits();
  }

  private static final SitePropertyAccess siteAccess = GWT.create(SitePropertyAccess.class);
  private static final DateTimeFormat f = DateTimeFormat.getFormat("MMM d");
  private Timer update;

  public Widget asWidget() {
    final Chart<Site> chart = new Chart<Site>(600, 400);

    final ListStore<Site> store = new ListStore<Site>(siteAccess.nameKey());
    Date initial = f.parse("Feb 1");
    for (int i = 0; i < 7; i++) {
      store.add(new Site(initial, Math.random() * 20 + 80, Math.random() * 20 + 40, Math.random() * 20));
      initial = CalendarUtil.copyDate(initial);
      CalendarUtil.addDaysToDate(initial, 1);
    }
    chart.setStore(store);

    NumericAxis<Site> axis = new NumericAxis<Site>();
    axis.setPosition(Position.LEFT);
    axis.addField(siteAccess.visits());
    TextSprite title = new TextSprite("Number of Hits");
    title.setFontSize(18);
    axis.setTitleConfig(title);
    axis.setDisplayGrid(true);
    axis.setMinimum(0);
    axis.setMaximum(100);
    chart.addAxis(axis);

    final TimeAxis<Site> time = new TimeAxis<Site>();
    time.setField(siteAccess.date());
    time.setStartDate(f.parse("Feb 1"));
    time.setEndDate(f.parse("Feb 7"));
    time.setLabelProvider(new LabelProvider<Date>() {
      @Override
      public String getLabel(Date item) {
        return f.format(item);
      }
    });
    chart.addAxis(time);

    LineSeries<Site> series = new LineSeries<Site>();
    series.setYAxisPosition(Position.LEFT);
    series.setYField(siteAccess.visits());
    series.setStroke(new RGB(148, 174, 10));
    series.setShowMarkers(true);
    series.setMarkerIndex(1);
    Sprite marker = Primitives.circle(0, 0, 6);
    marker.setFill(new RGB(148, 174, 10));
    series.setMarkerConfig(marker);
    chart.addSeries(series);

    series = new LineSeries<Site>();
    series.setYAxisPosition(Position.LEFT);
    series.setYField(siteAccess.views());
    series.setStroke(new RGB(17, 95, 166));
    series.setShowMarkers(true);
    series.setMarkerIndex(1);
    marker = Primitives.cross(0, 0, 6);
    marker.setFill(new RGB(17, 95, 166));
    series.setMarkerConfig(marker);
    chart.addSeries(series);

    series = new LineSeries<Site>();
    series.setYAxisPosition(Position.LEFT);
    series.setYField(siteAccess.veins());
    series.setStroke(new RGB(166, 17, 32));
    series.setShowMarkers(true);
    series.setMarkerIndex(1);
    marker = Primitives.diamond(0, 0, 6);
    marker.setFill(new RGB(166, 17, 32));
    series.setMarkerConfig(marker);
    chart.addSeries(series);

    update = new Timer() {
      @Override
      public void run() {
        Date startDate = CalendarUtil.copyDate(time.getStartDate());
        Date endDate = CalendarUtil.copyDate(time.getEndDate());
        CalendarUtil.addDaysToDate(startDate, 1);
        CalendarUtil.addDaysToDate(endDate, 1);
        chart.getStore().add(new Site(endDate, Math.random() * 20 + 80, Math.random() * 20 + 40, Math.random() * 20));
        time.setStartDate(startDate);
        time.setEndDate(endDate);
        chart.redrawChart();
      }
    };

    update.scheduleRepeating(1000);

    ToggleButton animation = new ToggleButton("Animate");
    animation.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        chart.setAnimated(event.getValue());
      }
    });
    animation.setValue(true, true);

    ToolBar toolBar = new ToolBar();
    toolBar.add(animation);

    ContentPanel panel = new FramedPanel();
    panel.getElement().getStyle().setMargin(10, Unit.PX);
    panel.setCollapsible(true);
    panel.setHeadingText("Live Chart");
    panel.setPixelSize(620, 500);
    panel.setBodyBorder(true);

    VerticalLayoutContainer layout = new VerticalLayoutContainer();
    panel.add(layout);

    toolBar.setLayoutData(new VerticalLayoutData(1, -1));
    layout.add(toolBar);

    chart.setLayoutData(new VerticalLayoutData(1, 1));
    layout.add(chart);

    panel.addAttachHandler(new AttachEvent.Handler() {
      @Override
      public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached() == false) {
          update.cancel();
        }
      }
    });

    return panel;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
