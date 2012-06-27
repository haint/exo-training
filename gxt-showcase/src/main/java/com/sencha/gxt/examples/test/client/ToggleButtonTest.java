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
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.button.ToggleButton;

public class ToggleButtonTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    ToggleGroup group = new ToggleGroup();
    
    ToggleButton toggle = new ToggleButton("Toggle");
    toggle.setAllowDepress(false);
    group.add(toggle);
    RootPanel.get().add(toggle);
    
    toggle = new ToggleButton("Toggle 2");
    toggle.setAllowDepress(false);
    group.add(toggle);
    RootPanel.get().add(toggle);
    
    toggle = new ToggleButton("Toggle 3");
    toggle.setAllowDepress(false);
    group.add(toggle);
    RootPanel.get().add(toggle);
  }

}
