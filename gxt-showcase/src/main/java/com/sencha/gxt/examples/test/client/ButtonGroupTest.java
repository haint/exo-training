/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.widget.core.client.button.ButtonGroup;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ButtonGroupTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    ButtonGroup group = new ButtonGroup();
    group.setHeadingText("Button Group");

    FlexTable table = new FlexTable();
    
    TextButton button = new TextButton("Cool");
    button.setIcon(ExampleImages.INSTANCE.add16());
    table.setWidget(0, 0, button);
    
    button = new TextButton("Copy");
    button.setIcon(ExampleImages.INSTANCE.add16());
    table.setWidget(0, 1, button);
    
    button = new TextButton("Add");
    button.setIcon(ExampleImages.INSTANCE.user_add());
    table.setWidget(1, 0, button);
    
    button = new TextButton("Delete");
    button.setIcon(ExampleImages.INSTANCE.user_delete());
    table.setWidget(1, 1, button);
    
    group.add(table);
    
    ToolBar bar = new ToolBar();
    bar.add(group);
    
    VerticalPanel vp = new VerticalPanel();
    // tool bar requires a width, vertical panel is table based so auto width
    vp.setWidth("300px");
    vp.setSpacing(10);
    vp.add(bar);
    RootPanel.get().add(vp);
  }

}
