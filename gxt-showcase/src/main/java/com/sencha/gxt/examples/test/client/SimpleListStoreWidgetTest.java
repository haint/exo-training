/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Country;

public class SimpleListStoreWidgetTest implements EntryPoint {

  /**
   * Example sub-interface, providing not only property access, but sub-property
   * access, a la GWT editors and GXT2 column data names
   * 
   * Manually initiated instances of this are something we want to allow, but
   * not encourage directly, except as a way to solve problems we don't give a
   * proper/complete solution for. This can then be used internally by column,
   * etc generated setups to get access to only what is needed.
   */
  public interface DataPropertyAccess extends PropertyAccess<Country> {
    @Path("abbr")
    ModelKeyProvider<Country> abbrKey();
    
    @Path("name")
    LabelProvider<Country> lp();

    ValueProvider<Country, String> name();

    ValueProvider<Country, Integer> value();

    ValueProvider<Country, String> abbr();

    // @Path("lastModified.year")
    // ValueProvider<Customer, Integer> modifiedYear();
  }

  /**
   * Accessor for this, currently static so it is global. Remember that this
   * should in most cases be generated as needed, and is intended only to
   * provide references to the ValueProvider impls as they exist, a la GWT RPC
   * field serializers
   */
  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

  /**
   * Should be generated off of SafeHtmlTemplates + Data model to interpolate
   * names. Some amount of string processing should be allowed as well.
   * 
   * A renderer like this can be provided in a more general way: Steal the @Template
   * annotation, and build our own interface for interpolating field accessors.
   * The *PropertyAccess types can be generated off of just what this needs,
   * instead of mucking around with manually building too many properties as I
   * have done.
   */
  // public static class DataRendererImpl implements ItemRenderer<Country> {
  // interface Templates extends SafeHtmlTemplates {
  // @Template("<div><b>{0}</b><br/>{1} ({2})</div>")
  // SafeHtml listTemplate(String name, String abbr, int value);
  // }
  //
  // private final static Templates t = GWT.create(Templates.class);
  //
  // @Override
  // public SafeHtml getRenderedItem(Country data) {
  // return t.listTemplate(data.getName(), data.getAbbr(), data.getValue());
  // }
  //
  // public SafeHtml getRenderedItem(ListStore<Country>.Record record) {
  // return t.listTemplate(record.getValue(dataAccess.name()) + "*",
  // record.getValue(dataAccess.abbr()),
  // record.getValue(dataAccess.value()));
  // }
  // }
  public interface DataRenderer extends XTemplates {
    @XTemplate("<div>Countries:<tpl for='.'><div>{#}. {name} ({abbr}): {value}</div></tpl></div>")
    public SafeHtml renderItems(List<Country> items, CssResource style);
  }

  static class LoadResult implements ListLoadResult<Country> {
    private List<Country> data;

    @Override
    public List<Country> getData() {
      return data;
    }

    public void setData(List<Country> data) {
      this.data = data;
    }
  }

  /**
   * App sample combining these tools:
   * 
   * * Build a data container, give it some starting data
   * 
   * * Make a data view, primed with a basic renderer/display provider keyed
   * directly to the data to be show
   * 
   * * Provide a few ways to manipulate the wrapped data
   * 
   * Next steps:
   * 
   * * Show editor usage, flushing data back to model for storage
   * 
   * * Show loader usage, paging, filtering, sorting, etc
   */
  @Override
  public void onModuleLoad() {
    final ListStore<Country> dataStore = new ListStore<Country>(dataAccess.abbrKey());
    dataStore.addSortInfo(new StoreSortInfo<Country>(dataAccess.value(), SortDir.ASC));
    dataStore.addAll(TestData.getCountries());
    //
    // DataRenderer renderer = GWT.create(DataRenderer.class);
    // ListView<Country> view = new ListView<Country>(dataStore, new
    // ListView.CustomListViewAppearaance<Country>();
    // RootPanel.get().add(view);
    // view.addDomHandler(new DoubleClickHandler() {
    // @Override
    // public void onDoubleClick(DoubleClickEvent event) {
    // if (dataStore.size() > 0) {
    // // bad way - no event!
    // // dataStore.get(0).name += "blah";
    //
    // // Note that this call, and its ValueProvider, should be generated. At
    // // the same time, we want to be able to allow customization of how it
    // // works
    // int itemIndex = 0;
    // int currValue =
    // dataStore.getRecord(dataStore.get(itemIndex)).getValue(dataAccess.value());
    // dataStore.getRecord(dataStore.get(0)).addChange(dataAccess.value(),
    // currValue + 1);
    // }
    // }
    // }, DoubleClickEvent.getType());
    //
    // TextButton moreData = new TextButton("AddItem");
    // moreData.addClickHandler(new ClickHandler() {
    // int counter = 0;
    // @Override
    // public void onClick(ClickEvent event) {
    // dataStore.add(new Country("new" + counter, "New Un-Named Country #" +
    // counter++, 0));
    // }
    // });
    // RootPanel.get().add(moreData);
    //
    // TextButton clear = new TextButton("Clear");
    // clear.addClickHandler(new ClickHandler() {
    // @Override
    // public void onClick(ClickEvent event) {
    // dataStore.clear();
    // }
    // });
    // RootPanel.get().add(clear);
    //
    // TextButton commit = new TextButton("Save");
    // commit.addClickHandler(new ClickHandler() {
    // @Override
    // public void onClick(ClickEvent event) {
    // dataStore.commitChanges();
    // }
    // });
    // RootPanel.get().add(commit);
  }

}
