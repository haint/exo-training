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
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class LayoutContainerTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    FlowLayoutContainer container = new FlowLayoutContainer();
    
    container.setPixelSize(400, 400);
    
    TextButton button = new TextButton("Click Me");
    container.add(button);
    
    RootPanel.get().add(container);
  }

}
