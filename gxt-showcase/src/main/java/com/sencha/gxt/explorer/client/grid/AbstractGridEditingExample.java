/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.grid;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Plant;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.Grid.GridCell;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public abstract class AbstractGridEditingExample implements IsWidget, EntryPoint {

  // just to show the converter feature
  enum Light {
    MOSTLYSHADY("Mostly Shady"), MOSTLYSUNNY("Mostly Sunny"), SHADE("Shade"), SUNNY("Sunny"), SUNORSHADE("Sun or Shade");
    static Light parseString(String object) {
      if (Light.MOSTLYSUNNY.toString().equals(object)) {
        return Light.MOSTLYSUNNY;
      } else if (Light.SUNORSHADE.toString().equals(object)) {
        return Light.SUNORSHADE;
      } else if (Light.MOSTLYSHADY.toString().equals(object)) {
        return Light.MOSTLYSHADY;
      } else if (Light.SHADE.toString().equals(object)) {
        return Light.SHADE;
      } else {
        return Light.SUNNY;
      }
    }

    private String text;

    Light(String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return text;
    }
  }

  interface PlaceProperties extends PropertyAccess<Plant> {
    ValueProvider<Plant, Date> available();

    @Path("id")
    ModelKeyProvider<Plant> key();

    ValueProvider<Plant, String> light();

    ValueProvider<Plant, String> name();

    ValueProvider<Plant, Boolean> indoor();

    ValueProvider<Plant, Double> price();
  }

  private static final PlaceProperties properties = GWT.create(PlaceProperties.class);
  
  protected Grid<Plant> grid;

  @Override
  public Widget asWidget() {
    ColumnConfig<Plant, String> cc1 = new ColumnConfig<Plant, String>(properties.name(), 220, "Name");

    ColumnConfig<Plant, String> cc2 = new ColumnConfig<Plant, String>(properties.light(), 130, "Light");

    DateCell dateCell = new DateCell(DateTimeFormat.getFormat("yyyy MMM dd"));

    ColumnConfig<Plant, Date> cc3 = new ColumnConfig<Plant, Date>(properties.available(), 95, "Date");

    cc3.setCell(dateCell);

    ColumnConfig<Plant, Boolean> cc4 = new ColumnConfig<Plant, Boolean>(properties.indoor(), 55, "Indoor");
    cc4.setCell(new SimpleSafeHtmlCell<Boolean>(new AbstractSafeHtmlRenderer<Boolean>() {
      @Override
      public SafeHtml render(Boolean object) {
        return SafeHtmlUtils.fromString(object ? "True" : "False");
      }
    }));

    ColumnConfig<Plant, Double> cc5 = new ColumnConfig<Plant, Double>(properties.price(), 100, "Price");
    cc5.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    cc5.setCell(new SimpleSafeHtmlCell<Double>(new AbstractSafeHtmlRenderer<Double>() {
      @Override
      public SafeHtml render(Double object) {
        return SafeHtmlUtils.fromString(NumberFormat.getCurrencyFormat().format(object));
      }
    }));

    List<ColumnConfig<Plant, ?>> l = new ArrayList<ColumnConfig<Plant, ?>>();
    l.add(cc1);
    l.add(cc2);
    l.add(cc5);
    l.add(cc3);
    l.add(cc4);

    ColumnModel<Plant> cm = new ColumnModel<Plant>(l);

    final ListStore<Plant> store = new ListStore<Plant>(properties.key());
    store.addAll(TestData.getPlants());

    grid = new Grid<Plant>(store, cm);
    grid.getView().setAutoExpandColumn(cc1);

    // EDITING//
    final GridEditing<Plant> editing = createGridEditing(grid);
    editing.addEditor(cc1, new TextField());

    SimpleComboBox<Light> combo = new SimpleComboBox<Light>(new StringLabelProvider<Light>());
    combo.setPropertyEditor(new PropertyEditor<Light>() {

      @Override
      public Light parse(CharSequence text) throws ParseException {
        return Light.parseString(text.toString());
      }

      @Override
      public String render(Light object) {
        return object == null ? Light.SUNNY.toString() : object.toString();
      }
    });
    combo.setTriggerAction(TriggerAction.ALL);
    combo.add(Light.SUNNY);
    combo.add(Light.MOSTLYSUNNY);
    combo.add(Light.SUNORSHADE);
    combo.add(Light.MOSTLYSHADY);
    combo.add(Light.SHADE);
    combo.setForceSelection(true);
    editing.addEditor(cc2, new Converter<String, Light>() {

      @Override
      public String convertFieldValue(Light object) {
        return object == null ? Light.SUNNY.toString() : object.toString();
      }

      @Override
      public Light convertModelValue(String object) {
        return Light.parseString(object);
      }

    }, combo);

    DateField dateField = new DateField(new DateTimePropertyEditor(
        DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
    dateField.setClearValueOnParseError(false);
    editing.addEditor(cc3, dateField);
    
    CheckBox checkField = new CheckBox();
    editing.addEditor(cc4, checkField);

    // column 5 is not editable

    // EDITING//

    FramedPanel cp = new FramedPanel();
    cp.setHeadingText("Editable Grid Example");
    cp.setPixelSize(600, 400);
    cp.addStyleName("margin-10");

    ToolBar toolBar = new ToolBar();

    TextButton add = new TextButton("Add Plant");
    add.addSelectHandler(new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        Plant plant = new Plant();
        plant.setName("New Plant 1");
        plant.setLight("Mostly Shady");
        plant.setPrice(0);
        plant.setAvailable(new DateWrapper().clearTime().asDate());
        plant.setIndoor(false);

        editing.cancelEditing();
        store.add(0, plant);
        editing.startEditing(new GridCell(0, 0));
      }
    });

    toolBar.add(add);

    VerticalLayoutContainer con = new VerticalLayoutContainer();
    con.setBorders(true);
    con.add(toolBar, new VerticalLayoutData(1, -1));
    con.add(grid, new VerticalLayoutData(1, 1));

    cp.setWidget(con);

    cp.setButtonAlign(BoxLayoutPack.CENTER);
    cp.addButton(new TextButton("Reset", new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        store.rejectChanges();
      }
    }));

    cp.addButton(new TextButton("Save", new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        store.commitChanges();
      }
    }));

    return cp;
  }

  protected abstract GridEditing<Plant> createGridEditing(Grid<Plant> grid);

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }
}
