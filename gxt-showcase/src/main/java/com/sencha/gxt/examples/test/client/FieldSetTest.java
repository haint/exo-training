/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FieldSetTest implements EntryPoint {

  @Override
  public void onModuleLoad() {
    FieldSet set = new FieldSet();
    set.setWidth(450);
    set.setHeadingText("FieldSet Test");
    set.setCollapsible(true);
    
    VerticalLayoutContainer p = new VerticalLayoutContainer();

    TextField firstName = new TextField();
    firstName.setAllowBlank(false);
    p.add(new FieldLabel(firstName, "Name"), new VerticalLayoutData(1, -1));

    TextField email = new TextField();
    email.setAllowBlank(false);

    p.add(new FieldLabel(email, "Email"), new VerticalLayoutData(1, -1));

    set.setWidget(p);

    RootPanel.get().add(set);
  }

}
