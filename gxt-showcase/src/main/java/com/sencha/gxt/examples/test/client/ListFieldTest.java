/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.State;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ListField;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;

public class ListFieldTest implements EntryPoint {

  interface StateProperties extends PropertyAccess<State> {
    ModelKeyProvider<State> abbr();

    LabelProvider<State> name();

    @Path("name")
    ValueProvider<State, String> nameProp();
  }

  @Override
  public void onModuleLoad() {
    StateProperties props = GWT.create(StateProperties.class);
    ListStore<State> states = new ListStore<State>(props.abbr());
    states.addAll(TestData.getStates());

    ListView<State, String> view = new ListView<State, String>(states, props.nameProp());

    final ListField<State, String> field = new ListField<State, String>(view);
    field.addValidator(new EmptyValidator<State>());
    field.setPixelSize(200, 300);

    RootPanel.get().add(field);
    RootPanel.get().add(new TextButton("Validate", new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        field.validate();
      }
    }));
  }

}
