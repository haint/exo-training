/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.misc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Detail(name = "Slider", icon = "slider", category = "Misc")
public class SliderExample implements IsWidget, EntryPoint {

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  @Override
  public Widget asWidget() {
    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);

    final Slider s = new Slider();
    vp.add(s);
    
    TextButton button = new TextButton("Set value to 40");
    button.addSelectHandler(new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        s.setValue(40);
      }
    });
    vp.add(button);
    
    final Slider vs = new Slider(true);
    vs.setIncrement(1);
    vp.add(vs);
    
    button = new TextButton("Set value to 40");
    button.addSelectHandler(new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        vs.setValue(40);
      }
    });
    vp.add(button);

    return vp;
  }

}
