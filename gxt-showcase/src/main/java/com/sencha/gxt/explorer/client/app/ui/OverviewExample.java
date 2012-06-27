/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.app.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.explorer.client.app.place.ExamplePlace;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.explorer.client.model.ExampleModel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.ListViewCustomAppearance;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

@Detail(name = "Overview", icon = "overview", category = "overview", fit = true)
public class OverviewExample implements IsWidget {

  public interface Renderer extends XTemplates {
    @XTemplate(source = "template.html")
    public SafeHtml renderItem(Example items);
  }

  private SimpleContainer overview = new SimpleContainer();

  private ListStore<Example> overviewStore = new ListStore<Example>(Example.KP);
  private ListView<Example, Example> overviewView;
  private PlaceController placeController;

  @Override
  public Widget asWidget() {
    final Renderer r = GWT.create(Renderer.class);

    ListViewCustomAppearance<Example> appearance = new ListViewCustomAppearance<Example>(".sample-box", "sample-over",
        "none") {
      @Override
      public void renderEnd(SafeHtmlBuilder builder) {
        String markup = new StringBuilder("<div class=\"").append(CommonStyles.get().clear()).append("\"></div>").toString();
        builder.appendHtmlConstant(markup);
      }

      @Override
      public void renderItem(SafeHtmlBuilder builder, SafeHtml content) {
        builder.appendHtmlConstant("<div class='sample-box' style='padding-top: 4px; border: none'>");
        builder.append(content);
        builder.appendHtmlConstant("</div>");
      }
    };

    overviewView = new ListView<Example, Example>(overviewStore, new IdentityValueProvider<Example>() {

      @Override
      public void setValue(Example object, Example value) {
      }
    }, appearance);
    overviewView.setCell(new SimpleSafeHtmlCell<Example>(new AbstractSafeHtmlRenderer<Example>() {

      @Override
      public SafeHtml render(Example object) {
        return r.renderItem(object);
      }

    }));
    overviewView.addStyleName("overview-page");
    overviewView.setBorders(false);

    overviewView.getSelectionModel().addSelectionHandler(new SelectionHandler<Example>() {
      @Override
      public void onSelection(SelectionEvent<Example> event) {
        if (event.getSelectedItem() != null) {
          placeController.goTo(new ExamplePlace(event.getSelectedItem().getId()));
          overviewView.getSelectionModel().deselectAll();
        }
      }
    });
    overview.add(overviewView);
    return overview;
  }

  public void loadData(ExampleModel exampleModel) {
    overviewStore.addAll(exampleModel.getExamplesAsList());
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  public void setPlaceController(PlaceController placeController) {
    this.placeController = placeController;
  }
}
