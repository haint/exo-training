/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.layout;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;

@Detail(name = "HBoxLayout", icon = "hboxlayout", category = "Layouts")
public class HBoxLayoutExample implements IsWidget, EntryPoint {

  private String button1Text = "Button 1";

  private String button2Text = "Button 2";
  private String button3Text = "Button 3";
  private String button4Text = "Button 4";
  private ContentPanel lccenter;
  private ToggleGroup toggleGroup = new ToggleGroup();

  @Override
  public Widget asWidget() {
    ScrollPanel con = new ScrollPanel();
    con.getElement().getStyle().setMargin(10, Unit.PX);

    ContentPanel panel = new ContentPanel();
    panel.setHeadingText("HorizontalBox Example");
    panel.setPixelSize(600, 500);

    BorderLayoutContainer border = new BorderLayoutContainer();
    panel.setWidget(border);

    VBoxLayoutContainer lcwest = new VBoxLayoutContainer();
    lcwest.setPadding(new Padding(5));
    lcwest.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);

    BorderLayoutData west = new BorderLayoutData(150);
    west.setMargins(new Margins(5));
    // west.setSplit(true);

    border.setWestWidget(lcwest, west);

    lccenter = new ContentPanel();
    lccenter.setHeaderVisible(false);
    lccenter.add(new HTML(
        "<p style=\"padding:10px;color:#556677;font-size:11px;\">Select a configuration on the left</p>"));

    MarginData center = new MarginData(new Margins(5));

    border.setCenterWidget(lccenter, center);

    BoxLayoutData vBoxData = new BoxLayoutData(new Margins(5, 5, 5, 5));
    vBoxData.setFlex(1);

    lcwest.add(createToggleButton("Spaced", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

          c.add(new TextButton(button1Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
          flex.setFlex(1);
          c.add(new Label(), flex);
          c.add(new TextButton(button2Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button3Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button4Text), new BoxLayoutData(new Margins(0)));

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Multi-Spaced", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

          c.add(new TextButton(button1Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
          flex.setFlex(1);
          c.add(new Label(), flex);
          c.add(new TextButton(button2Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new Label(), flex);
          c.add(new TextButton(button3Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new Label(), flex);
          c.add(new TextButton(button4Text), new BoxLayoutData(new Margins(0)));

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Align: top", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

          c.add(new TextButton(button1Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button2Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button3Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button4Text), new BoxLayoutData(new Margins(0)));

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Align: middle", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);

          c.add(new TextButton(button1Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button2Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button3Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button4Text), new BoxLayoutData(new Margins(0)));

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Align: bottom", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.BOTTOM);

          c.add(new TextButton(button1Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button2Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button3Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button4Text), new BoxLayoutData(new Margins(0)));

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Align: stretch", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));

          c.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCH);

          c.add(new TextButton(button1Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button2Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button3Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button4Text), new BoxLayoutData(new Margins(0)));

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Align: stretchmax", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);

          c.add(new TextButton(button1Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button2Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button3Text), new BoxLayoutData(new Margins(0, 5, 0, 0)));
          c.add(new TextButton(button4Text), new BoxLayoutData(new Margins(0)));

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Flex: All even", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));

          c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);

          BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
          flex.setFlex(1);
          c.add(new TextButton(button1Text), flex);
          c.add(new TextButton(button2Text), flex);
          c.add(new TextButton(button3Text), flex);

          BoxLayoutData flex2 = new BoxLayoutData(new Margins(0));
          flex2.setFlex(1);
          c.add(new TextButton(button4Text), flex2);

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Flex: ratio", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);

          BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
          flex.setFlex(1);
          c.add(new TextButton(button1Text), flex);
          c.add(new TextButton(button2Text), flex);
          c.add(new TextButton(button3Text), flex);

          BoxLayoutData flex2 = new BoxLayoutData(new Margins(0));
          flex2.setFlex(3);
          c.add(new TextButton(button4Text), flex2);

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Flex + Stretch", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCH);

          BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
          flex.setFlex(1);
          c.add(new TextButton(button1Text), flex);
          c.add(new TextButton(button2Text), flex);
          c.add(new TextButton(button3Text), flex);

          BoxLayoutData flex2 = new BoxLayoutData(new Margins(0));
          flex2.setFlex(3);
          c.add(new TextButton(button4Text), flex2);

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Pack: start", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
          c.setPack(BoxLayoutPack.START);

          BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 5, 0, 0));
          c.add(new TextButton(button1Text), layoutData);
          c.add(new TextButton(button2Text), layoutData);
          c.add(new TextButton(button3Text), layoutData);

          BoxLayoutData layoutData2 = new BoxLayoutData(new Margins(0));
          c.add(new TextButton(button4Text), layoutData2);

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Pack: center", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
          c.setPack(BoxLayoutPack.CENTER);

          BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 5, 0, 0));
          c.add(new TextButton(button1Text), layoutData);
          c.add(new TextButton(button2Text), layoutData);
          c.add(new TextButton(button3Text), layoutData);

          BoxLayoutData layoutData2 = new BoxLayoutData(new Margins(0));
          c.add(new TextButton(button4Text), layoutData2);

          addToCenter(c);
        }
      }
    }), vBoxData);

    lcwest.add(createToggleButton("Pack: end", new ValueChangeHandler<Boolean>() {

      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
          HBoxLayoutContainer c = new HBoxLayoutContainer();
          c.setPadding(new Padding(5));
          c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
          c.setPack(BoxLayoutPack.END);

          BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 5, 0, 0));
          c.add(new TextButton(button1Text), layoutData);
          c.add(new TextButton(button2Text), layoutData);
          c.add(new TextButton(button3Text), layoutData);

          BoxLayoutData layoutData2 = new BoxLayoutData(new Margins(0));
          c.add(new TextButton(button4Text), layoutData2);

          addToCenter(c);
        }
      }
    }), vBoxData);

    con.add(panel);
    return con;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  private void addToCenter(Widget c) {
    lccenter.clear();
    lccenter.add(c);
    lccenter.forceLayout();
  }

  private ToggleButton createToggleButton(String name, ValueChangeHandler<Boolean> valueChangeHandler) {
    ToggleButton button = new ToggleButton(name);
    toggleGroup.add(button);
    button.addValueChangeHandler(valueChangeHandler);
    button.setAllowDepress(false);
    return button;
  }
}
