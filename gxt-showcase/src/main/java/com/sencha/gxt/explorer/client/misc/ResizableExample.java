/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.misc;


//@Detail(name = "Resizable", icon = "resizable", category = "Misc")
//public class ResizableExample implements IsWidget, EntryPoint {
//  
//  public Widget asWidget() {
//    LayoutContainer con = new LayoutContainer();
//    con.setLayout(new AbsoluteLayout());
//    con.getElement().makePositionable();
//
//    for (int i = 0; i < 2; i++) {
//      ContentPanel cp = new ContentPanel();
//      cp.setHeading("8-Way Resizing " + (i == 1 ? "Dynamic" : ""));
//      cp.setIcon(Resources.IMAGES.text());
//      cp.setBodyStyleName("pad-text");
//      cp.add(new Label(TestData.DUMMY_TEXT_SHORT));
//
//      cp.setWidth(200);
//
//      Draggable d = new Draggable(cp);
//      d.setContainer(con);
//      
//      Resizable r = new Resizable(cp);
//      r.setDynamic(i == 1);
//
//      con.add(cp, new AbsoluteData(10, i == 0 ? 10 : 150));
//    }
//    return con;
//  }
//
//  public void onModuleLoad() {
//    RootPanel.get().add(asWidget());
//  }
//
//}
