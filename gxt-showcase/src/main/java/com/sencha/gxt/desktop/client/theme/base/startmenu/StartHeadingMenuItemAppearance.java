/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktop.client.theme.base.startmenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.blue.client.menu.BlueMenuItemAppearance;

public class StartHeadingMenuItemAppearance extends BlueMenuItemAppearance {

  public interface StartHeadingMenuItemResources extends BlueMenuItemResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/menu/MenuItem.css", //
        "com/sencha/gxt/theme/blue/client/menu/BlueMenuItem.css", //
        "StartHeadingMenuItem.css"})
    StartHeadingMenuItemStyle style();

  }

  public interface StartHeadingMenuItemStyle extends BlueMenuItemStyle {
  }

  public StartHeadingMenuItemAppearance() {
    this(GWT.<StartHeadingMenuItemResources> create(StartHeadingMenuItemResources.class),
        GWT.<MenuItemTemplate> create(MenuItemTemplate.class));
  }

  public StartHeadingMenuItemAppearance(StartHeadingMenuItemResources resources, MenuItemTemplate template) {
    super(resources, template);
  }

}
