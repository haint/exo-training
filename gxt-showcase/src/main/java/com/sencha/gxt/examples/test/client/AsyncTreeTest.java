/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.ChildTreeStoreBinding;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.examples.resources.client.ExampleService;
import com.sencha.gxt.examples.resources.client.ExampleServiceAsync;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.examples.resources.client.model.BaseDto;
import com.sencha.gxt.examples.resources.client.model.FolderDto;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class AsyncTreeTest implements EntryPoint {

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }
  
  @Override
  public void onModuleLoad() {
    FlowLayoutContainer con = new FlowLayoutContainer();

    final ExampleServiceAsync service = GWT.create(ExampleService.class);
    RpcProxy<BaseDto, List<BaseDto>> proxy = new RpcProxy<BaseDto, List<BaseDto>>() {

      @Override
      public void load(BaseDto loadConfig, AsyncCallback<List<BaseDto>> callback) {
        service.getMusicFolderChildren((FolderDto) loadConfig, callback);
      }
    };

    TreeLoader<BaseDto> loader = new TreeLoader<BaseDto>(proxy) {
      @Override
      public boolean hasChildren(BaseDto parent) {
        return parent instanceof FolderDto;
      }
    };

    TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());
    loader.addLoadHandler(new ChildTreeStoreBinding<BaseDto>(store));

    final Tree<BaseDto, String> tree = new Tree<BaseDto, String>(store, new ValueProvider<BaseDto, String>() {

      @Override
      public String getValue(BaseDto object) {
        return object.getName();
      }

      @Override
      public void setValue(BaseDto object, String value) {
      }

      @Override
      public String getPath() {
        return "name";
      }
    });
    tree.setLoader(loader);

//    TreeStateHandler<BaseDto> stateHandler = new TreeStateHandler<BaseDto>(tree);
//    stateHandler.loadState();
    tree.getStyle().setLeafIcon(ExampleImages.INSTANCE.music());

    ButtonBar buttonBar = new ButtonBar();

    buttonBar.add(new TextButton("Expand All", new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        tree.expandAll();
      }
    }));
    buttonBar.add(new TextButton("Collapse All", new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        tree.collapseAll();
      }

    }));

    buttonBar.setLayoutData(new MarginData(4));
    con.add(buttonBar);
    con.add(tree);
    
    RootPanel.get().add(con);
  }

}
