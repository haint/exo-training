/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.layout;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.examples.resources.client.model.NameImageModel;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.tree.Tree;

@Detail(name = "AccordionLayout (UiBinder)", icon = "accordionlayout", category = "Layouts", files = "AccordionUiBinderExample.ui.xml")
public class AccordionUiBinderExample implements IsWidget, EntryPoint {

  interface MyUiBinder extends UiBinder<Widget, AccordionUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField(provided = true)
  String dummyTextShort = TestData.DUMMY_TEXT_SHORT;

  @UiField
  ContentPanel panel;

  @UiField
  AccordionLayoutContainer con;

  @UiField
  Tree<NameImageModel, String> tree;

  public Widget asWidget() {
    if (panel == null) {
      uiBinder.createAndBindUi(this);

      panel.getHeader().setIcon(ExampleImages.INSTANCE.accordion());
      con.setActiveWidget((ContentPanel) con.getWidget(0));
      tree.expandAll();
    }

    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  @UiFactory
  public ContentPanel createContentPanel(ContentPanelAppearance appearance) {
    return new ContentPanel(appearance);
  }

  @UiFactory
  public IconProvider<NameImageModel> createIconProvider() {
    return new IconProvider<NameImageModel>() {
      public ImageResource getIcon(NameImageModel model) {
        if (null == model.getImage()) {
          return null;
        } else if ("user-girl" == model.getImage()) {
          return ExampleImages.INSTANCE.userFemale();
        } else if ("user-kid" == model.getImage()) {
          return ExampleImages.INSTANCE.userKid();
        } else {
          return ExampleImages.INSTANCE.user();
        }
      }
    };
  }

  @UiFactory
  public TreeStore<NameImageModel> createTreeStore() {
    TreeStore<NameImageModel> store = new TreeStore<NameImageModel>(NameImageModel.KP);

    NameImageModel m = newItem("Family", null);
    store.add(m);

    store.add(m, newItem("Darrell", "user"));
    store.add(m, newItem("Maro", "user-girl"));
    store.add(m, newItem("Lia", "user-kid"));
    store.add(m, newItem("Alec", "user-kid"));
    store.add(m, newItem("Andrew", "user-kid"));

    m = newItem("Friends", null);
    store.add(m);

    store.add(m, newItem("Bob", "user"));
    store.add(m, newItem("Mary", "user-girl"));
    store.add(m, newItem("Sally", "user-girl"));
    store.add(m, newItem("Jack", "user"));

    return store;
  }

  @UiFactory
  public ValueProvider<NameImageModel, String> createValueProvider() {
    return new ValueProvider<NameImageModel, String>() {

      @Override
      public String getValue(NameImageModel object) {
        return object.getName();
      }

      @Override
      public void setValue(NameImageModel object, String value) {
      }

      @Override
      public String getPath() {
        return "name";
      }
    };
  }

  private NameImageModel newItem(String text, String iconStyle) {
    return new NameImageModel(text, iconStyle);
  }
}
