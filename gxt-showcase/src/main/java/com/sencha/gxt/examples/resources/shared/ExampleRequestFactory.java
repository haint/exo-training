/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.shared;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface ExampleRequestFactory extends RequestFactory {
  MusicRequest music();

  FolderRequest folder();

  PostRequest post();
}
