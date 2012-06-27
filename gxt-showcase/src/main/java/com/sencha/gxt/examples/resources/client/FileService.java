/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.examples.resources.client.model.FileModel;

/**
 * Example <code>RemoteService</code>.
 */
@RemoteServiceRelativePath("fileservice")
public interface FileService extends RemoteService {

  /**
   * Returns the children of the given parent.
   * 
   * @param folder the parent folder
   * @return the children
   */
  public List<FileModel> getFolderChildren(FileModel folder);

}
