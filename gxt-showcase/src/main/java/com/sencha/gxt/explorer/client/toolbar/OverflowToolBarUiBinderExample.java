/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.toolbar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;


@Detail(name = "Overflow ToolBar (UiBinder)", icon = "overflowtoolbar", category = "ToolBar & Menu", files = "OverflowToolBarUiBinderExample.ui.xml")
public class OverflowToolBarUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Widget, OverflowToolBarUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
  
  @UiField
  Window window;

  public Widget asWidget() {
    uiBinder.createAndBindUi(this);
    

    TextButton b = new TextButton("ToolBar Overflow UiBinder Example");
    b.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        window.show();
      }
    });

    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);
    vp.add(b);
    return vp;
  }

  public void onModuleLoad() {
      RootPanel.get().add(asWidget());
  }

}
