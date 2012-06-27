/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.service;import com.google.gwt.user.client.ui.HasWidgets;import com.sencha.gxt.desktopapp.client.LoginPresenter;import com.sencha.gxt.desktopapp.client.servicebus.ServiceProvider;public class LoginServiceProvider implements ServiceProvider<LoginServiceRequest> {  private LoginPresenter loginPresenter;  private HasWidgets hasWidgets;  public LoginServiceProvider(LoginPresenter loginPresenter, HasWidgets hasWidgets) {    this.loginPresenter = loginPresenter;    this.hasWidgets = hasWidgets;  }  @Override  public void onServiceRequest(LoginServiceRequest loginServiceRequest) {    loginPresenter.go(hasWidgets);  }}