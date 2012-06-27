/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.treegrid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.examples.resources.client.model.BaseDto;
import com.sencha.gxt.examples.resources.client.model.FolderDto;
import com.sencha.gxt.examples.resources.client.model.MusicDto;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

@Example.Detail(name = "Basic TreeGrid", category = "TreeGrid", icon = "basictreegrid")
public class BasicTreeGridExample implements IsWidget, EntryPoint {

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }

  @Override
  public Widget asWidget() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("TreeGrid");
    panel.addStyleName("margin-10");
    panel.setPixelSize(600, 300);

    VerticalLayoutContainer v = new VerticalLayoutContainer();
    v.setBorders(true);
    panel.add(v);

    TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());

    FolderDto root = TestData.getMusicRootFolder();
    for (BaseDto base : root.getChildren()) {
      store.add(base);
      if (base instanceof FolderDto) {
        processFolder(store, (FolderDto) base);
      }
    }

    ColumnConfig<BaseDto, String> cc1 = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>("name"));
    cc1.setHeader("Name");

    ColumnConfig<BaseDto, String> cc2 = new ColumnConfig<BaseDto, String>(new ValueProvider<BaseDto, String>() {

      @Override
      public String getValue(BaseDto object) {
        return object instanceof MusicDto ? ((MusicDto) object).getAuthor() : "";
      }

      @Override
      public void setValue(BaseDto object, String value) {
        if (object instanceof MusicDto) {
          ((MusicDto) object).setAuthor(value);
        }
      }

      @Override
      public String getPath() {
        return "author";
      }
    });
    cc2.setHeader("Author");

    ColumnConfig<BaseDto, String> cc3 = new ColumnConfig<BaseDto, String>(new ValueProvider<BaseDto, String>() {

      @Override
      public String getValue(BaseDto object) {
        return object instanceof MusicDto ? ((MusicDto) object).getGenre() : "";
      }

      @Override
      public void setValue(BaseDto object, String value) {
        if (object instanceof MusicDto) {
          ((MusicDto) object).setGenre(value);
        }
      }

      @Override
      public String getPath() {
        return "genre";
      }
    });
    cc3.setHeader("Genre");
    cc3.setCell(new TextCell());
    List<ColumnConfig<BaseDto, ?>> l = new ArrayList<ColumnConfig<BaseDto, ?>>();
    l.add(cc1);
    l.add(cc2);
    l.add(cc3);
    ColumnModel<BaseDto> cm = new ColumnModel<BaseDto>(l);

    final TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(store, cm, cc1);
    tree.getStyle().setLeafIcon(ExampleImages.INSTANCE.music());
    tree.getView().setAutoExpandColumn(cc1);
    ToolBar buttonBar = new ToolBar();

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

    v.add(buttonBar, new VerticalLayoutData(1, -1));
    v.add(tree, new VerticalLayoutData(1, 1));
    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
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
