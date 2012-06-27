/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MenuBarTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    MenuBar bar = new MenuBar();

    Menu menu = new Menu();
    menu.add(new MenuItem("item 1"));
    menu.add(new MenuItem("item 2"));

    MenuBarItem item = new MenuBarItem("Click Me", menu);
    bar.add(item);

    menu = new Menu();
    menu.add(new MenuItem("item 1"));
    menu.add(new MenuItem("item 2"));

    item = new MenuBarItem("Click Me 2", menu);
    bar.add(item);

    RootPanel.get().add(bar);
  }

}
