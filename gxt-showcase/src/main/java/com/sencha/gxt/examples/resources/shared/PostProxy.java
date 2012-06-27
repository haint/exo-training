/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.shared;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.sencha.gxt.examples.resources.client.model.Post;

@ProxyFor(Post.class)
public interface PostProxy extends ValueProxy {
  int getId();

  String getUsername();

  String getForum();

  String getSubject();

  Date getDate();
}
