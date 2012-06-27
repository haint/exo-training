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
import com.sencha.gxt.core.client.dom.Layer;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class LayerTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    TextButton btn = new TextButton("sdfsdfsdf");

    new Layer(btn.getElement());

    btn.getElement().setSize(100, 100);

    RootPanel.get().add(btn);
  }

}
