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
import com.sencha.gxt.widget.core.client.form.FileUploadField;

public class FileUploadFieldTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    FileUploadField field = new FileUploadField();
    field.setWidth(250);
    
    
    RootPanel.get().add(field);
  }

}
