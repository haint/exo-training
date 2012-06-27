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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

@Detail(name = "CardLayout (UiBinder)", icon = "cardlayout", category = "Layouts", files={"CardLayoutUiBinderExample.ui.xml"})
public class CardLayoutUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Widget, CardLayoutUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
  
  @UiField CardLayoutContainer layout;
  @UiField FramedPanel panel;

  public Widget asWidget() {
    return uiBinder.createAndBindUi(this);
  }

  public void onModuleLoad() {
      RootPanel.get().add(asWidget());
  }

  @UiHandler({"card1Button", "card2Button", "card3Button", "card4Button"})
  public void onButton1Click(SelectEvent event) {
    TextButton button = (TextButton) event.getSource();
    int index = panel.getButtonBar().getWidgetIndex(button);
    layout.setActiveWidget(layout.getWidget(index));
  }
}
