/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.app.ui;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.explorer.client.ExplorerApp;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.explorer.client.model.ExampleModel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;

public class ExampleDetailViewImpl implements ExampleDetailView {

  private TabPanel tabPanel;
  private Presenter presenter;

  public interface Renderer extends XTemplates {
    @XTemplate(source = "template.html")
    public SafeHtml renderItems(List<Example> items);
  }

  @Inject
  private ExampleModel exampleModel;

  @Inject
  public ExampleDetailViewImpl(ExampleModel model) {
    tabPanel = new TabPanel();
    tabPanel.setBodyBorder(true);
    tabPanel.setTabScroll(true);
    tabPanel.setCloseContextMenu(true);

    tabPanel.addSelectionHandler(new SelectionHandler<Widget>() {
      @Override
      public void onSelection(SelectionEvent<Widget> event) {
        Widget item = event.getSelectedItem();
        TabItemConfig config = tabPanel.getConfig(item);
        String name = config.getText();
        Example e = exampleModel.findExampleByName(name);
        presenter.selectExample(e);
      }
    });
  }

  @Override
  public Widget asWidget() {
    return tabPanel;
  }

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  // public void onPlaceChange(PlaceChangeEvent event) {
  // Place place = event.getNewPlace();
  // if (place instanceof ExamplePlace) {
  // ExamplePlace ep = (ExamplePlace) place;
  // showExample(exampleModel.findExample(ep.getExampleId()));
  // }
  // }

  @Override
  public void showExample(final Example example) {
    if (example != null) {
      Page page = findTabItem(example.getName());
      if (page == null) {

        // TODO consider assisted inject for this, as it gets no dependencies
        // this way
        page = new Page(example);

        TabItemConfig config = new TabItemConfig(example.getName(), true);
        config.setClosable(!ExplorerApp.OVERVIEW.equals(example.getName()));
        tabPanel.add(page, config);
      }
      tabPanel.setActiveWidget(page);
    }
  }

  private Page findTabItem(String name) {
    for (int i = 0; i < tabPanel.getWidgetCount(); i++) {
      Page item = (Page) tabPanel.getWidget(i);
      TabItemConfig config = tabPanel.getConfig(item);
      if ((config.isHTML() ? config.getHTML() : config.getText()).equals(name)) {
        return item;
      }
    }
    return null;

  }

}
