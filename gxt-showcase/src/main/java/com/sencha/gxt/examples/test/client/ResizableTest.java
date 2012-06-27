/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class ResizableTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    FlowLayoutContainer con = new FlowLayoutContainer();
    con.setPixelSize(300, 300);
    con.setBorders(true);

    final TextButton button = new TextButton("Test");
    con.add(button);

    con.addResizeHandler(new ResizeHandler() {

      @Override
      public void onResize(ResizeEvent event) {
        button.setPixelSize(event.getWidth(), event.getHeight());
      }
    });

    new Resizable(con);

    RootPanel.get().add(con);
  }

}
