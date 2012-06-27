/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.utility;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;

public class PromptImpl extends Prompt {

  /**
   * Use a GWT.create() here to make it simple to hijack the default
   * implementation.
   */
  public static final Prompt INSTANCE = GWT.create(PromptImpl.class);

  @Override
  public void alert(String title, String text) {
    alert(title, text, null);
  }

  @Override
  public void alert(String title, String text, final Runnable runnable) {
    AlertMessageBox alertMessageBox = new AlertMessageBox(title, text);
    alertMessageBox.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        if (runnable != null) {
          runnable.run();
        }
      }
    });
    alertMessageBox.setWidth(300);
    alertMessageBox.show();
  }

  @Override
  public void confirm(String title, String text, final Runnable yesRunnable) {
    confirm(title, text, yesRunnable, null);
  }

  @Override
  public void confirm(String title, String text, final Runnable yesRunnable, final Runnable noRunnable) {
    ConfirmMessageBox confirmMessageBox = new ConfirmMessageBox(title, text);
    confirmMessageBox.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        Dialog dialog = (Dialog) event.getSource();
        boolean isYes = dialog.getHideButton() == dialog.getButtonById(PredefinedButton.YES.name());
        if (isYes) {
          if (yesRunnable != null) {
            yesRunnable.run();
          }
        } else {
          if (noRunnable != null) {
            noRunnable.run();
          }
        }
      }
    });
    confirmMessageBox.setWidth(300);
    confirmMessageBox.show();
  }

}
