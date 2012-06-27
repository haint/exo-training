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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.DualListField.Mode;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;

@Detail(name = "DualListField (UiBinder)", icon = "duallistfield", category = "Forms", files = "DualListFieldUiBinderExample.ui.xml")
public class DualListFieldUiBinderExample implements IsWidget, EntryPoint {

  interface StateProperties extends PropertyAccess<State> {
    ModelKeyProvider<State> abbr();

    LabelProvider<State> name();

    @Path("name")
    ValueProvider<State, String> nameProp();
  }

  interface MyUiBinder extends UiBinder<Widget, DualListFieldUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField(provided = true)
  DualListField<State, String> field;

  public Widget asWidget() {
    StateProperties props = GWT.create(StateProperties.class);
    ListStore<State> states = new ListStore<State>(props.abbr());
    ListStore<State> toStates = new ListStore<State>(props.abbr());

    field = new DualListField<State, String>(states, toStates, props.nameProp(), new TextCell());
    field.addValidator(new EmptyValidator<List<State>>());
    field.setEnableDnd(true);
    field.setMode(Mode.INSERT);

    states.addAll(TestData.getStates());
    return uiBinder.createAndBindUi(this);
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
