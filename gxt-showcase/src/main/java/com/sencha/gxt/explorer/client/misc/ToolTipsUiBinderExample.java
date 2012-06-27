/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.misc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

@Detail(name = "ToolTips (UiBinder)", icon = "tooltips", category = "Misc", files ={"ToolTipsUiBinderExample.ui.xml", "template.html"})
public class ToolTipsUiBinderExample implements IsWidget, EntryPoint {

  public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>, XTemplates {
    @Override
    @XTemplate(source = "template.html")
    public SafeHtml renderToolTip(Object data);
  }

  interface MyUiBinder extends UiBinder<Widget, ToolTipsUiBinderExample> {
  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  public Widget asWidget() {
    return uiBinder.createAndBindUi(this);
  }

  public void onModuleLoad() {
      RootPanel.get().add(asWidget());
  }

}
