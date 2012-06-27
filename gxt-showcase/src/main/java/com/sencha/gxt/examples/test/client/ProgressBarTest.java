/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ProgressBar;
import com.sencha.gxt.widget.core.client.info.Info;

public class ProgressBarTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final ProgressBar bar = new ProgressBar();
    bar.setWidth(400);

    final Timer t = new Timer() {
      float i;

      @Override
      public void run() {
        bar.updateProgress(i / 100, (int) i + "% Complete");
        i += 5;
        if (i > 105) {
          cancel();
          Info.display("Message", "Items were loaded");
        }
      }
    };
    t.scheduleRepeating(500);

    RootPanel.get().add(bar);

  }

}
