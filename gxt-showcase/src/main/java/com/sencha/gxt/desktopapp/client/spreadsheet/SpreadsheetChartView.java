/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.spreadsheet;

import com.google.gwt.user.client.ui.IsWidget;

public interface SpreadsheetChartView extends IsWidget {

  void configure(Worksheet worksheet);

  void setTitle(String newTitle);

}
