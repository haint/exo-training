/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.layout;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Detail(name = "BorderLayout", icon = "borderlayout", category = "Layouts", fit = true)
public class BorderLayoutExample implements IsWidget, EntryPoint {
  public Widget asWidget() {

    final BorderLayoutContainer con = new BorderLayoutContainer();
    con.setBorders(true);

    ContentPanel north = new ContentPanel();
    ContentPanel west = new ContentPanel();
    ContentPanel center = new ContentPanel();
    center.setHeadingText("BorderLayout Example");

    FlexTable table = new FlexTable();
    table.getElement().getStyle().setProperty("margin", "10px");
    table.setCellSpacing(8);
    table.setCellPadding(4);

    for (int i = 0; i < LayoutRegion.values().length; i++) {
      final LayoutRegion r = LayoutRegion.values()[i];
      if (r == LayoutRegion.CENTER) {
        continue;
      }

      SelectHandler handler = new SelectHandler() {

        @Override
        public void onSelect(SelectEvent event) {
          TextButton btn = (TextButton)event.getSource();
          String txt = btn.getText();
          if (txt.equals("Expand")) {
            con.expand(r);
          } else if (txt.equals("Collapse")) {
            con.collapse(r);
          } else if (txt.equals("Show")) {
            con.show(r);
          } else {
            con.hide(r);
          }
        }
      };

      table.setHTML(i, 0, "<div style='font-size: 12px; width: 100px'>" + r.name() + ":</span>");
      table.setWidget(i, 1, new TextButton("Expand", handler));
      table.setWidget(i, 2, new TextButton("Collapse", handler));
      table.setWidget(i, 3, new TextButton("Show", handler));
      table.setWidget(i, 4, new TextButton("Hide", handler));
    }

    center.setResize(false);
    center.add(table);

    ContentPanel east = new ContentPanel();
    ContentPanel south = new ContentPanel();

    BorderLayoutData northData = new BorderLayoutData(100);
    northData.setMargins(new Margins(5));
    northData.setCollapsible(true);
    northData.setSplit(true);

    BorderLayoutData westData = new BorderLayoutData(150);
    westData.setCollapsible(true);
    westData.setSplit(true);
    westData.setCollapseMini(true);
    westData.setMargins(new Margins(0, 5, 0, 5));

    MarginData centerData = new MarginData();

    BorderLayoutData eastData = new BorderLayoutData(150);
    eastData.setMargins(new Margins(0, 5, 0, 5));
    eastData.setCollapsible(true);
    eastData.setSplit(true);

    BorderLayoutData southData = new BorderLayoutData(100);
    southData.setMargins(new Margins(5));
    southData.setCollapsible(true);
    southData.setCollapseMini(true);

    con.setNorthWidget(north, northData);
    con.setWestWidget(west, westData);
    con.setCenterWidget(center, centerData);
    con.setEastWidget(east, eastData);
    con.setSouthWidget(south, southData);

    SimpleContainer simple = new SimpleContainer();
    simple.add(con, new MarginData(10));

    return simple;
  }

  public void onModuleLoad() {
    Widget con = asWidget();
    Viewport viewport = new Viewport();
    viewport.add(con);
    RootPanel.get().add(viewport);
  }

}
