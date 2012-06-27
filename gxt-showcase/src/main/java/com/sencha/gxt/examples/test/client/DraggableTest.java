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
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class DraggableTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    FlowLayoutContainer container = new FlowLayoutContainer();
    container.getElement().makePositionable();
    container.setPixelSize(400, 400);
    container.setBorders(true);
    
    TextButton btn = new TextButton("Clic Me");
    
    Draggable d = new Draggable(btn);
    d.setUseProxy(false);
    
    container.add(btn);
    
    RootPanel.get().add(container);
  }

}
