/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.window;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Detail(name = "Hello World (UiBinder)", icon = "helloworld", category = "Windows", files = "HelloWindowUiBinderExample.ui.xml")
public class HelloWindowUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Widget, HelloWindowUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField
  Window window;

  public Widget asWidget() {

    uiBinder.createAndBindUi(this);

    TextButton btn = new TextButton("Hello World");
    btn.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        window.show();
      }
    });
    window.setData("open", btn);

    
    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);
    vp.add(btn);
    
    return vp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  @UiHandler("window")
  public void onHide(HideEvent event) {
    TextButton open = window.getData("open");
    open.focus();
  }

  @UiHandler("closeButton")
  public void onCloseButtonClicked(SelectEvent event) {
    window.hide();
  }
}