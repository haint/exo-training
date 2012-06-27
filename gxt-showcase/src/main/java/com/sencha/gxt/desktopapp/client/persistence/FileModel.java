/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.persistence;

import java.util.Date;

import com.sencha.gxt.desktopapp.client.utility.Utility;

public interface FileModel {
  public enum FileType {
    BOOKMARK, DOCUMENT, FOLDER, PROGRAM, SPREADSHEET;

    @Override
    public String toString() {
      return Utility.capitalize(super.toString());
    }
  }

  public String getContent();

  public FileType getFileType();

  public String getId();

  public Date getLastModified();

  public String getName();

  public Long getSize();

  public void setContent(String content);

  public void setFileType(FileType fileType);

  public void setId(String id);

  public void setLastModified(Date lastModified);

  public void setName(String name);

  public void setSize(Long size);
}