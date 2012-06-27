/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.sencha.gxt.desktopapp.client.event.OpenFileModelEvent.OpenFileModelHandler;
import com.sencha.gxt.desktopapp.client.persistence.FileModel;
import com.sencha.gxt.desktopapp.client.persistence.FileSystem;

public class OpenFileModelEvent extends GwtEvent<OpenFileModelHandler> {

  public interface OpenFileModelHandler extends EventHandler {
    void onOpenFileModel(OpenFileModelEvent event);
  }

  public static Type<OpenFileModelHandler> TYPE = new Type<OpenFileModelHandler>();
  private FileSystem fileSystem;
  private FileModel fileModel;

  public OpenFileModelEvent(FileSystem fileSystem, FileModel fileModel) {
    this.fileSystem = fileSystem;
    this.fileModel = fileModel;
  }

  @Override
  public Type<OpenFileModelHandler> getAssociatedType() {
    return TYPE;
  }

  public FileModel getFileModel() {
    return fileModel;
  }

  public FileSystem getFileSystem() {
    return fileSystem;
  }

  @Override
  protected void dispatch(OpenFileModelHandler handler) {
    handler.onOpenFileModel(this);
  }
}