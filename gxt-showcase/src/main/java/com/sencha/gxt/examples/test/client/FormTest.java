/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.BlurEvent.BlurHandler;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent.FocusHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;

public class FormTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    class Handler implements FocusHandler, BlurHandler {

      @Override
      public void onBlur(BlurEvent event) {
        System.out.println("blur");
      }

      @Override
      public void onFocus(FocusEvent event) {
        System.out.println("focus");
      }

    }

    TextField field = new TextField();
    field.setName("thename");
    field.setEmptyText("sdfsdf");
    field.addValidator(new EmptyValidator<String>());

    Handler h = new Handler();
    field.addFocusHandler(h);
    field.addBlurHandler(h);

    NumberField<Double> number = new NumberField<Double>(new DoublePropertyEditor());
    number.setEmptyText("sdfdsfsf");
    h = new Handler();
    number.addFocusHandler(h);
    number.addBlurHandler(h);

    DateField date = new DateField();
    h = new Handler();
    date.addFocusHandler(h);
    date.addBlurHandler(h);
    date.setEmptyText("empty texxxt");
    // field.setFieldLabel("Name");
    // field.markInvalid("error", null);

    TextArea area = new TextArea();
    area.setEmptyText("sdfdsfsdf");
    
    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);

    vp.add(field);
    vp.add(number);
    vp.add(date);
    vp.add(area);
    
    RootPanel.get().add(vp);
    
    date.redraw();
    field.redraw();
    number.redraw();
  }

}
