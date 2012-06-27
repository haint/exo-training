/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.app.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.explorer.client.model.Source;
import com.sencha.gxt.explorer.client.model.Source.FileType;
import com.sencha.gxt.theme.blue.client.tabs.BlueTabPanelBottomAppearance;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * Displays an example and the associated source code.
 */
public class Page extends TabPanel {
  public interface SourceProperties extends PropertyAccess<Source> {
    @Path("id")
    ModelKeyProvider<Source> key();

    @Path("name")
    ValueProvider<Source, String> nameLabel();
  }

  private ContentPanel center;
  private SourceProperties properties = GWT.create(SourceProperties.class);
  private SimpleContainer sourceContainer;

  public Page(final Example example) {
    super(GWT.<TabPanelAppearance> create(BlueTabPanelBottomAppearance.class));

    setBodyBorder(false);

    Container demo = null;

    Widget w = example.getExample().asWidget();

    if (example.isFill()) {
      demo = new SimpleContainer();
      demo.getElement().getStyle().setOverflow(Overflow.HIDDEN);
      demo.getElement().getStyle().setPosition(Position.RELATIVE);
    } else {
      demo = new FlowLayoutContainer();
      ((FlowLayoutContainer)demo).getScrollSupport().setScrollMode(ScrollMode.AUTOY);
    }

    demo.setData("example", example);
    demo.setHideMode(example.getHideMode());
    demo.add(w);

    add(demo, "Demo");

    if (example.isClosable()) {
      sourceContainer = new SimpleContainer();
      add(sourceContainer, "Source");
      
      addSelectionHandler(new SelectionHandler<Widget>() {
        
        @Override
        public void onSelection(SelectionEvent<Widget> event) {
          if (event.getSelectedItem() == sourceContainer && sourceContainer.getWidgetCount() == 0) {
            sourceContainer.add(makeSourceContainer(example));
          }
        }
      });
    }
  }

  private BorderLayoutContainer makeSourceContainer(final Example example) {
    final Frame f = new Frame();
    f.addStyleName("x-noshim");
    f.getElement().setPropertyInt("frameBorder", 0);
    f.setSize("100%", "100%");

    BorderLayoutContainer sourceContainer = new BorderLayoutContainer();
    sourceContainer.addStyleName("source");
    sourceContainer.setHeight("800px");

    ContentPanel west = new ContentPanel();
    west.getHeader().getElement().getStyle().setProperty("backgroundImage", "none");
    west.getHeader().getElement().getStyle().setProperty("borderTop", "1px solid " + ThemeStyles.getStyle().borderColor());

    BorderLayoutData westData = new BorderLayoutData(230);
    westData.setMargins(new Margins(5, 0, 5, 5));
    westData.setCollapsible(true);
    westData.setSplit(true);
    westData.setCollapseMini(true);
    westData.setCollapseHidden(true);
    west.setHeadingText("Select File");

    final TreeStore<Source> sources = new TreeStore<Source>(properties.key());
    sources.addSubTree(0, example.getSources());

    Tree<Source, String> tree = new Tree<Source, String>(sources, properties.nameLabel()) {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        Source item = example.getSources().get(0).getChildren().get(0);
        getSelectionModel().select(item, false);
      }
    };

    tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    tree.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Source>() {
      @Override
      public void onSelectionChanged(SelectionChangedEvent<Source> event) {
        List<Source> sels = event.getSelection();
        if (sels.size() > 0) {
          Source m = sels.get(0);
          center.setHeadingText(m.getName());
          if (m.getType() != FileType.FOLDER) {
            f.setUrl(m.getUrl());
          }
        }
      }
    });

    tree.setIconProvider(new IconProvider<Source>() {
      @Override
      public ImageResource getIcon(Source model) {
        switch (model.getType()) {
          case CSS:
            return ExampleImages.INSTANCE.css();
          case XML:
            return ExampleImages.INSTANCE.xml();
          case JSON:
            return ExampleImages.INSTANCE.json();
          case FOLDER:
            return ExampleImages.INSTANCE.folder();
          case HTML:
            return ExampleImages.INSTANCE.html();
          case JAVA:
          default:
            return ExampleImages.INSTANCE.java();
        }
      }
    });
    tree.setAutoExpand(true);
    west.add(tree);

    sourceContainer.setWestWidget(west, westData);

    center = new ContentPanel();
    center.getHeader().getElement().getStyle().setProperty("backgroundImage", "none");
    center.getHeader().getElement().getStyle().setProperty("borderTop", "1px solid " + ThemeStyles.getStyle().borderColor());

    MarginData centerData = new MarginData();
    centerData.setMargins(new Margins(5));
    center.setHeadingText("Source Code");

    center.add(f);

    sourceContainer.setCenterWidget(center, centerData);

    return sourceContainer;
  }

}
