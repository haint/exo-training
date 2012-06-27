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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.examples.resources.client.ExampleService;
import com.sencha.gxt.examples.resources.client.ExampleServiceAsync;
import com.sencha.gxt.examples.resources.client.model.Post;
import com.sencha.gxt.examples.resources.client.model.PostProperties;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.LiveGridView;
import com.sencha.gxt.widget.core.client.grid.LiveToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

@Detail(name = "Live Grid", icon = "livegrid", category = "Grid", classes = {Post.class, PostProperties.class, ExampleService.class, ExampleServiceAsync.class})
public class LiveGridExample implements IsWidget, EntryPoint {

  @Override
  public Widget asWidget() {
    final ExampleServiceAsync service = GWT.create(ExampleService.class);

    RpcProxy<PagingLoadConfig, PagingLoadResult<Post>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<Post>>() {
      @Override
      public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Post>> callback) {
        service.getPosts(loadConfig, callback);
      }
    };

    PostProperties props = GWT.create(PostProperties.class);

    ListStore<Post> store = new ListStore<Post>(new ModelKeyProvider<Post>() {
      @Override
      public String getKey(Post item) {
        return "" + item.getId();
      }
    });

    final PagingLoader<PagingLoadConfig, PagingLoadResult<Post>> gridLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Post>>(
        proxy);
    gridLoader.setRemoteSort(true);

    ColumnConfig<Post, String> forumColumn = new ColumnConfig<Post, String>(props.forum(), 150, "Forum");
    ColumnConfig<Post, String> usernameColumn = new ColumnConfig<Post, String>(props.username(), 150, "Username");
    ColumnConfig<Post, String> subjectColumn = new ColumnConfig<Post, String>(props.subject(), 150, "Subject");
    ColumnConfig<Post, Date> dateColumn = new ColumnConfig<Post, Date>(props.date(), 150, "Date");
    dateColumn.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));

    List<ColumnConfig<Post, ?>> l = new ArrayList<ColumnConfig<Post, ?>>();
    l.add(forumColumn);
    l.add(usernameColumn);
    l.add(subjectColumn);
    l.add(dateColumn);

    final LiveGridView<Post> liveGridView = new LiveGridView<Post>();
    liveGridView.setForceFit(true);

    ColumnModel<Post> cm = new ColumnModel<Post>(l);

    Grid<Post> view = new Grid<Post>(store, cm) {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            gridLoader.load(0, liveGridView.getCacheSize());
          }
        });
      }
    };

    view.setLoadMask(true);
    view.setLoader(gridLoader);

    view.setView(liveGridView);

    FramedPanel cp = new FramedPanel();
    cp.setCollapsible(true);
    cp.setHeadingText("Live Grid Example");
    cp.setPixelSize(600, 390);
    cp.addStyleName("margin-10");

    VerticalLayoutContainer con = new VerticalLayoutContainer();
    con.setBorders(true);
    con.add(view, new VerticalLayoutData(1, 1));

    ToolBar toolBar = new ToolBar();
    toolBar.add(new LiveToolItem(view));
    toolBar.addStyleName(ThemeStyles.getStyle().borderTop());
    toolBar.getElement().getStyle().setProperty("borderBottom", "none");

    con.add(toolBar, new VerticalLayoutData(1, 25));

    cp.setWidget(con);

    return cp;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }
}
