/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client.model;

import java.util.Date;

public class Kid {
  private String name;
  private int age;
  private Date bday;

  public Kid(String name, int age, Date bday) {
    setName(name);
    setAge(age);
    setBday(bday);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getBday() {
    return bday;
  }

  public void setBday(Date bday) {
    this.bday = bday;
  }
}