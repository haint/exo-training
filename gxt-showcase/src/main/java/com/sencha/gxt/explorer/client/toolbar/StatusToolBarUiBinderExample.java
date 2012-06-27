/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.toolbar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.theme.blue.client.status.BlueBoxStatusAppearance;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Status;
import com.sencha.gxt.widget.core.client.Status.StatusAppearance;
import com.sencha.gxt.widget.core.client.form.TextArea;

@Detail(name = "Status ToolBar (UiBinder)", icon = "statustoolbar", category = "ToolBar & Menu", files = "StatusToolBarUiBinderExample.ui.xml")
public class StatusToolBarUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Component, StatusToolBarUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  private DelayedTask task = new DelayedTask() {
    @Override
    public void onExecute() {
      status.clearStatus("Not writing");
    }
  };

  @UiField
  Status status;
  @UiField(provided = true)
  Status charCount = new Status(GWT.<StatusAppearance> create(BlueBoxStatusAppearance.class));
  @UiField(provided = true)
  Status wordCount = new Status(GWT.<StatusAppearance> create(BlueBoxStatusAppearance.class));

  public Widget asWidget() {
    return uiBinder.createAndBindUi(this);
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  @UiHandler("textArea")
  public void onKeyPress(KeyPressEvent event) {
    status.setBusy("writing...");
    TextArea t = (TextArea) event.getSource();
    String value = t.getCurrentValue();
    int length = value != null ? value.length() : 0;
    charCount.setText(length + (length == 1 ? " Char" : " Chars"));

    if (value != null) {
      int wc = getWordCount(value);
      wordCount.setText(wc + (wc == 1 ? " Word" : " Words"));
    }

    task.delay(1000);
  }

  public native int getWordCount(String v) /*-{
		if (v) {
			var wc = v.match(/\b/g);
			return wc ? wc.length / 2 : 0;
		}
		return 0;
  }-*/;
}
