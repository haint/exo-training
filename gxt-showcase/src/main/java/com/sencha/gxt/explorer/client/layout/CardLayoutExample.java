/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.layout;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.examples.resources.client.ExampleStyles;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.FramedPanel.FramedPanelAppearance;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Detail(name = "CardLayout", icon = "cardlayout", category = "Layouts")
public class CardLayoutExample implements IsWidget, EntryPoint {
  public Widget asWidget() {

    ContentPanel panel = new ContentPanel(GWT.<ContentPanelAppearance> create(FramedPanelAppearance.class));
    panel.getElement().getStyle().setMargin(10d, Unit.PX);
    panel.setPixelSize(400, 100);
    panel.setHeadingText("CardLayout Example");

    final CardLayoutContainer layout = new CardLayoutContainer();

    for (int i = 0; i < 4; i++) {
      final Label l = new Label("This is the contents for card: " + (i + 1));
      l.addStyleName(ExampleStyles.get().text());
      
      layout.add(l);
      TextButton b = new TextButton("Card " + (i + 1));
      b.addSelectHandler(new SelectHandler() {

        @Override
        public void onSelect(SelectEvent event) {
          layout.setActiveWidget(l);
        }
      });

      panel.addButton(b);
    }
    panel.add(layout);
    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
