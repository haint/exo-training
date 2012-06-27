/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.examples.resources.client.model.FileModel;

/**
 * Async <code>FileService<code> interface.
 */
public interface FileServiceAsync {

  public void getFolderChildren(FileModel model, AsyncCallback<List<FileModel>> children);

  // public void getFolderChildren(RemoteSortTreeLoadConfig loadConfig,
  // AsyncCallback<List<FileModel>> children);

}
