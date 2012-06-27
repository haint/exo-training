/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.misc;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.DatePicker;
import com.sencha.gxt.widget.core.client.info.Info;

@Detail(name = "DatePicker", icon = "datepicker", category = "Misc")
public class DatePickerExample implements IsWidget, EntryPoint {

  @Override
  public Widget asWidget() {
    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);

    DatePicker picker = new DatePicker();
    picker.addValueChangeHandler(new ValueChangeHandler<Date>() {
      
      @Override
      public void onValueChange(ValueChangeEvent<Date> event) {
        Date d = event.getValue();
        DateTimeFormat f = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
        Info.display("Value Changed", "You selected " + f.format(d));
      }
    });
    vp.add(picker);

    return vp;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
