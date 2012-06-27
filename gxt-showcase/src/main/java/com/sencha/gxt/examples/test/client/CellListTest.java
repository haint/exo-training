/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class CellListTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    Menu menu = new Menu();
    menu.add(new MenuItem("Click Me")); 
   
    
    TextButtonCell cell = new TextButtonCell();
    cell.setMenu(menu);
    cell.setWidth(100);
    cell.addSelectHandler(new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        System.out.println(event.getSource().getClass());
        System.out.println(event.getContext().getIndex());
      }
    });
    
    
    CellList<Date> list = new CellList<Date>(new DatePickerCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
    list.setWidth("300px");
    
    List<Date> data = new ArrayList<Date>();
    data.add(new Date());
    data.add(new Date());
    
    list.setRowData(data);
    
    TextButton button = new TextButton("sfsdfs");
    button.addSelectHandler(new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        System.out.println(event.getSource().getClass());
      }
    });
    
    RootPanel.get().add(list);
    
    RootPanel.get().add(button);
  }

}
