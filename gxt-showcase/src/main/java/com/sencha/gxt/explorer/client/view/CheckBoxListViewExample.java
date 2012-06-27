/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.view;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.FramedPanel.FramedPanelAppearance;

public class CheckBoxListViewExample implements IsWidget, EntryPoint {
  public Widget asWidget() {

    // final ExampleServiceAsync service = (ExampleServiceAsync)
    // Registry.get(Examples.SERVICE);

    // RpcProxy<List<Photo>> proxy = new RpcProxy<List<Photo>>() {
    // @Override
    // protected void load(Object loadConfig, AsyncCallback<List<Photo>>
    // callback) {
    // service.getPhotos(callback);
    // }
    // };

    // ListLoader<ListLoadResult<Photo>> loader = new
    // BaseListLoader<ListLoadResult<Photo>>(proxy, new BeanModelReader());
    // ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
    // loader.load();

    final ContentPanel panel = new ContentPanel(GWT.<ContentPanelAppearance> create(FramedPanelAppearance.class));
    panel.setCollapsible(true);
    panel.setAnimCollapse(false);
    panel.setHeadingText("CheckBox ListView (0 items selected)");
    panel.setWidth(300);
    panel.setBodyBorder(false);

    // final CheckBoxListView<BeanModel> view = new
    // CheckBoxListView<BeanModel>() {
    // @Override
    // protected BeanModel prepareData(BeanModel model) {
    // String s = model.get("name");
    // model.set("shortName", Format.ellipse(s, 15));
    // return model;
    // }
    //
    // };

    // view.setStore(store);
    // view.setDisplayProperty("name");
    // view.getSelectionModel().addListener(Events.SelectionChange, new
    // Listener<SelectionChangedEvent<BeanModel>>() {
    //
    // public void handleEvent(SelectionChangedEvent<BeanModel> be) {
    // panel.setHeading("CheckBox ListView (" + be.getSelection().size() +
    // " items selected)");
    // }
    //
    // });
    // panel.add(view);

    Button b = new Button("get checked items");
    b.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        // Info.display("Checked Items", "There are " + view.getChecked().size()
        // + " items checked!");

      }

    });
    panel.add(b);
    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
