/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.button;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

@Detail(name = "Button Aligning (UiBinder)", icon = "buttonaligning", category = "Button", files = "ButtonAlignUiBinderExample.ui.xml")
public class ButtonAlignUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Widget, ButtonAlignUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  public Widget asWidget() {
    Widget w = uiBinder.createAndBindUi(this);
    w.addStyleName("margin-10");
    return w;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  @UiHandler({"button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8", "button9"})
  public void onButtonClick(SelectEvent event) {
    Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");

  }
}
