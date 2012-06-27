/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public final class EMF {
  private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("openjpa");

  public static EntityManagerFactory get() {
    return emfInstance;
  }

  private EMF() {
    // nothing
  }
}