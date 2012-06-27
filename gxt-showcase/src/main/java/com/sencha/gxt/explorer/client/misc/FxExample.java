/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.misc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.Direction;
import com.sencha.gxt.core.client.util.Rectangle;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.fx.client.FxElement;
import com.sencha.gxt.fx.client.animation.AfterAnimateEvent;
import com.sencha.gxt.fx.client.animation.AfterAnimateEvent.AfterAnimateHandler;
import com.sencha.gxt.fx.client.animation.Fx;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
@Detail(name = "Fx", icon = "fx", category = "Misc", fit=true)
public class FxExample implements IsWidget, EntryPoint {

  private ContentPanel cp;

  public Widget asWidget() {
    FlowLayoutContainer con = new FlowLayoutContainer();

    TextButton slide = new TextButton("Slide In / Out");
    slide.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        if (cp.isVisible()) {
          cp.getElement().<FxElement>cast().slideOut(Direction.UP);
        } else {
          cp.getElement().<FxElement>cast().slideIn(Direction.DOWN);
        }
      }
    });

    TextButton fade = new TextButton("Fade In / Out");
    fade.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        cp.getElement().<FxElement>cast().fadeToggle();
      }
    });

    TextButton move = new TextButton("Move");
    move.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        Rectangle bounds = cp.getElement().getBounds();
        cp.getElement().<FxElement>cast().setXY(bounds.getX() + 50, bounds.getY() + 50, new Fx());
      }
    });

    TextButton blink = new TextButton("Blink");
    blink.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        cp.getElement().<FxElement>cast().blink();
      }
    });

    TextButton event = new TextButton("Event");
    event.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        Fx fx = new Fx();
        fx.addAfterAnimateHandler(new AfterAnimateHandler() {
          @Override
          public void onAfterAnimate(AfterAnimateEvent event) {
            if (cp.isCollapsed()) {
              cp.expand();
            } else {
              cp.collapse();
            }
          }
        });
        cp.getElement().<FxElement>cast().blink(fx, 50);
      }
    });

    TextButton reset = new TextButton("Reset");
    reset.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        cp.setPosition(100, 100);
      }
    });

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.add(slide);
    buttonBar.add(fade);
    buttonBar.add(move);
    buttonBar.add(blink);
    buttonBar.add(event);
    buttonBar.add(reset);

    cp = new ContentPanel();
    cp.setCollapsible(true);
    cp.setAnimCollapse(true);
    cp.setHeadingText("FX Demo");
    // cp.setIcon(Resources.ICONS.text());
    // cp.setBodyStyleName("pad-text");
    // cp.addText(TestData.DUMMY_TEXT_SHORT);
    cp.setPixelSize(200, 200);

    con.add(buttonBar, new MarginData(10));
    con.add(cp);
    // cp.setStyleAttribute("position", "relative");
    cp.setPosition(100, 100);
    return con;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}