/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MenuTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    Menu menu = new Menu();
    
    MenuItem item = new MenuItem("foo");
    item.setIcon(ExampleImages.INSTANCE.add16());
    menu.add(item);
    
    item = new MenuItem("foo");
    item.setIcon(ExampleImages.INSTANCE.add16());
    menu.add(item);
    
    menu.showAt(100, 100);
  }

}
