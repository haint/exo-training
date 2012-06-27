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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

@Detail(name = "ToolTips", icon = "tooltips", category = "Misc", files = "template.html")
public class ToolTipsExample implements IsWidget, EntryPoint {
  public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>, XTemplates {

    @Override
    @XTemplate(source = "template.html")
    public SafeHtml renderToolTip(Object data);

  }

  private Renderer renderer = GWT.create(Renderer.class);

  public Widget asWidget() {
    HorizontalPanel hp = new HorizontalPanel();
    hp.setSpacing(5);

    TextButton btn = new TextButton("Basic");
    btn.setToolTipConfig(new ToolTipConfig("Information", "Prints the current document"));
    hp.add(btn);

    btn = new TextButton("Closable");
    ToolTipConfig config = new ToolTipConfig();
    config.setTitleHtml("Information");
    config.setBodyHtml("Prints the current document");
    config.setCloseable(true);
    btn.setToolTipConfig(config);
    hp.add(btn);

    btn = new TextButton("Mouse Tracking");
    config = new ToolTipConfig();
    config.setTitleHtml("Information");
    config.setBodyHtml("Prints the current document");
    config.setTrackMouse(true);
    btn.setToolTipConfig(config);
    hp.add(btn);

    btn = new TextButton("Anchor");
    config = new ToolTipConfig();
    config.setTitleHtml("Information");
    config.setBodyHtml("Prints the current document");
    config.setMouseOffset(new int[] {0, 0});
    config.setAnchor(Side.LEFT);
    btn.setToolTipConfig(config);
    hp.add(btn);

    btn = new TextButton("Custom");
    config = new ToolTipConfig();
    config.setBodyHtml("Prints the current document");
    config.setTitleHtml("Template Tip");
    config.setMouseOffset(new int[] {0, 0});
    config.setAnchor(Side.LEFT);
    config.setRenderer(renderer);
    config.setCloseable(true);
    config.setMaxWidth(415);
    btn.setToolTipConfig(config);
    hp.add(btn);
    return hp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
