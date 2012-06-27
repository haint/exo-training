/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client;

public interface FileBasedMiniAppPresenter extends Presenter {

  void bind();

  void onClose();

  void onSave();

  void onContentUpdate();

  void unbind();
}
