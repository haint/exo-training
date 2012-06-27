/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.app.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.sencha.gxt.explorer.client.app.ui.ExampleDetailView;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.explorer.client.model.ExampleModel;

public class ShowExampleActivity extends AbstractActivity {
  @Inject
  private ExampleDetailView detailView;

  @Inject
  private ExampleModel model;

  private String exampleId;

  public String getExampleId() {
    return exampleId;
  }

  public void setExampleId(String exampleId) {
    this.exampleId = exampleId;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    Example example = model.findExample(exampleId);
    detailView.showExample(example);
  }

}
