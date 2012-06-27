/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.sencha.gxt.widget.core.client.Window;


public class WindowTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    Window window = new Window();
    window.setHeadingText("Test");
    window.show();
  }

}
