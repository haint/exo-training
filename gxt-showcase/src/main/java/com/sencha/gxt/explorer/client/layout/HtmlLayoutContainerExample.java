/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.layout;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

@Detail(name = "HtmlLayout", icon = "htmllayoutcontainer", category = "Layouts")
public class HtmlLayoutContainerExample implements IsWidget, EntryPoint {

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  public interface HtmlLayoutContainerTemplate extends XTemplates {
    @XTemplate("<table width=\"100%\" height=\"100%\"><tbody><tr><td height=\"100%\" class=\"cell1\" /><td>Some other cell</td><td class=\"cell2\" /></tr></tbody></table>")
    SafeHtml getTemplate();
  }

  @Override
  public Widget asWidget() {
    FramedPanel panel = new FramedPanel();
    panel.addStyleName("margin-10");
    panel.setHeadingText("HtmlLayoutContainer Example");
    panel.setPixelSize(400, 300);
    panel.setCollapsible(true);

    HtmlLayoutContainerTemplate templates = GWT.create(HtmlLayoutContainerTemplate.class);

    HtmlLayoutContainer c = new HtmlLayoutContainer(templates.getTemplate());
    panel.setWidget(c);

    TextButton button1 = new TextButton("Button Left Column");
    TextButton button2 = new TextButton("Button Right Column");

    c.add(button1, new HtmlData(".cell1"));
    c.add(button2, new HtmlData(".cell2"));

    return panel;
  }

}
