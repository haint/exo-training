/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.tabs;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.examples.resources.client.Resources;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.info.Info;

@Detail(name = "Basic Tabs", category = "Tabs", icon = "basictabs")
public class BasicTabExample implements IsWidget, EntryPoint {
  public Widget asWidget() {
    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);

    String txt = TestData.DUMMY_TEXT_SHORT;

    SelectionHandler<Widget> handler = new SelectionHandler<Widget>() {
      @Override
      public void onSelection(SelectionEvent<Widget> event) {
        TabPanel panel = (TabPanel) event.getSource();
        Widget w = event.getSelectedItem();
        TabItemConfig config = panel.getConfig(w);
        Info.display("Message", "'" + config.getText() + "' Selected");
      }
    };

    TabPanel folder = new TabPanel();
    folder.addSelectionHandler(handler);
    folder.setWidth(450);

    HTML shortText = new HTML(txt);
    shortText.addStyleName("pad-text");
    folder.add(shortText, "Short Text");

    HTML longText = new HTML(txt + "<br><br>" + txt);
    longText.addStyleName("pad-text");
    folder.add(longText, "Long Text");

    final PlainTabPanel panel = new PlainTabPanel();
    panel.setPixelSize(450, 250);
    panel.addSelectionHandler(handler);

    Label normal = new Label("Just a plain old tab");
    normal.addStyleName("pad-text");
    panel.add(normal, "Normal");

    Label iconTab = new Label("Just a plain old tab with an icon");
    iconTab.addStyleName("pad-text");

    TabItemConfig config = new TabItemConfig("Icon Tab");
    config.setIcon(Resources.IMAGES.table());
    panel.add(iconTab, config);

    Label disabled = new Label("This tab should be disabled");
    disabled.addStyleName("pad-text");

    config = new TabItemConfig("Disabled");
    config.setEnabled(false);
    panel.add(disabled, config);

    vp.add(folder);
    vp.add(panel);
    return vp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
