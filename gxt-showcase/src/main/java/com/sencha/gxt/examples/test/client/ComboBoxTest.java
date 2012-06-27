/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.test.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.examples.resources.client.TestData;
import com.sencha.gxt.examples.resources.client.model.State;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.BlurEvent.BlurHandler;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent.FocusHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ComboBoxTest implements EntryPoint {

  interface StateProperties extends PropertyAccess<State> {
    ModelKeyProvider<State> abbr();

    LabelProvider<State> name();
  }

  interface Template extends XTemplates {
    @XTemplate("<div>yo {state.name}</div>")
    SafeHtml render(State state);
  }

  @Override
  public void onModuleLoad() {
    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);

    StateProperties props = GWT.create(StateProperties.class);
    ListStore<State> states = new ListStore<State>(props.abbr());
    states.addAll(TestData.getStates());

    final Template t = GWT.create(Template.class);

    ListView<State, State> view = new ListView<State, State>(states, new IdentityValueProvider<State>());

    view.setCell(new AbstractCell<State>() {
      @Override
      public void render(Cell.Context context, State value, SafeHtmlBuilder sb) {
        sb.append(t.render(value));
      }
    });

    final ComboBox<State> combo = new ComboBox<State>(states, props.name(), view);
    combo.setEmptyText("Select a state...");
    combo.setEditable(false);
//    combo.setForceSelection(true);
    combo.setWidth(150);
//    combo.setReadOnly(true);
//    combo.setTypeAhead(true);
    combo.setTriggerAction(TriggerAction.ALL);
    combo.addValueChangeHandler(new ValueChangeHandler<State>() {

      @Override
      public void onValueChange(ValueChangeEvent<State> event) {
        State s = event.getValue();
        System.out.println(s);
        
//        System.out.println("e: " + event.getValue());
//        System.out.println("c: " + combo.getValue());
      }
    });
    vp.add(combo);
    
    
    Timer tt = new Timer() {
      
      @Override
      public void run() {
        System.out.println("run");
        combo.getListView().getSelectionModel().select(2, false);
      }
    };
    tt.schedule(3000);

    class Handler implements FocusHandler, BlurHandler {

      @Override
      public void onBlur(BlurEvent event) {
        // System.out.println("blur");
      }

      @Override
      public void onFocus(FocusEvent event) {
        // System.out.println("focus");
      }

    }
    Handler h = new Handler();
    combo.addFocusHandler(h);
    combo.addBlurHandler(h);

    TextField text = new TextField();
    text.setWidth(150);
    vp.add(text);

    TextArea area = new TextArea();
    area.setPixelSize(150, 100);
    vp.add(area);

    Slider s = new Slider();
    s.setWidth(150);
    vp.add(s);

    RootPanel.get().add(vp);
  }

}
