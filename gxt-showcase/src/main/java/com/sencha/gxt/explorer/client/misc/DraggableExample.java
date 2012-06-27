/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.misc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.examples.resources.client.Resources;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.ContentPanel;

@Detail(name = "Draggable", icon = "draggable", category = "Misc", fit=true)
public class DraggableExample implements IsWidget, EntryPoint {
  
  public Widget asWidget() {
    AbsolutePanel con = new AbsolutePanel();

    ContentPanel cp = new ContentPanel();
    cp.setCollapsible(true);
    cp.getHeader().setIcon(Resources.IMAGES.text());
    cp.setBodyStyleName("pad-text");
    cp.setHeadingText("Proxy Drag");
    cp.add(new Label(TestData.DUMMY_TEXT_SHORT));
    cp.setWidth(200);

    Draggable d = new Draggable(cp);

    con.add(cp,10,10);

    cp = new ContentPanel();
    cp.setCollapsible(true);
    cp.setBodyStyleName("pad-text");
    cp.setHeadingText("Direct Drag");
    cp.getHeader().setIcon(Resources.IMAGES.text());
    cp.add(new Label("Drags can only be started from the header."));
    cp.setWidth(200);
    con.add(cp, 240,10);

    d = new Draggable(cp, cp.getHeader());
    d.setUseProxy(false);

    cp = new ContentPanel();
    cp.setBodyStyleName("pad-text");
    cp.setHeadingText("Constrain");
    cp.getHeader().setIcon(Resources.IMAGES.text());
    cp.add(new Label("Can only be dragged vertically."));
    cp.setWidth(200);
    con.add(cp,465, 10);

    d = new Draggable(cp, cp.getHeader());
    d.setConstrainHorizontal(true);
    return con;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
