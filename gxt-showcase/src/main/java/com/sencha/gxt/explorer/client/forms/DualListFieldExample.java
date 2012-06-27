/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.forms;

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.State;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.DualListField.Mode;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;

@Detail(name = "DualListField", icon = "duallistfield", category = "Forms")
public class DualListFieldExample implements IsWidget, EntryPoint {

  interface StateProperties extends PropertyAccess<State> {
    ModelKeyProvider<State> abbr();

    LabelProvider<State> name();

    @Path("name")
    ValueProvider<State, String> nameProp();
  }

  private FramedPanel panel;

  public Widget asWidget() {
    if (panel == null) {
      panel = new FramedPanel();
      panel.setHeadingText("DualListField Example");
      panel.setWidth(550);
      panel.getElement().setMargins(10);

      VerticalLayoutContainer con = new VerticalLayoutContainer();
      panel.add(con);

      TextField firstName = new TextField();
      firstName.setAllowBlank(false);
      con.add(new FieldLabel(firstName, "Name"), new VerticalLayoutData(-18, -1));

      StateProperties props = GWT.create(StateProperties.class);
      ListStore<State> states = new ListStore<State>(props.abbr());
      states.addAll(TestData.getStates());

      ListStore<State> toStates = new ListStore<State>(props.abbr());

      final DualListField<State, String> field = new DualListField<State, String>(states, toStates, props.nameProp(),
          new TextCell());
      field.addValidator(new EmptyValidator<List<State>>());
      field.setEnableDnd(true);
      field.setMode(Mode.INSERT);

      con.add(new FieldLabel(field, "States"), new VerticalLayoutData(-18, -1));

      TextField email = new TextField();
      email.setAllowBlank(false);

      con.add(new FieldLabel(email, "Email"), new VerticalLayoutData(-18, -1));

      panel.addButton(new TextButton("Cancel"));
      panel.addButton(new TextButton("Save"));
    }

    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
