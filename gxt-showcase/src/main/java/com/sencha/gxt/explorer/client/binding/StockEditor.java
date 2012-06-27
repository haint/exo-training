/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.binding;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.examples.resources.client.model.Stock;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class StockEditor implements IsWidget, Editor<Stock> {

  private FormPanel panel;
  private VerticalLayoutContainer container;

  TextField name;
  TextField symbol;

  private TextButton save;

  public StockEditor() {
    panel = new FormPanel();
    panel.setLabelWidth(50);

    container = new VerticalLayoutContainer();
    panel.setWidget(container);

    name = new TextField();
    symbol = new TextField();

    container.add(new FieldLabel(name, "Name"), new VerticalLayoutData(1, -1));
    container.add(new FieldLabel(symbol, "Symbol"), new VerticalLayoutData(1, -1));

    save = new TextButton("Save");
    save.setEnabled(false);
    container.add(save);

    panel.setLabelWidth(50);
  }

  @Override
  public Widget asWidget() {
    return panel;
  }
  
  public void setSaveEnabled(boolean enabled) {
    save.setEnabled(enabled);
    if (!enabled) {
      name.setValue("");
      symbol.setValue("");
    }
  }
  
  public HasSelectHandlers getSaveButton() {
    return save;
  }
}