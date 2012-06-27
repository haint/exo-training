/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.explorer.client.app.ui.ExampleDetailView;
import com.sencha.gxt.explorer.client.app.ui.ExampleListView;
import com.sencha.gxt.state.client.BorderLayoutStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class ExplorerShell extends BorderLayoutContainer {

  public ExplorerShell() {
    monitorWindowResize = true;
    Window.enableScrolling(false);
    setPixelSize(Window.getClientWidth(), Window.getClientHeight());
    
    setStateful(true);
    setStateId("explorerLayout");
    
    BorderLayoutStateHandler state = new BorderLayoutStateHandler(this);
    state.loadState();
  }

  private ContentPanel west;
  private SimpleContainer center;

  @Inject
  public ExplorerShell(ExampleListView listView, ExampleDetailView detailView) {
    this();
    HTML north = new HTML();
    north.setHTML("<div id='demo-theme'></div><div id=demo-title>Sencha GXT Explorer Demo</div>");
    north.getElement().setId("demo-header");

    BorderLayoutData northData = new BorderLayoutData(35);
    setNorthWidget(north, northData);

    BorderLayoutData westData = new BorderLayoutData(200);
    westData.setMargins(new Margins(5, 0, 5, 5));
    westData.setSplit(true);
    westData.setCollapsible(true);
    westData.setCollapseHidden(true);
    westData.setCollapseMini(true);

    west = new ContentPanel();
    west.setHeadingText("Navigation");
    west.setBodyBorder(true);
    west.add(listView.asWidget());

    MarginData centerData = new MarginData();
    centerData.setMargins(new Margins(5));

    center = new SimpleContainer();
    center.add(detailView.asWidget());

    setWestWidget(west, westData);
    setCenterWidget(center, centerData);
  }

  @Override
  protected void onWindowResize(int width, int height) {
    setPixelSize(width, height);
  }

}
