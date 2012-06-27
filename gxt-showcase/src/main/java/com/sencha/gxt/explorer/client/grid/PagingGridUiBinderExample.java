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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.examples.resources.client.ExampleService;
import com.sencha.gxt.examples.resources.client.ExampleServiceAsync;
import com.sencha.gxt.examples.resources.client.model.Post;
import com.sencha.gxt.examples.resources.client.model.PostProperties;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

@Detail(name = "Paging UiBinder Grid", icon = "paginguibinder", category = "Grid", classes = {Post.class, PostProperties.class, ExampleService.class, ExampleServiceAsync.class}, files = "PagingGridUiBinderExample.ui.xml")
public class PagingGridUiBinderExample implements IsWidget, EntryPoint {
  interface MyUiBinder extends UiBinder<Widget, PagingGridUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
  
  @UiField
  ColumnModel<Post> cm;
  
  @UiField
  ListStore<Post> store;
  
  @UiField
  Grid<Post> grid;
  
  @UiField
  GridView<Post> view;
  
  @UiField
  PagingToolBar toolBar;
  
  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }
  
  @UiFactory ColumnModel<Post> createColumnModel() {
    return cm;
  }
  
  @UiFactory ListStore<Post> createListStore() {
    return store;
  }

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

    store = new ListStore<Post>(new ModelKeyProvider<Post>() {
      @Override
      public String getKey(Post item) {
        return "" + item.getId();
      }
    });
    
    final PagingLoader<PagingLoadConfig, PagingLoadResult<Post>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Post>>(
        proxy);
    loader.setRemoteSort(true);
    loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, Post, PagingLoadResult<Post>>(store));

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

    cm = new ColumnModel<Post>(l);
    
    Widget component = uiBinder.createAndBindUi(this);
    
    grid.setLoader(loader);
    
    Timer t = new Timer() {
      
      @Override
      public void run() {
        loader.load();
      }
    };
    t.schedule(100);
    
    toolBar.getElement().getStyle().setProperty("borderBottom", "none");
    toolBar.bind(loader);


    
    return component;
  }
}
