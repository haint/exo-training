/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.NumberCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Task;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GroupSummaryView;
import com.sencha.gxt.widget.core.client.grid.SummaryColumnConfig;
import com.sencha.gxt.widget.core.client.grid.SummaryRenderer;
import com.sencha.gxt.widget.core.client.grid.SummaryType;

@Detail(name = "Live GroupSummary", icon = "livegroupsummary", category = "Grid", classes = {Task.class})
public class LiveGroupSummaryExample implements EntryPoint, IsWidget {

  interface TaskProperties extends PropertyAccess<Task> {

    @Path("id")
    ModelKeyProvider<Task> key();

    ValueProvider<Task, String> description();

    ValueProvider<Task, String> project();

    ValueProvider<Task, String> due();

    ValueProvider<Task, Double> estimate();

    ValueProvider<Task, Double> rate();
  }

  @Override
  public Widget asWidget() {
    List<Task> tasks = TestData.getTasks();

    TaskProperties properties = GWT.create(TaskProperties.class);
    final ListStore<Task> store = new ListStore<Task>(properties.key());
    store.addAll(tasks);

    SummaryColumnConfig<Task, String> desc = new SummaryColumnConfig<Task, String>(properties.description(), 65, "Task");
    desc.setSummaryType(new SummaryType.CountSummaryType<String>());
    desc.setSummaryRenderer(new SummaryRenderer<Task>() {

      @Override
      public SafeHtml render(Number value, Map<ValueProvider<? super Task, ?>, Number> data) {
        return SafeHtmlUtils.fromTrustedString(value.intValue() > 1 ? "(" + value.intValue() + " Tasks)" : "(1 Task)");
      }
    });

    final SummaryColumnConfig<Task, String> project = new SummaryColumnConfig<Task, String>(properties.project(), 55,
        "Project");

    SummaryColumnConfig<Task, String> due = new SummaryColumnConfig<Task, String>(properties.due(), 20, "Due");

    SummaryColumnConfig<Task, Double> estimate = new SummaryColumnConfig<Task, Double>(properties.estimate(), 20,
        "Estimate");
    estimate.setSummaryType(new SummaryType.SumSummaryType<Double>());
    estimate.setSummaryRenderer(new SummaryRenderer<Task>() {
      @Override
      public SafeHtml render(Number value, Map<ValueProvider<? super Task, ?>, Number> data) {
        return SafeHtmlUtils.fromTrustedString(value + " hours");
      }
    });
    estimate.setCell(new AbstractCell<Double>() {
      @Override
      public void render(com.google.gwt.cell.client.Cell.Context context, Double value, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant(value + " hours");
      }
    });

    SummaryColumnConfig<Task, Double> rate = new SummaryColumnConfig<Task, Double>(properties.rate(), 20, "Rate");
    rate.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    rate.setCell(new NumberCell<Double>(NumberFormat.getCurrencyFormat()));
    rate.setSummaryType(new SummaryType.AvgSummaryType<Double>());
    rate.setSummaryFormat(NumberFormat.getCurrencyFormat());

    SummaryColumnConfig<Task, Task> cost = new SummaryColumnConfig<Task, Task>(new IdentityValueProvider<Task>(), 20,
        "Cost");
    cost.setColumnClassSuffix("cost");
    cost.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    cost.setCell(new AbstractCell<Task>() {
      @Override
      public void render(com.google.gwt.cell.client.Cell.Context context, Task value, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant(NumberFormat.getCurrencyFormat().format(value.getRate() * value.getEstimate()));
      }
    });
    cost.setSummaryFormat(NumberFormat.getCurrencyFormat());
    cost.setSummaryType(new SummaryType<Task, Double>() {

      @Override
      public <M> Double calculate(List<? extends M> m, ValueProvider<? super M, Task> valueProvider) {
        double value = 0;
        for (int i = 0; i < m.size(); i++) {
          Task t = valueProvider.getValue(m.get(i));
          value = value + (t.getRate() * t.getEstimate());
        }
        return value;
      }

    });
    cost.setComparator(new Comparator<Task>() {
      @Override
      public int compare(Task o1, Task o2) {
        return Double.valueOf(o1.getRate() * o1.getEstimate()).compareTo(o2.getRate() * o2.getEstimate());
      }
    });

    List<ColumnConfig<Task, ?>> cfgs = new ArrayList<ColumnConfig<Task, ?>>();
    cfgs.add(desc);
    cfgs.add(project);
    cfgs.add(due);
    cfgs.add(estimate);
    cfgs.add(rate);
    cfgs.add(cost);

    ColumnModel<Task> cm = new ColumnModel<Task>(cfgs);

    final GroupSummaryView<Task> summary = new GroupSummaryView<Task>();
    summary.setForceFit(true);
    summary.setShowGroupedColumn(false);

    Grid<Task> grid = new Grid<Task>(store, cm);
    grid.setBorders(true);
    grid.setView(summary);
    grid.getView().setShowDirtyCells(false);

    Scheduler.get().scheduleFinally(new ScheduledCommand() {
      @Override
      public void execute() {
        summary.groupBy(project);
      }
    });

    FramedPanel panel = new FramedPanel();
    panel.setHeadingHtml("Sponsored Projects");
    panel.setSize("800", "450");
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
