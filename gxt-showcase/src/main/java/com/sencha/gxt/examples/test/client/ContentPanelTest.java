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
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ContentPanelTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("ContentPanel");
    panel.setPixelSize(400, 400);
    panel.setCollapsible(true);
    panel.setAnimCollapse(true);
    panel.setPagePosition(200, 200);
    panel.setBodyBorder(false);
//    panel.collapse();
  
    
    ToolBar bar = new ToolBar();
    bar.add(new TextButton("Foo"));
    
    
   
    RootPanel.get().add(panel);
  }

}
