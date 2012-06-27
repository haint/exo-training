/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client;

import com.sencha.gxt.desktop.client.layout.DesktopLayoutType;
import com.sencha.gxt.desktopapp.client.persistence.FileSystem;

/**
 * Provides a profile model interface for use with the GWT auto bean factory
 * framework. The profile model auto bean factory is generated automatically by
 * the framework and is capable of creating instances of these profile models.
 * <p/>
 * The profile model represents the user's profile settings. In this
 * implementation of the desktop application, it is managed by the
 * {@link ProfilePresenterImpl} and {@link DesktopAppPresenterImpl} and
 * persisted in the {@link FileSystem}.
 * 
 * @see ProfileFactory
 */
public interface ProfileModel {

  /**
   * Returns the type of desktop layout in the user's profile.
   * 
   * @return the current desktop layout type
   */
  DesktopLayoutType getDesktopLayoutType();

  /**
   * Returns the user's currently defined display name.
   * 
   * @return the current display name
   */
  String getDisplayName();

  /**
   * Sets the desktop layout type.
   * 
   * @param desktopLayoutType the desktop layout type
   */
  void setDesktopLayoutType(DesktopLayoutType desktopLayoutType);

  /**
   * Sets the display name.
   * 
   * @param displayName the display name
   */
  void setDisplayName(String displayName);

}
