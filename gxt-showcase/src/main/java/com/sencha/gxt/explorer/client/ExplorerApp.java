/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.sencha.gxt.explorer.client.app.mvp.ExplorerPlaceHistoryMapper;
import com.sencha.gxt.explorer.client.app.place.ExamplePlace;
import com.sencha.gxt.explorer.client.app.ui.ExampleDetailView;
import com.sencha.gxt.explorer.client.app.ui.ExampleListView;
import com.sencha.gxt.explorer.client.app.ui.OverviewExample;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.explorer.client.model.ExampleModel;

public class ExplorerApp implements ExampleListView.Presenter, ExampleDetailView.Presenter {

  private static final Logger log = Logger.getLogger(ExplorerApp.class.getName());
  public static final String OVERVIEW = "Overview";
  @Inject
  private EventBus eventBus;

  @Inject
  private PlaceController placeController;

  @Inject
  private ActivityMapper activityMapper;

  @Inject
  private ExplorerShell shell;

  @Inject
  private ExampleListView listView;

  @Inject
  private ExampleDetailView detailView;

  @Inject
  private ExampleModel exampleModel;
  
  public void run() {
    init();
  }

  @SuppressWarnings("deprecation")
  private void init() {
    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
      public void onUncaughtException(Throwable e) {
        Window.alert("Error: " + e.getMessage());
        log.log(Level.SEVERE, e.getMessage(), e);
        e.printStackTrace();
      }
    });

    // Start ActivityManager for the main widget with our ActivityMapper
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(shell);

    // Start PlaceHistoryHandler with our PlaceHistoryMapper
    ExplorerPlaceHistoryMapper historyMapper = GWT.create(ExplorerPlaceHistoryMapper.class);
    Historian historian = (Historian) GWT.create(DefaultHistorian.class);
    final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper, historian);
    historyHandler.register(placeController, eventBus, Place.NOWHERE);

    listView.setPresenter(this);
    detailView.setPresenter(this);

    eventBus.addHandler(PlaceChangeEvent.TYPE, listView);
//    eventBus.addHandler(PlaceChangeEvent.TYPE, detailView);

    Place newPlace = handleHistoryToken(historyMapper, historian.getToken());

    Example example = exampleModel.findExample(OVERVIEW.toLowerCase());
    OverviewExample oExample = (OverviewExample)example.getExample();
    oExample.setPlaceController(placeController);
    oExample.loadData(exampleModel);
    detailView.showExample(example);
    
    RootPanel.get().add(shell);

    // Goes to place represented on URL or default place
//    historyHandler.handleCurrentHistory();
    placeController.goTo(newPlace);


  }
  private Place handleHistoryToken(PlaceHistoryMapper mapper, String token) {

    Place newPlace = null;

    if ("".equals(token)) {
      token = "ExamplePlace:" + OVERVIEW.toLowerCase();
    }

    if (newPlace == null) {
      newPlace = mapper.getPlace(token);
    }
    return newPlace;

  }
  @Override
  public void selectExample(Example ex) {
    placeController.goTo(new ExamplePlace(ex.getId()));
  }
}
