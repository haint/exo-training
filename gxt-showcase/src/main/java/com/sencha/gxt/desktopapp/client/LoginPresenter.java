/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Presents the login view to the user.
 */
public interface LoginPresenter {

  /**
   * Creates a login view and connects it to the user interface.
   * 
   * @param hasWidgets the user interface
   */
  void go(HasWidgets hasWidgets);

  /**
   * Handles a login request from the view. Attempts to log a user into the
   * application.
   */
  void onLogin();

}
