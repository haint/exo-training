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
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.HttpProxy;
import com.sencha.gxt.data.client.loader.XmlReader;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class XmlGridTest implements EntryPoint {

  interface XmlAutoBeanFactory extends AutoBeanFactory {
    static XmlAutoBeanFactory instance = GWT.create(XmlAutoBeanFactory.class);

    AutoBean<EmailCollection> items();

    AutoBean<ListLoadConfig> loadConfig();

  }

  public interface Email {
    @PropertyName("Name")
    String getName();

    @PropertyName("Email")
    String getEmail();

    @PropertyName("Phone")
    String getPhone();

    @PropertyName("State")
    String getState();

    @PropertyName("Zip")
    String getZip();
  }

  interface EmailCollection {
    @PropertyName("record")
    List<Email> getValues();
  }

  interface EmailProperties extends PropertyAccess<Email> {

    ValueProvider<Email, String> name();

    ValueProvider<Email, String> email();

    ValueProvider<Email, String> phone();

    ValueProvider<Email, String> state();

    ValueProvider<Email, String> zip();
  }

  @Override
  public void onModuleLoad() {
    String path = "data/data.xml";
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, path);
    HttpProxy<ListLoadConfig> proxy = new HttpProxy<ListLoadConfig>(builder);

    XmlReader<ListLoadResult<Email>, EmailCollection> reader = new XmlReader<ListLoadResult<Email>, EmailCollection>(
        XmlAutoBeanFactory.instance, EmailCollection.class) {
      protected com.sencha.gxt.data.shared.loader.ListLoadResult<Email> createReturnData(Object loadConfig,
          EmailCollection records) {
        return new ListLoadResultBean<Email>(records.getValues());
      };
    };

    ListStore<Email> store = new ListStore<Email>(new ModelKeyProvider<Email>() {
      @Override
      public String getKey(Email item) {
        return item.getEmail() + item.getName();
      }
    });

    final ListLoader<ListLoadConfig, ListLoadResult<Email>> loader = new ListLoader<ListLoadConfig, ListLoadResult<Email>>(
        proxy, reader);
    loader.useLoadConfig(XmlAutoBeanFactory.instance.create(ListLoadConfig.class).as());
    loader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, Email, ListLoadResult<Email>>(store));

    EmailProperties props = GWT.create(EmailProperties.class);

    ColumnConfig<Email, String> cc1 = new ColumnConfig<Email, String>(props.name(), 100, "Sender");
    ColumnConfig<Email, String> cc2 = new ColumnConfig<Email, String>(props.email(), 165, "Email");
    ColumnConfig<Email, String> cc3 = new ColumnConfig<Email, String>(props.phone(), 100, "Phone");
    ColumnConfig<Email, String> cc4 = new ColumnConfig<Email, String>(props.state(), 50, "State");
    ColumnConfig<Email, String> cc5 = new ColumnConfig<Email, String>(props.zip(), 65, "Zip Code");

    List<ColumnConfig<Email, ?>> l = new ArrayList<ColumnConfig<Email, ?>>();
    l.add(cc1);
    l.add(cc2);
    l.add(cc3);
    l.add(cc4);
    l.add(cc5);
    ColumnModel<Email> cm = new ColumnModel<Email>(l);

    Grid<Email> view = new Grid<Email>(store, cm);
    view.getView().setForceFit(true);
    view.setBorders(true);
    view.setLoadMask(true);
    view.setLoader(loader);

    FramedPanel cp = new FramedPanel();
    cp.setHeadingText("Xml Grid Example");
    cp.setWidget(view);
    cp.setPixelSize(500, 400);
    cp.setCollapsible(true);
    cp.setAnimCollapse(true);
    cp.addStyleName("margin-10");
    cp.setButtonAlign(BoxLayoutPack.CENTER);
    cp.addButton(new TextButton("Load Xml", new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        loader.load();
      }
    }));
    
    RootPanel.get().add(cp);
  }

}
