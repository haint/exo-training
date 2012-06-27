/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class ScrollSupporTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final FlowLayoutContainer con = new FlowLayoutContainer();
    con.setPixelSize(400, 200);
    con.setBorders(true);
    con.getScrollSupport().setScrollMode(ScrollMode.ALWAYS);
    
    con.addScrollHandler(new ScrollHandler() {
      
      @Override
      public void onScroll(ScrollEvent event) {
        System.out.println(con.getScrollSupport().getVerticalScrollPosition());
      }
    });
    for (int i = 0; i < 20; i++) {
      con.add(new HTML("Widget " + i));
    }
    
    RootPanel.get().add(con);
    
    RootPanel.get().add(new TextButton("scroll bottm", new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        con.getScrollSupport().scrollToBottom();
      }
    }));
    RootPanel.get().add(new TextButton("scroll into view", new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        con.getScrollSupport().ensureVisible(con.getWidget(18));
      }
    }));
  }

}
