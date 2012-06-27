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
import com.sencha.gxt.examples.resources.client.model.BaseDto;
import com.sencha.gxt.examples.resources.client.model.FolderDto;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class TreeTest implements EntryPoint {

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }

  @Override
  public void onModuleLoad() {
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

    final TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());
    loader.addLoadHandler(new ChildTreeStoreBinding<BaseDto>(store));

    final Tree<BaseDto, String> tree = new Tree<BaseDto, String>(store, new ValueProvider<BaseDto, String>() {

      @Override
      public String getValue(BaseDto object) {
        return object.getName();
      }

      @Override
      public void setValue(BaseDto object, String value) {
        object.setName(value);

      }

      @Override
      public String getPath() {
        return "name";
      }
    });
    tree.setLoader(loader);

    RootPanel.get().add(tree);
    
    RootPanel.get().add(new TextButton("sdfdfs", new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        BaseDto m = store.getRootItems().get(0);
        tree.refresh(m);
      }
    }));
  }

}
