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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;

public class HorizontalLayoutTest implements EntryPoint {

  @Override
  public void onModuleLoad() {

    HorizontalLayoutContainer c = new HorizontalLayoutContainer();
    c.setPixelSize(400, 400);
    c.setBorders(true);

    HTML label1 = new HTML("Test Label 1");
    label1.getElement().getStyle().setProperty("whiteSpace", "nowrap");
    label1.addStyleName(ThemeStyles.getStyle().border());
    label1.addStyleName("pad-text white-bg");

    Label label2 = new Label("Test Label 2");
    label2.addStyleName(ThemeStyles.getStyle().border());
    label2.addStyleName("pad-text white-bg");

    Label label3 = new Label("Test Label 3");
    label3.addStyleName(ThemeStyles.getStyle().border());
    label3.addStyleName("pad-text white-bg");

    c.add(label1, new HorizontalLayoutData(-1, 1, new Margins(4)));
    c.add(label2, new HorizontalLayoutData(1, 1, new Margins(4, 0, 4, 0)));
    c.add(label3, new HorizontalLayoutData(-1, 1, new Margins(4)));


    
    RootPanel.get().add(c);
    
  }

}
