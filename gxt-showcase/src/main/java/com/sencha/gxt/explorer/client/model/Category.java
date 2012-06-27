/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.model;

import java.util.ArrayList;
import java.util.List;

public class Category extends NamedModel {

  private List<Example> examples;

  public Category(String name) {
    super(name);

    examples = new ArrayList<Example>();
  }

  public void addExample(Example example) {
    examples.add(example);
  }

  public List<Example> getExamples() {
    return examples;
  }

  public void setExamples(List<Example> examples) {
    this.examples = examples;
  }

}
