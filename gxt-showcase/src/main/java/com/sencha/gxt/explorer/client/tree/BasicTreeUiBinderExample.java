/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.tree;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.examples.resources.client.model.BaseDto;
import com.sencha.gxt.examples.resources.client.model.FolderDto;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.tree.Tree;

@Detail(name = "Basic Tree (UiBinder)", icon = "basictree", category = "Tree", files = "BasicTreeUiBinderExample.ui.xml")
public class BasicTreeUiBinderExample implements IsWidget, EntryPoint {

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }

  interface MyUiBinder extends UiBinder<Widget, BasicTreeUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField(provided = true)
  TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());

  @UiField
  Tree<BaseDto, String> tree;

  public Widget asWidget() {

    FolderDto root = TestData.getMusicRootFolder();
    for (BaseDto base : root.getChildren()) {
      store.add(base);
      if (base instanceof FolderDto) {
        processFolder(store, (FolderDto) base);
      }
    }

    Widget widget = uiBinder.createAndBindUi(this);
    widget.addStyleName("margin-10");
    tree.getStyle().setLeafIcon(ExampleImages.INSTANCE.music());
    return widget;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  @UiFactory
  public ValueProvider<BaseDto, String> createValueProvider() {
    return new ValueProvider<BaseDto, String>() {

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
    };
  }

  @UiHandler("expandAll")
  public void expandAll(SelectEvent event) {
    tree.expandAll();
  }

  @UiHandler("collapseAll")
  public void collapseAll(SelectEvent event) {
    tree.collapseAll();
  }

  private void processFolder(TreeStore<BaseDto> store, FolderDto folder) {
    for (BaseDto child : folder.getChildren()) {
      store.add(folder, child);
      if (child instanceof FolderDto) {
        processFolder(store, (FolderDto) child);
      }
    }
  }
}
