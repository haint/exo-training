/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.draw;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.draw.DrawComponent;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.Slider;

@Detail(name = "Rotate Text", icon = "rotatetext", category = "Draw")
public class RotateTextExample implements IsWidget, EntryPoint {

  @Override
  public Widget asWidget() {

    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(20);

    Slider slider = new Slider();
    slider.setIncrement(1);
    slider.setMinValue(0);
    slider.setMaxValue(360);
    vp.add(slider);

    DrawComponent draw = new DrawComponent(400, 400);
    TextSprite text = new TextSprite("With GXT 3.0 Drawing");
    text.setFont("Arial");
    text.setFontSize(18);
    text.setRotation(45);
    text.setTranslation(20, 20);
    draw.addSprite(text);
    text = new TextSprite("Creating rotated text");
    text.setFont("Arial");
    text.setFontSize(18);
    text.setRotation(90);
    text.setTranslation(200, 20);
    draw.addSprite(text);
    final TextSprite rotate = new TextSprite("Is a snap!");
    rotate.setFont("Arial");
    rotate.setFontSize(18);
    rotate.setRotation(45);
    rotate.setTranslation(300, 100);
    draw.addSprite(rotate);
    vp.add(draw);

    slider.addValueChangeHandler(new ValueChangeHandler<Integer>() {

      @Override
      public void onValueChange(ValueChangeEvent<Integer> event) {
        rotate.setRotation(event.getValue());
        rotate.redraw();
      }
    });

    return vp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
