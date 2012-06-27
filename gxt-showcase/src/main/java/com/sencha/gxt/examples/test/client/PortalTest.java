/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class PortalTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    PortalLayoutContainer portal = new PortalLayoutContainer(3);
    portal.setBorders(true);
    portal.getElement().getStyle().setBackgroundColor("white");
    portal.setColumnWidth(0, .40);
    portal.setColumnWidth(1, .30);
    portal.setColumnWidth(2, .30);

    portal.setPixelSize(900, 400);
    portal.setBorders(true);

    Portlet portlet = new Portlet();
    portlet.setHeadingText("Grid in a Portlet");
    configPanel(portlet);
    // portlet.add(createGrid());
    portlet.setHeight(250);
    portal.add(portlet, 0);
    
    portlet = new Portlet();
    portlet.setHeadingText("Another Panel 1");
    configPanel(portlet);
    portlet.add(getBogusText());
    portal.add(portlet, 0);
    
    portlet = new Portlet();
    portlet.setHeadingText("Panel 2");
    configPanel(portlet);
    portlet.add(getBogusText());
    portal.add(portlet, 1);
    
    portlet = new Portlet();
    portlet.setHeadingText("Another Panel 2");
    configPanel(portlet);
    portlet.add(getBogusText());
    portal.add(portlet, 1);
    
    RootPanel.get().add(portal);
  }

  private void configPanel(final Portlet panel) {
    panel.setCollapsible(true);
    panel.setAnimCollapse(false);
    panel.getHeader().addTool(new ToolButton(ToolButton.GEAR));
    panel.getHeader().addTool(new ToolButton(ToolButton.CLOSE, new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        panel.removeFromParent();
      }
    }));
  }
  
  private HTML getBogusText() {
    return new HTML("<div class='pad-text'>" + TestData.DUMMY_TEXT_SHORT + "</div>");
  }

}
