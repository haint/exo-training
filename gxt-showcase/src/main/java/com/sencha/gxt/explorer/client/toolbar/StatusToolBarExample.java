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
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.theme.blue.client.status.BlueBoxStatusAppearance;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Status;
import com.sencha.gxt.widget.core.client.Status.StatusAppearance;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

@Detail(name = "Status ToolBar", icon = "statustoolbar", category = "ToolBar & Menu")
public class StatusToolBarExample implements IsWidget, EntryPoint {

  private DelayedTask task = new DelayedTask() {
    @Override
    public void onExecute() {
      status.clearStatus("Not writing");
    }
  };

  private Status charCount;
  private Status wordCount;
  private Status status;

  public Widget asWidget() {
    ToolBar toolBar = new ToolBar();
    toolBar.addStyleName(ThemeStyles.getStyle().borderTop());

    status = new Status();
    status.setText("Not writing");
    status.setWidth(150);
    toolBar.add(status);
    toolBar.add(new FillToolItem());

    charCount = new Status(GWT.<StatusAppearance> create(BlueBoxStatusAppearance.class));
    charCount.setWidth(100);
    charCount.setText("0 Chars");
    toolBar.add(charCount);

    toolBar.add(new LabelToolItem("&nbsp;"));

    wordCount = new Status(GWT.<StatusAppearance> create(BlueBoxStatusAppearance.class));
    wordCount.setWidth(100);
    wordCount.setText("0 Words");
    toolBar.add(wordCount);

    ContentPanel panel = new ContentPanel();
    panel.setHeadingText("Status Toolbar");
    panel.setPixelSize(450, 300);
    panel.addStyleName("margin-10");

    VerticalLayoutContainer form = new VerticalLayoutContainer();
    panel.add(form);

    TextArea textArea = new TextArea();
    textArea.addKeyPressHandler(new KeyPressHandler() {

      @Override
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

    });

    VerticalLayoutData data = new VerticalLayoutData(1, 1);
    data.setMargins(new Margins(5));
    textArea.setLayoutData(data);

    Widget w = textArea;
    // TODO investigate IE bug where text area is floating out of view
    if (GXT.isIE6()) {
      SimpleContainer s = new SimpleContainer();
      s.setWidget(textArea);
      w = s;
    }

    form.add(w, new VerticalLayoutData(1, 1, new Margins(5)));

    toolBar.setLayoutData(new VerticalLayoutData(1, -1));
    form.add(toolBar);

    return panel;
  }

  public native int getWordCount(String v) /*-{
		if (v) {
			var wc = v.match(/\b/g);
			return wc ? wc.length / 2 : 0;
		}
		return 0;
  }-*/;

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
