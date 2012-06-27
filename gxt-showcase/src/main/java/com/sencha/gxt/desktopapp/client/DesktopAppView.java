/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.desktop.client.layout.DesktopLayoutType;

/**
 * Provides a desktop application view.
 */
public interface DesktopAppView extends IsWidget, HasWidgets {

  /**
   * Sets the type of desktop layout to use when opening subsequent windows.
   * 
   * @param desktopLayoutType the layout to use when opening subsequent windows
   */
  void setDesktopLayoutType(DesktopLayoutType desktopLayoutType);

}
