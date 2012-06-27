/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;

public class TabPanelTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final TabPanel panel = new TabPanel();
    panel.setTabScroll(true);
    panel.setPixelSize(400, 400);
    panel.setResizeTabs(true);

    for (int i = 0; i < 10; i++) {
      panel.add(new Label("Label " + i), new TabItemConfig("Tab " + i));

    }

    TabItemConfig config = new TabItemConfig("Icon Tab");
    config.setIcon(ExampleImages.INSTANCE.add16());
    config.setClosable(true);
    panel.add(new Label("2"), config);

    panel.setActiveWidget(panel.getWidget(1));

    RootPanel.get().add(panel);

    // panel = new TabPanel(TabPosition.BOTTOM);
    //
    // panel.setPixelSize(400, 400);
    //
    // item = new TabItem("foo");
    // item.add(new Label("1"));
    // panel.add(item);
    //
    // item = new TabItem("bar");
    // item.add(new Label("2"));
    // panel.add(item);
    // RootPanel.get().add(panel);
  }

}
