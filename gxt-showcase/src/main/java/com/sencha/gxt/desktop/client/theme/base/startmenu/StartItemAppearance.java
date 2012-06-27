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
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.blue.client.menu.BlueItemAppearance;

public class StartItemAppearance extends BlueItemAppearance {

  public interface StartItemResources extends BlueItemResources, ClientBundle {

    ImageResource itemOver();

    @Source({"com/sencha/gxt/theme/base/client/menu/Item.css", //
        "com/sencha/gxt/theme/blue/client/menu/BlueItem.css", //
        "StartItem.css"})
    StartItemStyle style();

  }

  public interface StartItemStyle extends BlueItemStyle {
  }

  public StartItemAppearance() {
    this(GWT.<StartItemResources> create(StartItemResources.class));
  }

  public StartItemAppearance(StartItemResources resources) {
    super(resources);
  }

}
