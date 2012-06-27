/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.binding;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.examples.resources.client.model.Stock;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ConverterEditorAdapter;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

@Detail(name = "Converter Example", icon = "converter", category = "Binding", files = "BasicBindingExample.html", classes = Stock.class)
public class ConverterExample implements IsWidget, EntryPoint, Editor<Stock> {
  interface Driver extends SimpleBeanEditorDriver<Stock, ConverterExample> {
  }

  /*
   * Simple Converter that allows the DateField and DateCell to display
   * dates/times in a timezone other than the user's own.
   * 
   * This is not meant to be a definitive way of doing this, as there are almost
   * certainly issues here with daylight savings time.
   */
  static class TimeZoneDateConverter implements Converter<Date, Date> {
    @SuppressWarnings("deprecation")
    private long getOffsetFromUtc() {
      return new Date().getTimezoneOffset() * 60 * 1000;
    }

    @Override
    public Date convertFieldValue(Date object) {
      if (object == null) {
        return null;
      }
      long localOffset = getOffsetFromUtc();
      long fakeUtc = object.getTime();
      Date localDate = new Date(fakeUtc + localOffset);
      return localDate;
    }

    @Override
    public Date convertModelValue(Date object) {
      long localOffset = getOffsetFromUtc();
      long utc = object.getTime();
      Date utcDate = new Date(utc - localOffset);
      return utcDate;
    }

  }

  @Ignore
  DateField lastTransField = new DateField();

  ConverterEditorAdapter<Date, Date, DateField> lastTrans = new ConverterEditorAdapter<Date, Date, DateField>(
      lastTransField, new TimeZoneDateConverter());

  @Override
  public Widget asWidget() {
    FlowLayoutContainer lc = new FlowLayoutContainer();
    lc.addStyleName("margin-10");

    lastTransField.setWidth(250);

    lc.add(new FieldLabel(lastTransField, "Date in UTC"));
    lastTransField.setPropertyEditor(new DateTimePropertyEditor(
        DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_LONG)));

    final Driver d = GWT.create(Driver.class);
    d.initialize(this);

    Stock stockWithLocalTime = new Stock("Wayne Enterprises", 93.55, 0.0, 0.0, new Date(), "Manufacturing");

    d.edit(stockWithLocalTime);

    TextButton display = new TextButton("Show", new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        Stock s = d.flush();
        if (d.hasErrors()) {
          new MessageBox("Please correct the errors before saving.").show();
          return;
        }
        Window.alert(s.getLastTrans() == null ? "No value" : s.getLastTrans().toString());
      }
    });
    lc.add(display);

    return lc;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

}
