/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.spreadsheet;

import com.sencha.gxt.desktopapp.client.FileBasedMiniAppPresenter;

public interface SpreadsheetPresenter extends FileBasedMiniAppPresenter {

  void onCellValueChange(int rowIndex, int columnIndex, String value);

  void onColumnCountChange(int value);

  void onColumnMove();

  void onDeleteColumn();

  void onDeleteRow();

  void onDetailValueChange(String value);

  void onDragDrop();

  void onInsertAbove();

  void onInsertBelow();

  void onInsertLeft();

  void onInsertRight();

  void onOpenChart();

  void onRowCountChange(int value);

}