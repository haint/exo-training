/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.forms;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.Country;
import com.sencha.gxt.examples.resources.client.model.State;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.info.Info;

@Detail(name = "ComboBox", icon = "combobox", category = "Combos")
public class ComboBoxExample implements IsWidget, EntryPoint {

  interface ComboBoxTemplates extends XTemplates {

    @XTemplate("<img width=\"16\" height=\"11\" src=\"{imageUri}\">&nbsp;{name}")
    SafeHtml country(SafeUri imageUri, String name);

    @XTemplate("<div qtip=\"{slogan}\" qtitle=\"State Slogan\">{name}</div>")
    SafeHtml state(String slogan, String name);

  }

  interface CountryProperties extends PropertyAccess<Country> {
    ModelKeyProvider<Country> abbr();

    LabelProvider<Country> name();
  }

  interface StateProperties extends PropertyAccess<State> {
    ModelKeyProvider<State> abbr();

    LabelProvider<State> name();
  }

  private VerticalPanel vp;

  public Widget asWidget() {
    if (vp == null) {
      vp = new VerticalPanel();
      vp.setSpacing(10);

      StateProperties props = GWT.create(StateProperties.class);
      ListStore<State> states = new ListStore<State>(props.abbr());
      states.addAll(TestData.getStates());

      ComboBox<State> combo = new ComboBox<State>(states, props.name());
      addHandlersForEventObservation(combo, props.name());

      combo.setEmptyText("Select a state...");
      combo.setWidth(150);
      combo.setTypeAhead(true);
      combo.setTriggerAction(TriggerAction.ALL);
      vp.add(combo);

      states = new ListStore<State>(props.abbr());
      states.addAll(TestData.getStates());

      combo = new ComboBox<State>(states, props.name(), new AbstractSafeHtmlRenderer<State>() {
        public SafeHtml render(State item) {
          final ComboBoxTemplates comboBoxTemplates = GWT.create(ComboBoxTemplates.class);
          return comboBoxTemplates.state(item.getSlogan(), item.getName());
        }
      });
      addHandlersForEventObservation(combo, props.name());

      combo.setEmptyText("Select a state...");
      combo.setWidth(150);
      combo.setTypeAhead(true);
      combo.setTriggerAction(TriggerAction.ALL);
      vp.add(combo);

      final CountryProperties countryProps = GWT.create(CountryProperties.class);

      ListStore<Country> countries = new ListStore<Country>(countryProps.abbr());
      countries.addAll(TestData.getCountries());

      final String moduleBaseUrl = GWT.getHostPageBaseURL();
      ComboBox<Country> combo2 = new ComboBox<Country>(countries, countryProps.name(),
          new AbstractSafeHtmlRenderer<Country>() {
            final ComboBoxTemplates comboBoxTemplates = GWT.create(ComboBoxTemplates.class);

            public SafeHtml render(Country item) {
              SafeUri imageUri = UriUtils.fromString(moduleBaseUrl + "examples/images/flags/" + item.getAbbr() + ".png");
              return comboBoxTemplates.country(imageUri, item.getName());

            }
          });
      addHandlersForEventObservation(combo2, countryProps.name());
      
      combo2.setWidth(150);
      combo2.setTypeAhead(true);
      combo2.setTriggerAction(TriggerAction.ALL);

      vp.add(combo2);
    }
    return vp;
  }

  /**
   * Helper to add handlers to observe events that occur on each combobox
   */
  private <T> void addHandlersForEventObservation(ComboBox<T> combo, final LabelProvider<T> labelProvider) {
    combo.addValueChangeHandler(new ValueChangeHandler<T>() {
      @Override
      public void onValueChange(ValueChangeEvent<T> event) {
        Info.display("Value Changed", "New value: "
            + (event.getValue() == null ? "nothing" : labelProvider.getLabel(event.getValue()) + "!"));
      }
    });
    combo.addSelectionHandler(new SelectionHandler<T>() {
      @Override
      public void onSelection(SelectionEvent<T> event) {
        Info.display("State Selected", "You selected "
            + (event.getSelectedItem() == null ? "nothing" : labelProvider.getLabel(event.getSelectedItem()) + "!"));
      }
    });
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
