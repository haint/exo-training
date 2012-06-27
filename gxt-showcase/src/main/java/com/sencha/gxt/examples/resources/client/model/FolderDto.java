/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client.model;

import java.util.List;

@SuppressWarnings("serial")
public class FolderDto extends BaseDto {

  private List<BaseDto> children;

  protected FolderDto() {

  }

  public FolderDto(Integer id, String name) {
    super(id, name);
  }

  public List<BaseDto> getChildren() {
    return children;
  }

  public void setChildren(List<BaseDto> children) {
    this.children = children;
  }

  public void addChild(BaseDto child) {
    getChildren().add(child);
  }
}
