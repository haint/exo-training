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
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;

public class ButtonTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    ButtonBar bar = new ButtonBar();
    
//    TextButton btn = new TextButton("test");
//    btn.setMinWidth(175);
//    btn.setIcon(ExampleImages.INSTANCE.add24());
//    btn.setScale(ButtonScale.MEDIUM);
//    btn.setWidth(100);
//    bar.add(btn);
    
    TextButton btn2 = new TextButton("sfd");
    btn2.setScale(ButtonScale.MEDIUM);
    bar.add(btn2);
    
    ToggleButton toggle = new ToggleButton("toggle");
    bar.add(toggle);
    
    RootPanel.get().add(bar);
//    ButtonCellResources ba = GWT.create(ButtonCellResources.class);
//    ba.buttonStyle().ensureInjected();
//
//    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
//
//      @Override
//      public void execute() {
//        final ButtonBar bar = new ButtonBar();
//        bar.setPosition(50, 50);
//
//        final Button btn = new Button();
//        // btn.setText("Click Me");
//        // btn.setWidth(55);
//        btn.setScale(ButtonScale.LARGE);
//        // btn.setPixelSize(200, 100);
//
//        btn.setIcon(ExampleImages.INSTANCE.add24());
//
//        // btn.setIconAlign(IconAlign.BOTTOM);
//        bar.add(btn);
//
//        Button menuButton = new Button("Split Button Baby");
//        menuButton.setArrowAlign(ButtonArrowAlign.RIGHT);
//
//        Menu menu = new Menu();
//        menu.add(new MenuItem("Foo"));
//        menuButton.setMenu(menu);
//
//        bar.add(menuButton);
//
//        bar.add(new ToggleButton("toggle me"));
//
//        SplitButton split = new SplitButton("Split Button");
//        split.setArrowAlign(ButtonArrowAlign.BOTTOM);
//        split.setIcon(ExampleImages.INSTANCE.add32());
//        split.setScale(ButtonScale.LARGE);
//        split.setMenu(new Menu());
//        bar.add(split);
//
//        // btn = new Button();
//        // btn.setText("Click Me");
//        // btn.setIcon(ExampleImages.INSTANCE.add16());
//        // bar.add(btn);
//
//        RootPanel.get().add(bar);
//
//      }
//
//    });

  }

}
