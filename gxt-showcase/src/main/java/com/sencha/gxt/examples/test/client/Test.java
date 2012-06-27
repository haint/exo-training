/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent.BeforeStartEditHandler;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

public class Test implements EntryPoint {

  interface ItemProperties extends PropertyAccess<Item> {
    @Path("name")
    ModelKeyProvider<Item> key();

    ValueProvider<Item, String> name();

    ValueProvider<Item, String> color();

    ValueProvider<Item, String> description();

    ValueProvider<Item, Boolean> hidden();

  }

  public class Item {

    private String name;
    private String color;
    private String description;
    private boolean hide;

    public Item() {
    }

    public Item(String name, String color, String description) {
      this.name = name;
      this.color = color;
      this.description = description;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public void setHidden(boolean hide) {
      this.hide = hide;
    }

    public boolean getHidden() {
      return hide;
    }
  }

  private static final List<String> SUPERSET = Arrays.asList("mother of pearl", "very, very dark red", "red", "yellow",
      "blue", "green", "orange", "purple", "black", "white");

  private Grid<Item> grid;

  private ListStore<Item> datastore;

  private Item currentRow;

  private SimpleComboBox<String> comboBox;

  private Widget asWidget() {

    VerticalLayoutContainer container = new VerticalLayoutContainer();

    ItemProperties properties = GWT.create(ItemProperties.class);
    datastore = new ListStore<Item>(properties.key());
    createDummyData();
    grid = createGrid(properties);

    Radio yes = new Radio();
    yes.setBoxLabel("yes");
    Radio no = new Radio();
    no.setBoxLabel("no");
    ToggleGroup group = new ToggleGroup();
    group.add(yes);
    group.add(no);
    group.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
      @Override
      public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
        System.out.println("onValueChange");
      }
    });
    container.add(yes);
    container.add(no);

    container.add(grid);

    return container;
  }

  @SuppressWarnings("unchecked")
  private Grid<Item> createGrid(ItemProperties properties) {
    ColumnModel<Item> model = createColumnModel(properties);

    Grid<Item> myGrid = new Grid<Item>(datastore, model);

    final GridInlineEditing<Item> editor = new GridInlineEditing<Item>(myGrid);

    final TextField field = new TextField();

    comboBox = new SimpleComboBox<String>(new LabelProvider<String>() {
      @Override
      public String getLabel(String item) {
        return (item != null) ? item : "";
      }
    });

    comboBox.setAllowBlank(true);
    comboBox.setForceSelection(true);
    comboBox.setTriggerAction(TriggerAction.ALL);

    comboBox.getStore().replaceAll(SUPERSET);

    comboBox.setPropertyEditor(new PropertyEditor<String>() {

      @Override
      public String parse(CharSequence text) throws ParseException {
        System.out.println("parse : " + text.toString());
        return text.toString();
      }

      @Override
      public String render(String object) {
        System.out.println("render : " + object);
        return object == null ? "Default" : object.toString();
      }
    });

    comboBox.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {

        System.out.println("onChange " + ((ComboBox<?>) event.getSource()).getCurrentValue());

        if ("purple".equals(comboBox.getCurrentValue())) {
          currentRow.setHidden(true);
          currentRow.setColor(comboBox.getCurrentValue());
          datastore.fireEvent(new StoreUpdateEvent<Item>(Arrays.asList(currentRow)));
        } else {
          currentRow.setHidden(false);
          currentRow.setColor(comboBox.getCurrentValue());
          datastore.fireEvent(new StoreUpdateEvent<Item>(Arrays.asList(currentRow)));
        }
      }
    });

    ColumnConfig<Item, String> col1 = (ColumnConfig<Item, String>) model.findColumnConfig("name");
    ColumnConfig<Item, String> col2 = (ColumnConfig<Item, String>) model.findColumnConfig("color");
    ColumnConfig<Item, String> col3 = (ColumnConfig<Item, String>) model.findColumnConfig("description");

    editor.addEditor(col1, field);
    editor.addEditor(col2, comboBox);
    editor.addEditor(col3, field);

    editor.addBeforeStartEditHandler(new BeforeStartEditHandler<Item>() {
      @Override
      public void onBeforeStartEdit(BeforeStartEditEvent<Item> event) {
        System.out.println("onBeforeStartEdit");
        currentRow = datastore.get(event.getEditCell().getRow());

        if (event.getEditCell().getCol() == 1) {

          List<String> list = new ArrayList<String>();
          for (String value : SUPERSET) {
            if (Random.nextBoolean()) {
              list.add(value);
            }
          }
          comboBox.getStore().replaceAll(list);
        }
      }
    });

    editor.addCompleteEditHandler(new CompleteEditHandler<Item>() {
      @Override
      public void onCompleteEdit(CompleteEditEvent<Item> event) {
        System.out.println("onCompleteEdit");
        // datastore.fireEvent(new
        // StoreUpdateEvent<Item>(Arrays.asList(currentRow)));
        currentRow = null;
      }
    });

    return myGrid;
  }

  private class EditableCell extends AbstractEditableCell<String, String> {
    @Override
    public boolean isEditing(com.google.gwt.cell.client.Cell.Context context, com.google.gwt.dom.client.Element parent,
        String value) {
      return true;
    }

    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context, String value, SafeHtmlBuilder sb) {

      System.out.println("rendering column " + context.getColumn());

      Item item = datastore.get(context.getIndex());

      // if item is 'hidden', don't display the description
      if (!item.getHidden() || context.getColumn() != 2) {

        if (item.getColor() != null) {
          sb.appendHtmlConstant("<span style='color:" + item.getColor() + "'>");
        }

        sb.appendHtmlConstant("" + value);

        if (item.getColor() != null) {
          sb.appendHtmlConstant("</span>");
        }
      }

    }
  }

  private ColumnModel<Item> createColumnModel(ItemProperties properties) {
    List<ColumnConfig<Item, ?>> configs = new ArrayList<ColumnConfig<Item, ?>>();

    ColumnConfig<Item, String> name = new ColumnConfig<Item, String>(properties.name());
    name.setWidth(100);
    name.setHeader("name");
    name.setCell(new EditableCell());

    ColumnConfig<Item, String> color = new ColumnConfig<Item, String>(properties.color());
    color.setWidth(200);
    color.setHeader("color");
    color.setCell(new EditableCell());

    ColumnConfig<Item, String> description = new ColumnConfig<Item, String>(properties.description());
    description.setWidth(200);
    description.setHeader("description");
    description.setCell(new EditableCell());

    configs.add(name);
    configs.add(color);
    configs.add(description);

    return new ColumnModel<Item>(configs);
  }

  private void createDummyData() {
    List<Item> list = new ArrayList<Item>(20);
    for (int i = 0; i < 20; i++) {
      list.add(new Item("name" + i, "black", "desciption" + i));
    }
    datastore.addAll(list);
    datastore.setAutoCommit(true);
  }

  public void onModuleLoad() {
    Viewport viewport = new Viewport();
    viewport.add(asWidget());
    RootPanel.get().add(viewport);
  }
}