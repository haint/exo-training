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
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;

public class AdapterFieldTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final HTML html = new HTML("adapt me");
    
    AdapterField<String> adapter = new AdapterField<String>(html) {
  
      @Override
      public void setValue(String value) {
        html.setHTML(value);
      }
      
      @Override
      public String getValue() {
        return html.getHTML();
      }
    };
    adapter.setWidth(100);
    adapter.addValidator(new EmptyValidator<String>());
    adapter.addValidator(new MinLengthValidator(20));
    
    RootPanel.get().add(adapter);
    System.out.println(adapter.isValid());
  }

}
