/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.shared;


import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.sencha.gxt.examples.resources.server.data.Music;

@Service(Music.class)
public interface MusicRequest extends RequestContext {
  InstanceRequest<MusicProxy, MusicProxy> persist();
  
  InstanceRequest<MusicProxy, Void> remove();
}
