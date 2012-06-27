/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.toolbar;

import java.util.Date;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.examples.resources.client.Resources;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.DateMenu;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

@Detail(name = "MenuBar", icon = "menubar", category = "ToolBar & Menu")
public class MenuBarExample implements IsWidget {

  @Override
  public Widget asWidget() {
    SelectionHandler<Item> handler = new SelectionHandler<Item>() {
      @Override
      public void onSelection(SelectionEvent<Item> event) {
        MenuItem item = (MenuItem)event.getSelectedItem();
        Info.display("Action", "You selected the " + item.getText());
      }
    };
    
    Menu menu = new Menu();
    menu.addSelectionHandler(handler);

    MenuItem item1 = new MenuItem("New");
    menu.add(item1);

    MenuItem item2 = new MenuItem("Open File");
    menu.add(item2);

    Menu sub = new Menu();
    sub.addSelectionHandler(handler);
    
    sub.add(new MenuItem("readme.txt"));
    sub.add(new MenuItem("helloworld.txt"));
    item2.setSubMenu(sub);

    MenuBar bar = new MenuBar();
    bar.addStyleName(ThemeStyles.getStyle().borderBottom());

    bar.add(new MenuBarItem("File", menu));

    Menu sub2 = new Menu();
    sub2.addSelectionHandler(handler);
    sub2.add(new MenuItem("Cut"));
    sub2.add(new MenuItem("Copy"));

    MenuBarItem edit = new MenuBarItem("Edit", sub2);
    bar.add(edit);

    sub = new Menu();
    sub.addSelectionHandler(handler);
    sub.add(new MenuItem("Search"));
    sub.add(new MenuItem("File"));
    sub.add(new MenuItem("Java"));

    MenuBarItem item3 = new MenuBarItem("Search", sub);
    bar.add(item3);
    
    menu = new Menu();
    menu.addSelectionHandler(handler);

    CheckMenuItem menuItem = new CheckMenuItem("I Like Cats");
    menuItem.setChecked(true);
    menu.add(menuItem);

    menuItem = new CheckMenuItem("I Like Dogs");
    menu.add(menuItem);


    menu.add(new SeparatorMenuItem());

    MenuItem radios = new MenuItem("Radio Options");
    menu.add(radios);

    Menu radioMenu = new Menu();
    radioMenu.addSelectionHandler(handler);
    CheckMenuItem r = new CheckMenuItem("Blue Theme");
    r.setGroup("radios");
    r.setChecked(true);
    radioMenu.add(r);
    r = new CheckMenuItem("Gray Theme");
    r.setGroup("radios");
    radioMenu.add(r);
    radios.setSubMenu(radioMenu);

    MenuItem date = new MenuItem("Choose a Date");
    date.setIcon(Resources.IMAGES.calendar());
    menu.add(date);

    final DateMenu dateMenu = new DateMenu();
    dateMenu.getDatePicker().addValueChangeHandler(new ValueChangeHandler<Date>() {
      
      @Override
      public void onValueChange(ValueChangeEvent<Date> event) {
        Date d = event.getValue();
        DateTimeFormat f = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
        Info.display("Value Changed", "You selected " + f.format(d));
        dateMenu.hide(true);
      }
    });
    date.setSubMenu(dateMenu);
    
    MenuBarItem item4 = new MenuBarItem("Foo", menu);
    bar.add(item4);

    ContentPanel panel = new ContentPanel();
    panel.setHeadingText("MenuBar Example");
    panel.setPixelSize(400, 300);
    panel.getElement().setMargins(10);
    
    NorthSouthContainer comp = new NorthSouthContainer();
    comp.setNorthWidget(bar);
    
    panel.add(comp);
    
    return panel;
  }

}
