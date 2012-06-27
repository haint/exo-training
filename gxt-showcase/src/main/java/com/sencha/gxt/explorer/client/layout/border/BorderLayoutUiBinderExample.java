/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.layout.border;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Detail(name = "BorderLayout (UiBinder)", icon = "borderlayout", category = "Layouts", fit = true, files = {"BorderLayoutUiBinderExample.ui.xml"})
public class BorderLayoutUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Component, BorderLayoutUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField(provided = true)
  MarginData outerData = new MarginData(20);
  @UiField(provided = true)
  BorderLayoutData northData = new BorderLayoutData(100);
  @UiField(provided = true)
  BorderLayoutData westData = new BorderLayoutData(150);
  @UiField(provided = true)
  MarginData centerData = new MarginData();
  @UiField(provided = true)
  BorderLayoutData eastData = new BorderLayoutData(150);
  @UiField(provided = true)
  BorderLayoutData southData = new BorderLayoutData(100);

  @UiField
  BorderLayoutContainer con;
  @UiField
  FlexTable table;

  public Widget asWidget() {

    northData.setMargins(new Margins(5));
    westData.setMargins(new Margins(0, 5, 0, 5));
    westData.setCollapsible(true);
    westData.setSplit(true);
    eastData.setMargins(new Margins(0, 5, 0, 5));
    southData.setMargins(new Margins(5));

    Widget widget = uiBinder.createAndBindUi(this);

    for (int i = 0; i < LayoutRegion.values().length; i++) {
      final LayoutRegion r = LayoutRegion.values()[i];
      if (r == LayoutRegion.CENTER) {
        continue;
      }

      SelectHandler handler = new SelectHandler() {

        @Override
        public void onSelect(SelectEvent event) {
          TextButton btn = (TextButton) event.getSource();
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

    return widget;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
