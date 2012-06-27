/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.spreadsheet;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public interface ColumnProvider {

  ColumnConfig<Row, Object> getColumn(int cellIndex);

  void setColumnHeader(int columnIndex, SafeHtml fromString);

}
