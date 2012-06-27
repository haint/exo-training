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
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent.DndDragStartHandler;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.examples.resources.client.images.ExampleImages;
import com.sencha.gxt.examples.resources.client.model.BaseDtoProperties;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.explorer.client.model.ExampleModel;
import com.sencha.gxt.explorer.client.model.NamedModel;
import com.sencha.gxt.explorer.client.model.NamedModel.NamedModelProperties;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tree.Tree;

@Detail(name = "Reordering Tree", category = "Drag and Drop", icon = "reorderingtree", classes = {BaseDtoProperties.class})
public class ReorderingTreeExample implements EntryPoint, IsWidget {

  @Override
  public Widget asWidget() {
    ExampleModel examples = GWT.create(ExampleModel.class);
    NamedModelProperties props = GWT.create(NamedModelProperties.class);

    final TreeStore<NamedModel> sourceStore = new TreeStore<NamedModel>(props.kp());
    sourceStore.addSubTree(0, examples.getModels());

    final Tree<NamedModel, String> sourceTree = new Tree<NamedModel, String>(sourceStore, props.name());
    sourceTree.getStyle().setLeafIcon(ExampleImages.INSTANCE.text());
    sourceTree.setBorders(true);
    sourceTree.getElement().getStyle().setBackgroundColor("white");

    TreeDragSource<NamedModel> source = new TreeDragSource<NamedModel>(sourceTree);
    source.addDragStartHandler(new DndDragStartHandler() {

      @Override
      public void onDragStart(DndDragStartEvent event) {
        NamedModel sel = sourceTree.getSelectionModel().getSelectedItem();
        if (sel != null && sel == sourceStore.getRootItems().get(0)) {
          event.setCancelled(true);
          event.getStatusProxy().setStatus(false);
          Info.display("Drag Prevented",
              "See the source code for an example of how to cancel a drag operation under program control.");
        }
      }
    });

    TreeDropTarget<NamedModel> target = new TreeDropTarget<NamedModel>(sourceTree);
    target.setAllowSelfAsSource(true);
    target.setFeedback(Feedback.BOTH);

    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("Reordering Tree");
    panel.addStyleName("margin-10");
    panel.setPixelSize(320, 420);
    panel.setWidget(sourceTree);

    return panel;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

}
