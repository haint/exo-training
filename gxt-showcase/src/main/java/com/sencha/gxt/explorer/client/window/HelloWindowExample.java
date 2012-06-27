/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.window;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Detail(name = "Hello World", icon = "helloworld", category = "Windows")
public class HelloWindowExample implements IsWidget {

  private VerticalPanel vp;

  @Override
  public Widget asWidget() {
    if (vp == null) {
      final Window window = new Window();
      window.setPixelSize(500, 300);
      window.setModal(true);
      window.setBlinkModal(true);
      window.setHeadingText("Hello Window");
      window.addHideHandler(new HideHandler() {
        @Override
        public void onHide(HideEvent event) {
          TextButton open = ((Window) event.getSource()).getData("open");
          open.focus();
        }
      });

      TabPanel panel = new TabPanel();
      panel.setBorders(false);

      Label label1 = new Label("Hello...");
      label1.addStyleName("pad-text");

      Label label2 = new Label("World...");
      label2.addStyleName("pad-text");

      panel.add(label1, new TabItemConfig("Hello World 1"));
      panel.add(label2, new TabItemConfig("Hello World 2"));

      window.add(panel);
      TextButton b = new TextButton("Close");
      b.addSelectHandler(new SelectHandler() {

        @Override
        public void onSelect(SelectEvent event) {
          window.hide();
        }
      });
      window.addButton(b);
      window.setFocusWidget(window.getButtonBar().getWidget(0));

      TextButton btn = new TextButton("Hello World");
      btn.addSelectHandler(new SelectHandler() {

        @Override
        public void onSelect(SelectEvent event) {
          window.show();
        }
      });
      window.setData("open", btn);

      vp = new VerticalPanel();
      vp.setSpacing(10);
      vp.add(btn);
    }
    return vp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
