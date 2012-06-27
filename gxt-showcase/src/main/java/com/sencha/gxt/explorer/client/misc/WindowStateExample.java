/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.misc;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.util.Rectangle;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.state.client.AbstractStateHandler;
import com.sencha.gxt.state.client.BeforeRestoreStateEvent;
import com.sencha.gxt.state.client.BeforeRestoreStateEvent.BeforeRestoreStateHandler;
import com.sencha.gxt.state.client.CookieProvider;
import com.sencha.gxt.state.client.DefaultStateAutoBeanFactory;
import com.sencha.gxt.state.client.RestoreStateEvent;
import com.sencha.gxt.state.client.RestoreStateEvent.RestoreStateHandler;
import com.sencha.gxt.state.client.StateManager;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

@Detail(name = "Window State", icon = "helloworld", category = "Misc")
public class WindowStateExample implements IsWidget, EntryPoint {

  /**
   * We must extend <code>DefaultStateAutoBeanFactory</code> and then specify
   * via the GWT module XML file this class.
   * 
   * <code>
   *  &lt;set-configuration-property name="GXT.state.autoBeanFactory" 
   *  value="com.sencha.gxt.explorer.client.misc.StateExample.ExampleAutoBeanFactory" />
   * </code>
   */
  public interface ExampleAutoBeanFactory extends DefaultStateAutoBeanFactory {
    AutoBean<WindowsState> windowsState();

    AutoBean<WindowState> windowState();
  }

  public static interface WindowsState {
    Map<String, WindowState> getWindowState();

    void setWindowState(Map<String, WindowState> windowState);
  }

  public class StateExampleHandler extends AbstractStateHandler<WindowsState, WindowStateExample> {

    public StateExampleHandler(WindowStateExample example, String key) {
      super(WindowsState.class, example, key);
    }

    @Override
    public void applyState() {
      Map<String, WindowState> windows = getState().getWindowState();
      if (windows == null) {
        windows = new HashMap<String, WindowState>();
        getState().setWindowState(windows);
      }
    }

  }

  public static interface WindowState {
    int getHeight();

    int getWidth();

    int getX();

    int getY();

    void setHeight(int height);

    void setWidth(int width);

    void setX(int x);

    void setY(int y);
  }

  private class Handler implements BeforeHideHandler, BeforeShowHandler,
      BeforeRestoreStateHandler<WindowsState, WindowStateExample>,
      RestoreStateHandler<WindowsState, WindowStateExample> {

    @Override
    public void onBeforeHide(BeforeHideEvent event) {
      onBeforeHideWindow((Window) event.getSource());
    }

    @Override
    public void onBeforeShow(BeforeShowEvent event) {
      onBeforeShowWindow((Window) event.getSource());
    }

    @Override
    public void onBeforeRestoreState(BeforeRestoreStateEvent<WindowsState, WindowStateExample> event) {
      Info.display("State Change", "Before restore state");
    }

    @Override
    public void onRestoreState(RestoreStateEvent<WindowsState, WindowStateExample> event) {
      Info.display("State Change", "Restore state");
    }

  }

  private ExampleAutoBeanFactory factory = GWT.create(ExampleAutoBeanFactory.class);
  private StateExampleHandler stateHandler;
  private Handler handler = new Handler();

  @Override
  public Widget asWidget() {
    stateHandler = new StateExampleHandler(this, "stateExample");
    stateHandler.addBeforeRestoreStateHandler(handler);
    stateHandler.loadState();

    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);

    vp.add(new HTML(
        "<span style='font-size: 12px'>This examples saves the windows size and position when closed. State is restored when opening window in a new session.</span>"));

    ButtonBar bar = new ButtonBar();

    for (int i = 0; i < 5; i++) {
      final Window w = createWindow(i + 1);
      bar.add(new TextButton("Window " + (i + 1), new SelectHandler() {

        @Override
        public void onSelect(SelectEvent event) {
          w.show();
        }
      }));
    }

    vp.add(bar);

    return vp;
  }

  @Override
  public void onModuleLoad() {
    StateManager.get().setProvider(new CookieProvider("/", null, null, GXT.isSecure()));

    RootPanel.get().add(asWidget());
  }

  private Window createWindow(int index) {
    Window w = new Window();
    w.setHeadingText("Window " + index);
    w.addBeforeHideHandler(handler);
    w.addBeforeShowHandler(handler);
    w.setStateId("stateExampleWindow" + index);

    return w;
  }

  private void onBeforeHideWindow(Window window) {
    String stateId = window.getStateId();

    WindowsState state = stateHandler.getState();
    Map<String, WindowState> windows = state.getWindowState();
    WindowState windowState = windows.get(stateId);
    if (windowState == null) {
      windowState = factory.windowState().as();
      windows.put(stateId, windowState);
    }

    Rectangle rect = window.getElement().getBounds();

    windowState.setHeight(rect.getHeight());
    windowState.setWidth(rect.getWidth());
    windowState.setX(rect.getX());
    windowState.setY(rect.getY());

    stateHandler.saveState();
  }

  private void onBeforeShowWindow(Window window) {
    if (stateHandler.getState() != null && stateHandler.getState().getWindowState() != null) {
      String stateId = window.getStateId();
      WindowState state = stateHandler.getState().getWindowState().get(stateId);
      if (state != null) {
        window.setPixelSize(state.getWidth(), state.getHeight());
        window.setPagePosition(state.getX(), state.getY());
      }
    }
  }

}
