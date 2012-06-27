/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.filemanager;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.desktopapp.client.persistence.FileModel;

public interface FileManagerView extends IsWidget {

  public void collapse();

  public void editName(FileModel childFileModel);

  public void expand();

  public FileModel getSelectedItem();

  public List<FileModel> getSelectedItems();

  public void selectFileModel(FileModel fileModel);

}