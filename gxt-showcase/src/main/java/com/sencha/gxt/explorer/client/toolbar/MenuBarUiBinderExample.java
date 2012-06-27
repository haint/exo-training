/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.toolbar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

@Detail(name = "MenuBar (UiBinder)", icon = "menubar", category = "ToolBar & Menu", files = "MenuBarUiBinderExample.ui.xml")
public class MenuBarUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Widget, MenuBarUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  public Widget asWidget() {
    return uiBinder.createAndBindUi(this);
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  @UiHandler(value = {"menuNew", "menuOpen", "menuEdit", "menuSearch", "menuFoo", "menuTheme"})
  public void onMenuSelection(SelectionEvent<Item> event) {
    MenuItem item = (MenuItem) event.getSelectedItem();
    Info.display("Action", "You selected the " + item.getText());
  }
}
