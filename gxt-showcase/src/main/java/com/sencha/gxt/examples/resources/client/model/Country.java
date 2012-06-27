/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client.model;

public class Country {
  
  private String abbr;
  private String name;
  private int value;

  public Country() {

  }

  public Country(String abbr, String name, int value) {
    setAbbr(abbr);
    setName(name);
    setValue(value);
  }

  public void setAbbr(String abbr) {
    this.abbr = abbr;
  }

  public String getAbbr() {
    return abbr;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
