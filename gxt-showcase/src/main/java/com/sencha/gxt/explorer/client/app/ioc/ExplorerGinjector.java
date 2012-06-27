/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.app.ioc;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.sencha.gxt.explorer.client.ExplorerApp;

@GinModules(ExplorerModule.class)
public interface ExplorerGinjector extends Ginjector {

  ExplorerApp getApp();
}
