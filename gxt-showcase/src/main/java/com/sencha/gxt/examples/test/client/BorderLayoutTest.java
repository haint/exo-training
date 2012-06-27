/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;

public class BorderLayoutTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    BorderLayoutContainer con = new BorderLayoutContainer();
    con.setBorders(true);

    ContentPanel west = new ContentPanel();
    west.setHeadingText("West");

    BorderLayoutData westData = new BorderLayoutData(200);
    westData.setSplit(true);
    westData.setCollapsible(true);
    westData.setCollapseMini(true);
    westData.setCollapseHidden(true);
    westData.setMargins(new Margins(5));

    con.setWestWidget(west, westData);
    
    ContentPanel east = new ContentPanel();
    east.setHeadingText("East");

    BorderLayoutData eastData = new BorderLayoutData(200);
    eastData.setSplit(true);
    eastData.setCollapsible(true);
    eastData.setCollapseMini(true);
    eastData.setMargins(new Margins(5));

    con.setEastWidget(east, eastData);
    
    ContentPanel north = new ContentPanel();
    north.setHeadingText("North");
    
    BorderLayoutData northData = new BorderLayoutData(100);
    northData.setCollapsible(true);
    northData.setCollapseMini(true);
    northData.setSplit(true);
    northData.setCollapseHidden(true);
    northData.setMargins(new Margins(5, 5, 0, 5));
    
    con.setNorthWidget(north, northData);
    
    
    ContentPanel south = new ContentPanel();
    south.setHeadingText("South");
    
    BorderLayoutData southData = new BorderLayoutData(100);
    southData.setCollapsible(true);
    southData.setSplit(true);
    southData.setCollapseMini(true);
    southData.setMargins(new Margins(0, 5, 5, 5));
    
    con.setSouthWidget(south, southData);
    

    ContentPanel center = new ContentPanel();
    center.setHeadingText("Center");

    MarginData centerData = new MarginData();
    centerData.setMargins(new Margins(5, 0, 5, 0));

    con.setCenterWidget(center, centerData);

    con.setPixelSize(800, 400);
    RootPanel.get().add(con);
  }

}
