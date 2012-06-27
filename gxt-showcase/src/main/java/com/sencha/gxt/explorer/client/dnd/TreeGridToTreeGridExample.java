/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.dnd;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.TreeGridDragSource;
import com.sencha.gxt.dnd.core.client.TreeGridDropTarget;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.BaseDto;
import com.sencha.gxt.examples.resources.client.model.BaseDtoProperties;
import com.sencha.gxt.examples.resources.client.model.FolderDto;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

@Detail(name = "TreeGrid to TreeGrid", category = "Drag and Drop", icon = "treegridtotreegrid", classes = {BaseDtoProperties.class})
public class TreeGridToTreeGridExample implements EntryPoint, IsWidget {

  @Override
  public Widget asWidget() {
    FramedPanel cp = new FramedPanel();
    cp.setHeadingText("TreeGrid to TreeGrid Example");
    cp.setPixelSize(500, 500);
    cp.addStyleName("margin-10");

    VerticalLayoutContainer vp = new VerticalLayoutContainer();

    BaseDtoProperties props = GWT.create(BaseDtoProperties.class);

    FolderDto folder = TestData.getMusicRootFolder();
    TreeStore<BaseDto> topStore = new TreeStore<BaseDto>(BaseDtoProperties.key);
    topStore.addSubTree(0, folder.getChildren());

    TreeStore<BaseDto> bottomStore = new TreeStore<BaseDto>(BaseDtoProperties.key);

    TreeGrid<BaseDto> topTree = createTreeGrid(props, topStore);

    new TreeGridDragSource<BaseDto>(topTree);
    final TreeGridDropTarget<BaseDto> topTarget = new TreeGridDropTarget<BaseDto>(topTree);

    TreeGrid<BaseDto> bottomTree = createTreeGrid(props, bottomStore);

    new TreeGridDragSource<BaseDto>(bottomTree);
    final TreeGridDropTarget<BaseDto> bottomTarget = new TreeGridDropTarget<BaseDto>(bottomTree);

    ToolBar toolbar = new ToolBar();
    toolbar.setBorders(true);

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
        topTarget.setFeedback(event.getValue());
        bottomTarget.setFeedback(event.getValue());
      }
    });
    toolbar.add(type);
    type.setValue(Feedback.APPEND);
    topTarget.setFeedback(Feedback.APPEND);
    bottomTarget.setFeedback(Feedback.APPEND);

    vp.add(toolbar, new VerticalLayoutData(1, -1));
    vp.add(topTree, new VerticalLayoutData(1, .5, new Margins(5, 0, 0, 0)));
    vp.add(bottomTree, new VerticalLayoutData(1, .5, new Margins(5, 0, 0, 0)));
    cp.setWidget(vp);

    return cp;
  }

  protected TreeGrid<BaseDto> createTreeGrid(BaseDtoProperties props, TreeStore<BaseDto> store) {
    ColumnConfig<BaseDto, String> cc1 = new ColumnConfig<BaseDto, String>(props.name());
    cc1.setHeader("Name");

    ColumnConfig<BaseDto, String> cc2 = new ColumnConfig<BaseDto, String>(BaseDtoProperties.author);
    cc2.setHeader("Author");

    ColumnConfig<BaseDto, String> cc3 = new ColumnConfig<BaseDto, String>(BaseDtoProperties.genre);
    cc3.setHeader("Genre");
    @SuppressWarnings("unchecked")
    List<ColumnConfig<BaseDto, ?>> columns = Arrays.<ColumnConfig<BaseDto, ?>> asList(cc1, cc2, cc3);

    TreeGrid<BaseDto> sourceTree = new TreeGrid<BaseDto>(store, new ColumnModel<BaseDto>(columns), cc1);
    sourceTree.getView().setAutoExpandColumn(cc1);
    sourceTree.setBorders(true);
    return sourceTree;
  }

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

}
