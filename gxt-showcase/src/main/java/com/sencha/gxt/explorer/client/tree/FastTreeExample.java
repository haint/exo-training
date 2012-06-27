/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.tree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.examples.resources.client.model.BaseDto;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.BeforeExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.BeforeExpandItemEvent.BeforeExpandItemHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;

@Example.Detail(name = "Fast Tree", category = "Tree", icon = "fasttree")
public class FastTreeExample implements IsWidget, EntryPoint {

  private int counter = 0;

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return item.getId().toString();
    }
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

  @Override
  public Widget asWidget() {
    final ContentPanel panel = new ContentPanel();
    panel.setHeadingText("Fast Tree");
    panel.setPixelSize(315, 400);
    panel.addStyleName("margin-10");

    final TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());

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
    }) {
      protected boolean hasChildren(BaseDto model) {
        return true;
      };
    };

    tree.addBeforeExpandHandler(new BeforeExpandItemHandler<BaseDto>() {
      @Override
      public void onBeforeExpand(BeforeExpandItemEvent<BaseDto> event) {
        TreeNode<BaseDto> node = tree.findNode(event.getItem());
        if (store.getChildCount(node.getModel()) != 0) {
          return;
        }
        List<BaseDto> list = new ArrayList<BaseDto>();
        for (int i = 0; i < 500; i++) {
          BaseDto m = createModel("Tree Item " + i);
          list.add(m);
        }
        tree.getStore().add(event.getItem(), list);
      }
    });

    tree.addExpandHandler(new ExpandItemHandler<BaseDto>() {

      @Override
      public void onExpand(ExpandItemEvent<BaseDto> event) {
        panel.setHeadingText("FastTree - This tree is handling " + store.getAllItemsCount() + " children");
      }
    });
    BaseDto m = createModel("Fast Tree");
    store.add(m);

    panel.add(tree);
    return panel;
  }

  private BaseDto createModel(String n) {
    return new BaseDto(counter++, n);
  }

}
