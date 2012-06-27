/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.examples.resources.client.model.Kid;
import com.sencha.gxt.examples.resources.client.model.Person;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

@Detail(name = "Templates", category = "Templates & Lists", icon = "templates", files = "template.html")
public class TemplateExample implements IsWidget, EntryPoint {

  public interface DataRenderer extends XTemplates {
    @XTemplate("<p>Name: {data.name}</p><p>Company: {data.company}</p><p>Location: {data.location}</p>")
    public SafeHtml render(Person data);

    @XTemplate(source = "template.html")
    public SafeHtml renderTemplate(Person data);

  }

  DataRenderer renderer = GWT.create(DataRenderer.class);

  public Widget asWidget() {
    final Person person = new Person("Darrell Meyer", "Sencha Inc", "GXT", "Washington, DC", 1000000000d);

    List<Kid> kids = new ArrayList<Kid>();
    kids.add(new Kid("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    kids.add(new Kid("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    kids.add(new Kid("Andrew", 1, new DateWrapper(2007, 1, 1).asDate()));

    person.setKids(kids);

    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);

    ContentPanel panel = new ContentPanel();
    final VerticalLayoutContainer rowLayoutContainer = new VerticalLayoutContainer();
    panel.add(rowLayoutContainer);
    panel.setHeadingText("Basic Template");
    panel.setWidth(300);

    ToolBar toolbar = new ToolBar();
    TextButton apply = new TextButton("Apply Template");
    apply.addSelectHandler(new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        rowLayoutContainer.remove(1);
        final HTML text = new HTML(renderer.render(person));
        text.addStyleName("pad-text");
        text.setLayoutData(new VerticalLayoutData(1, -1));
        rowLayoutContainer.add(text);
        rowLayoutContainer.forceLayout();
      }
    });

    toolbar.add(apply);

    toolbar.setLayoutData(new VerticalLayoutData(1, -1));
    rowLayoutContainer.add(toolbar);
    rowLayoutContainer.add(new HTML());

    ContentPanel xpanel = new ContentPanel();
    final VerticalLayoutContainer xRowLayoutContainer = new VerticalLayoutContainer();
    xpanel.add(xRowLayoutContainer);
    xpanel.setHeadingText("Advanced Template");
    xpanel.setWidth(300);

    toolbar = new ToolBar();

    apply = new TextButton("Apply Template");
    apply.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        xRowLayoutContainer.remove(1);
        final HTML text = new HTML(renderer.renderTemplate(person));
        text.addStyleName("pad-text");
        text.setLayoutData(new VerticalLayoutData(1, -1));
        xRowLayoutContainer.add(text);
        xRowLayoutContainer.forceLayout();
      }
    });

    toolbar.add(apply);
    toolbar.setLayoutData(new VerticalLayoutData(1, -1));

    xRowLayoutContainer.add(toolbar);
    xRowLayoutContainer.add(new HTML());

    vp.add(panel);
    vp.add(xpanel);
    return vp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
