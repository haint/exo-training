/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client.model;

import java.util.Date;


public interface PlantProxy {

  public Date getAvailable();

  public void setAvailable(Date available);

  public boolean isIndoor();

  public void setIndoor(boolean indoor);

  public String getLight();
  
  public void setLight(String light);

  public String getName();

  public void setName(String name);

  public double getPrice();

  public void setPrice(double price);

}
