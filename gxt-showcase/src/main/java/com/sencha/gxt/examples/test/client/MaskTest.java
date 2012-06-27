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
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class MaskTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final SimpleContainer con = new SimpleContainer();
    con.setBorders(true);
    con.setPixelSize(400, 400);
    
    RootPanel.get().add(con);
    RootPanel.get().add(new TextButton("Mask", new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        con.getElement().mask("foo");
      }
    }));
    RootPanel.get().add(new TextButton("Mask No Message", new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        con.getElement().mask(null);
      }
    }));
    RootPanel.get().add(new TextButton("Unmask", new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        con.getElement().unmask();
      }
    }));
  }

}
