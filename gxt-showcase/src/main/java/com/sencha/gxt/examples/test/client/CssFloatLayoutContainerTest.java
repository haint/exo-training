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
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;

public class CssFloatLayoutContainerTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    CssFloatLayoutContainer con = new CssFloatLayoutContainer();
    con.setBorders(true);
    con.setPixelSize(400, 400);

    HTML html = new HTML("one");
    html.addStyleName(ThemeStyles.getStyle().border());
    con.add(html, new CssFloatData(.5));

    html = new HTML("two");
    html.addStyleName(ThemeStyles.getStyle().border());
    con.add(html, new CssFloatData(.5));

    RootPanel.get().add(con);

  }

}
