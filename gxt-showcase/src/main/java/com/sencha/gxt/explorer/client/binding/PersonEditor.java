/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.binding;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.model.Kid;
import com.sencha.gxt.examples.resources.client.model.Person;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

public class PersonEditor implements IsWidget, Editor<Person> {
  interface KidProperties extends PropertyAccess<Kid> {
    @Path("name")
    ModelKeyProvider<Kid> key();
    
    ValueProvider<Kid, String> name();
    ValueProvider<Kid, Integer> age();
  }
  private static final KidProperties props = GWT.create(KidProperties.class);

  TextField name = new TextField();
  TextField company = new TextField();
  TextField location = new TextField();
  
  NumberField<Double> income = new NumberField<Double>(new DoublePropertyEditor());
  
  ListStore<Kid> kidStore = new ListStore<Kid>(props.key());
  ListStoreEditor<Kid> kids = new ListStoreEditor<Kid>(kidStore);
  
  
  @Override
  public Widget asWidget() {
    FlowPanel container = new FlowPanel();
    
    
    // should be layout based
    int w = 275;
    name.setWidth(w);
    company.setWidth(w);
    location.setWidth(w);
    income.setWidth(w);
    
    container.add(new FieldLabel(name, "Name"));
    container.add(new FieldLabel(company, "Company"));
    container.add(new FieldLabel(location, "Location"));
    
    container.add(new FieldLabel(income, "Income"));
    
    List<ColumnConfig<Kid, ?>> columns = new ArrayList<ColumnConfig<Kid,?>>();
    ColumnConfig<Kid, String> name = new ColumnConfig<Kid, String>(props.name(), 200, "Name");
    columns.add(name);
    ColumnConfig<Kid, Integer> age = new ColumnConfig<Kid, Integer>(props.age(), 100, "Age");
    columns.add(age);
    
    
    Grid<Kid> grid = new Grid<Kid>(kidStore, new ColumnModel<Kid>(columns));
    grid.setBorders(true);
    
    grid.getView().setForceFit(true);
    GridInlineEditing<Kid> inlineEditor = new GridInlineEditing<Kid>(grid);
    inlineEditor.addEditor(name, new TextField());
    inlineEditor.addEditor(age, new NumberField<Integer>(new IntegerPropertyEditor()));

    grid.setWidth(382);
    grid.setHeight(200);
    
    FieldLabel kidsContainer = new FieldLabel();
    kidsContainer.setText("Kids");
    kidsContainer.setLabelAlign(LabelAlign.TOP);
    kidsContainer.setWidget(grid);
    container.add(kidsContainer);
    
    return container;
  }

}
