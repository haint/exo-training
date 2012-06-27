/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.dnd;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.examples.resources.client.ExampleStyles;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Detail(name = "Basic DND", icon = "basicdnd", category = "Drag and Drop")
public class BasicDndExample implements IsWidget, EntryPoint {

  @Override
  public Widget asWidget() {
	  FlowLayoutContainer con = new FlowLayoutContainer();

    HorizontalPanel hp = new HorizontalPanel();
    hp.setSpacing(10);

    final FlowLayoutContainer container = new FlowLayoutContainer();
    container.setBorders(true);
    container.setPixelSize(200, 200);

    DropTarget target = new DropTarget(container) {
      @Override
      protected void onDragDrop(DndDropEvent event) {
        super.onDragDrop(event);
        HTML html = (HTML) event.getData();
        container.add(html);
      }

    };
    target.setGroup("test");
    target.setOverStyle("drag-ok");

    final FlowLayoutContainer sourceContainer = new FlowLayoutContainer();
    sourceContainer.setWidth(100);

    addSources(sourceContainer);

    TextButton reset = new TextButton("Reset");
    reset.addSelectHandler(new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        container.clear();
        sourceContainer.clear();
        addSources(sourceContainer);
      }
    });

    hp.add(container);
    hp.add(sourceContainer);
    con.add(hp);
    con.add(reset, new MarginData(10));

    return con;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

  private void addSources(FlowLayoutContainer container) {
    for (int i = 0; i < 5; i++) {
      final SafeHtmlBuilder builder = new SafeHtmlBuilder();
      builder.appendHtmlConstant("<div style=\"border:1px solid #ddd;cursor:default\" class=\""
          + SafeHtmlUtils.htmlEscape(ExampleStyles.get().paddedText()) + "\">");
      builder.appendHtmlConstant("Drag Me " + i);
      builder.appendHtmlConstant("</div>");
      final HTML html = new HTML(builder.toSafeHtml());
      container.add(html, new MarginData(3));

      DragSource source = new DragSource(html) {
        @Override
        protected void onDragStart(DndDragStartEvent event) {
          super.onDragStart(event);
          // by default drag is allowed
          event.setData(html);
          event.getStatusProxy().update(builder.toSafeHtml());
        }

      };
      // group is optional
      source.setGroup("test");
    }
  }

}
