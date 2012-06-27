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
import com.sencha.gxt.widget.core.client.form.HtmlEditor;

public class HtmlEditorTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    HtmlEditor editor = new HtmlEditor();
    editor.setPixelSize(650, 300);
    
    editor.getElement().setMargins(10);
    RootPanel.get().add(editor);
  }

}
