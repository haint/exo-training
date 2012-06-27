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
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.sencha.gxt.examples.resources.shared.ExampleRequestFactory;
import com.sencha.gxt.examples.resources.shared.PostProxy;
import com.sencha.gxt.examples.resources.shared.PostRequest;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

@Detail(name = "RequestFactory Grid", icon = "requestfactory", category = "Grid", classes = {
    PostProxy.class, ExampleRequestFactory.class, PostRequest.class, ExampleRequestFactory.class})
public class RequestFactoryGridExample implements EntryPoint, IsWidget {
  interface PostProxyProperties extends PropertyAccess<PostProxy> {
    ModelKeyProvider<PostProxy> id();

    ValueProvider<PostProxy, String> username();

    ValueProvider<PostProxy, String> forum();

    ValueProvider<PostProxy, String> subject();

    ValueProvider<PostProxy, Date> date();
  }
  @Override
  public Widget asWidget() {
    final ExampleRequestFactory rf = GWT.create(ExampleRequestFactory.class);
    rf.initialize(new SimpleEventBus());

    RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<PostProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<PostProxy>>() {
      @Override
      public void load(FilterPagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<PostProxy>> receiver) {
        PostRequest req = rf.post();
        List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());
        List<FilterConfig> filterConfig = createRequestFilterConfig(req, loadConfig.getFilters());
        req.getPosts(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo, filterConfig).to(receiver);
        
        req.fire();
      }
    };
    final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<PostProxy>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<PostProxy>>(
        proxy) {
      @Override
      protected FilterPagingLoadConfig newLoadConfig() {
        return new FilterPagingLoadConfigBean();
      }
    };
    loader.setRemoteSort(true);

    PostProxyProperties props = GWT.create(PostProxyProperties.class);

    ListStore<PostProxy> store = new ListStore<PostProxy>(props.id());
    loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, PostProxy, PagingLoadResult<PostProxy>>(
        store));

    final PagingToolBar toolBar = new PagingToolBar(50);
    toolBar.getElement().getStyle().setProperty("borderBottom", "none");
    toolBar.bind(loader);

    ColumnConfig<PostProxy, String> forumColumn = new ColumnConfig<PostProxy, String>(props.forum(), 150, "Forum");
    ColumnConfig<PostProxy, String> usernameColumn = new ColumnConfig<PostProxy, String>(props.username(), 150,
        "Username");
    ColumnConfig<PostProxy, String> subjectColumn = new ColumnConfig<PostProxy, String>(props.subject(), 150, "Subject");
    ColumnConfig<PostProxy, Date> dateColumn = new ColumnConfig<PostProxy, Date>(props.date(), 150, "Date");
    dateColumn.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));

    List<ColumnConfig<PostProxy, ?>> l = new ArrayList<ColumnConfig<PostProxy, ?>>();
    l.add(forumColumn);
    l.add(usernameColumn);
    l.add(subjectColumn);
    l.add(dateColumn);

    ColumnModel<PostProxy> cm = new ColumnModel<PostProxy>(l);

    Grid<PostProxy> view = new Grid<PostProxy>(store, cm) {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            loader.load();
          }
        });
      }
    };
    view.getView().setForceFit(true);
    view.setLoadMask(true);
    view.setLoader(loader);

    // Create the filters, and hook them to the loader and grid
    GridFilters<PostProxy> filters = new GridFilters<PostProxy>(loader);
    filters.initPlugin(view);
    filters.setLocal(false);//be sure to be remote, or it will affect the local cached data only
    filters.addFilter(new DateFilter<PostProxy>(props.date()));
    filters.addFilter(new StringFilter<PostProxy>(props.subject()));
    filters.addFilter(new StringFilter<PostProxy>(props.forum()));
    filters.addFilter(new StringFilter<PostProxy>(props.username()));

    FramedPanel cp = new FramedPanel();
    cp.setHeadingText("RequestFactory Grid Example");
    cp.setPixelSize(500, 400);
    cp.addStyleName("margin-10");

    VerticalLayoutContainer con = new VerticalLayoutContainer();
    con.setBorders(true);
    con.add(view, new VerticalLayoutData(1, 1));
    con.add(toolBar, new VerticalLayoutData(1, -1));
    cp.setWidget(con);

    return cp;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

}
