/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.dnd;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.examples.resources.client.model.BaseDtoProperties;
import com.sencha.gxt.explorer.client.model.Category;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.explorer.client.model.ExampleModel;
import com.sencha.gxt.explorer.client.model.NamedModel;
import com.sencha.gxt.explorer.client.model.NamedModel.NamedModelProperties;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree;

@Detail(name = "Tree to Tree", category = "Drag and Drop", icon = "treetotree", classes = {BaseDtoProperties.class})
public class TreeToTreeExample implements EntryPoint, IsWidget {

  @Override
  public Widget asWidget() {
    ContentPanel cp = new ContentPanel();
    cp.setHeadingHtml("Tree to Tree Example");
    cp.setPixelSize(600, 400);
    cp.addStyleName("margin-10");

    VerticalLayoutContainer outer = new VerticalLayoutContainer();

    HorizontalLayoutContainer vp = new HorizontalLayoutContainer();

    ExampleModel examples = GWT.create(ExampleModel.class);
    NamedModelProperties props = GWT.create(NamedModelProperties.class);
    TreeStore<NamedModel> sourceStore = new TreeStore<NamedModel>(props.kp());
    sourceStore.addSubTree(0, examples.getModels());

    Tree<NamedModel, String> sourceTree = new Tree<NamedModel, String>(sourceStore, props.name());
    sourceTree.getStyle().setLeafIcon(ExampleImages.INSTANCE.text());
    
    new TreeDragSource<NamedModel>(sourceTree);
    
    TreeStore<NamedModel> targetStore = new TreeStore<NamedModel>(props.kp());
    targetStore.add(new Category("My Files"));

    Tree<NamedModel, String> targetTree = new Tree<NamedModel, String>(targetStore, props.name()) {
      protected boolean hasChildren(NamedModel model) {
        return (model instanceof Category) || super.hasChildren(model);
      }
    };
    targetTree.getStyle().setLeafIcon(ExampleImages.INSTANCE.text());
    
    final TreeDropTarget<NamedModel> target = new TreeDropTarget<NamedModel>(targetTree);

    vp.add(sourceTree, new HorizontalLayoutData(.5, 1));
    vp.add(targetTree, new HorizontalLayoutData(.5, 1));

    ToolBar toolbar = new ToolBar();
    toolbar.add(new LabelToolItem("Feedback: "));
    SimpleComboBox<Feedback> type = new SimpleComboBox<Feedback>(new LabelProvider<Feedback>() {
      @Override
      public String getLabel(Feedback item) {
        return item.toString().substring(0, 1) + item.toString().substring(1).toLowerCase();
      }
    });
    type.setTriggerAction(TriggerAction.ALL);
    type.setEditable(false);
    type.add(Feedback.APPEND);
    type.add(Feedback.INSERT);
    type.add(Feedback.BOTH);
    type.addValueChangeHandler(new ValueChangeHandler<Feedback>() {
      @Override
      public void onValueChange(ValueChangeEvent<Feedback> event) {
        target.setFeedback(event.getValue());
      }
    });
    toolbar.add(type);
    type.setValue(Feedback.APPEND);
    target.setFeedback(Feedback.APPEND);

    outer.add(toolbar, new VerticalLayoutData(1, -1));
    outer.add(vp, new VerticalLayoutData(1, 1));
    cp.setWidget(outer);

    return cp;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

}
