/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;

public class AccordionLayoutTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    ContentPanel panel = new ContentPanel();
    panel.setHeadingText("AccordionLayout");
    panel.setBodyBorder(false);

    AccordionLayoutContainer con = new AccordionLayoutContainer();
    // con.setFill(true);

    panel.add(con);
    panel.getHeader().setIcon(ExampleImages.INSTANCE.add16());

    AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);

    ContentPanel cp = new ContentPanel(appearance);
    cp.setAnimCollapse(false);
    cp.setHeadingText("Online Users");
    // cp.setLayout(new FitLayout());
    con.add(cp);
    con.setActiveWidget(cp);

    cp = new ContentPanel(appearance);
    cp.setAnimCollapse(false);
    cp.setBodyStyleName("pad-text");
    cp.setHeadingText("Settings");
    // cp.addText(TestData.DUMMY_TEXT_SHORT);
    con.add(cp);

    cp = new ContentPanel(appearance);
    cp.setAnimCollapse(false);
    cp.setBodyStyleName("pad-text");
    cp.setHeadingText("Stuff");
    // cp.addText(TestData.DUMMY_TEXT_SHORT);
    con.add(cp);

    cp = new ContentPanel(appearance);
    cp.setAnimCollapse(false);
    cp.setBodyStyleName("pad-text");
    cp.setHeadingText("More Stuff");
    // cp.addText(TestData.DUMMY_TEXT_SHORT);
    con.add(cp);

//     panel.setPixelSize(200, 325);
    con.setWidth(200);

    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);
    vp.add(panel);

    RootPanel.get().add(vp);
  }

}
