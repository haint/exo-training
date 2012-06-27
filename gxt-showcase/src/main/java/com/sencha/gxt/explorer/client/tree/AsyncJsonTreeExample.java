/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.tree;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.HttpProxy;
import com.sencha.gxt.data.client.writer.UrlEncodingWriter;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.ChildTreeStoreBinding;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree;

@Detail(name = "Async Json Tree", category = "Tree", icon = "asyncxmltreepanel")
public class AsyncJsonTreeExample implements IsWidget, EntryPoint {

  public interface JsonTreeAutoBeanFactory extends AutoBeanFactory {

    AutoBean<RecordResult> items();

  }

  /**
   * Defines the structure of our JSON records.
   */
  public interface Record {

    public Integer getId();

    public String getName();

    public boolean isFolder();

  }

  /**
   * Defines the structure of the root JSON object being returned by the server.
   * This class is needed as we cannot return a list of objects. Instead, we
   * return a single object with a single property that contains the data
   * records.
   */
  public interface RecordResult {

    List<Record> getRecords();
  }

  private class RecordKeyProvider implements ModelKeyProvider<Record> {
    @Override
    public String getKey(Record item) {
      return item.getId().toString();
    }
  }

  private class DataRecordJsonReader extends JsonReader<List<Record>, RecordResult> {
    public DataRecordJsonReader(AutoBeanFactory factory, Class<RecordResult> rootBeanType) {
      super(factory, rootBeanType);
    }

    @Override
    protected List<Record> createReturnData(Object loadConfig, RecordResult incomingData) {
      return incomingData.getRecords();
    }
  }

  public Widget asWidget() {
    JsonTreeAutoBeanFactory factory = GWT.create(JsonTreeAutoBeanFactory.class);

    DataRecordJsonReader reader = new DataRecordJsonReader(factory, RecordResult.class);

    RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT.getModuleBaseURL() + "jsontreeloader");
    HttpProxy<Record> jsonProxy = new HttpProxy<Record>(rb);
    jsonProxy.setWriter(new UrlEncodingWriter<Record>(factory, Record.class));

    TreeLoader<Record> loader = new TreeLoader<Record>(jsonProxy, reader) {
      @Override
      public boolean hasChildren(Record parent) {
        return parent.isFolder();
      }

    };

    ContentPanel panel = new ContentPanel();
    panel.setHeadingText("Async Json Tree");
    panel.setPixelSize(315, 400);
    panel.addStyleName("margin-10");
    
    VerticalLayoutContainer con = new VerticalLayoutContainer();
    panel.add(con);


    TreeStore<Record> store = new TreeStore<Record>(new RecordKeyProvider());
    loader.addLoadHandler(new ChildTreeStoreBinding<Record>(store));

    final Tree<Record, String> tree = new Tree<Record, String>(store, new ValueProvider<Record, String>() {

      @Override
      public String getValue(Record object) {
        return object.getName();
      }

      @Override
      public void setValue(Record object, String value) {
      }

      @Override
      public String getPath() {
        return "name";
      }
    });
    tree.setLoader(loader);
    tree.getStyle().setLeafIcon(ExampleImages.INSTANCE.music());

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

    con.add(buttonBar, new VerticalLayoutData(1, -1));
    con.add(tree, new VerticalLayoutData(1, 1));
    
    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
