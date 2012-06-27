/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.examples.resources.client.ExampleService;
import com.sencha.gxt.examples.resources.client.ExampleServiceAsync;
import com.sencha.gxt.examples.resources.client.model.Post;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class PagingGridTest implements EntryPoint {

  interface PostProperties extends PropertyAccess<Post> {
    ModelKeyProvider<Post> id();

    ValueProvider<Post, String> username();
    
    ValueProvider<Post, String> forum();
    
    ValueProvider<Post, String> subject();
  }
  
  @Override
  public void onModuleLoad() {
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
    
    final PagingLoader<PagingLoadConfig, PagingLoadResult<Post>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Post>>(
        proxy);
    loader.setRemoteSort(true);
    loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, Post, PagingLoadResult<Post>>(store));
    

    
    final PagingToolBar toolBar = new PagingToolBar(50);
    toolBar.bind(loader);
    
    ColumnConfig<Post, String> forumColumn = new ColumnConfig<Post, String>(props.forum(), 150, "Forum");
    ColumnConfig<Post, String> usernameColumn = new ColumnConfig<Post, String>(props.username(), 150, "Username");
    ColumnConfig<Post, String> subjectColumn = new ColumnConfig<Post, String>(props.subject(), 150, "Subject");
    ColumnConfig<Post, String> dateColumn = new ColumnConfig<Post, String>(props.forum(), 150, "Date");
    
    List<ColumnConfig<Post, ?>> l = new ArrayList<ColumnConfig<Post, ?>>();
    l.add(forumColumn);
    l.add(usernameColumn);
    l.add(subjectColumn);
    l.add(dateColumn);
    
    ColumnModel<Post> cm = new ColumnModel<Post>(l);
    
    Grid<Post> view = new Grid<Post>(store, cm) {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        loader.load();
      }
    };
    view.getView().setForceFit(true);
    view.setLoader(loader);
    
    ContentPanel cp = new ContentPanel();
    cp.setHeadingText("Paging Grid Example");
    cp.setPixelSize(500, 400);
    cp.addStyleName("margin-10");
 
    
    VerticalLayoutContainer con = new VerticalLayoutContainer();
    con.add(view, new VerticalLayoutData(1, 1));
    con.add(toolBar, new VerticalLayoutData(1, -1));
    cp.setWidget(con);
    
    RootPanel.get().add(cp);
  }

}
