/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.button;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.examples.resources.client.Resources;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.FramedPanel.FramedPanelAppearance;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;
import com.sencha.gxt.widget.core.client.button.SplitButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

@Detail(name = "Buttons", icon = "buttons", category = "Button", files = "ButtonExample.css")
public class ButtonExample implements IsWidget {

  public interface ExampleStyle extends CssResource {
    String header();

    String section();
  }

  public interface ExampleResources extends ClientBundle {

    @Source("ButtonExample.css")
    ExampleStyle style();

  }

  enum Category {
    NORMAL("Normal Buttons", "Normal"), MENU("Menu Buttons", "Menu"), MENUBOTTOM("Menu (Arrow on bottom)",
        "Menu Bottom"), SPLIT("Split Buttons", "Split"), SPLITBOTTOM("Split Buttons (Arrow on bottom)", "Split Bottom"), TOGGLE(
        "Toggle Buttons", "Toggle");

    private String text;
    private String desc;

    Category(String text, String desc) {
      this.text = text;
      this.desc = desc;
    }

    String getText() {
      return text;
    }
  }

  enum Type {

    BOTTOM("Icon and Text (bottom)"), ICON("Icon Only"), LEFT("Icon and Text (left)"), RIGHT("Icon and Text (right)"), TEXT(
        "Text Only"), TOP("Icon and Text (top)");

    private String text;

    Type(String text) {
      this.text = text;
    }

    String getText() {
      return text;
    }
  }

  private ContentPanel cp;
  private CardLayoutContainer con;
  private ExampleStyle style;

  private Map<Category, FlowLayoutContainer> created = new HashMap<Category, FlowLayoutContainer>();

  private FlowLayoutContainer createButtons(Category cat) {
    VerticalPanel vp = new VerticalPanel();
    vp.setSpacing(10);
    vp.setWidth("400px");

    for (Type type : Type.values()) {
      vp.add(format(type.getText()));

      HorizontalPanel hp = new HorizontalPanel();
      hp.setSpacing(5);

      CellButtonBase<?> small = createButton(cat, type);
      CellButtonBase<?> medium = createButton(cat, type);
      CellButtonBase<?> large = createButton(cat, type);

      configureButton(small, type, ButtonScale.SMALL);
      configureButton(medium, type, ButtonScale.MEDIUM);
      configureButton(large, type, ButtonScale.LARGE);

      hp.add(small);
      hp.add(medium);
      hp.add(large);

      vp.add(hp);
    }

    FlowLayoutContainer f = new FlowLayoutContainer();
    f.getScrollSupport().setScrollMode(ScrollMode.AUTO);
    f.add(vp);

    con.add(f);

    return f;
  }

  @Override
  public Widget asWidget() {
    if (cp == null) {
      ExampleResources bundle = GWT.create(ExampleResources.class);

      this.style = bundle.style();
      this.style.ensureInjected();

      cp = new ContentPanel(GWT.<ContentPanelAppearance> create(FramedPanelAppearance.class));
      cp.addStyleName("margin-10");
      cp.setPixelSize(500, 400);
      cp.getBody().getStyle().setBackgroundColor("white");
      cp.getBody().addClassName(ThemeStyles.getStyle().border());

      con = new CardLayoutContainer();
      cp.add(con);

      ToggleGroup group = new ToggleGroup();

      for (Category cat : Category.values()) {
        final ToggleButton btn = new ToggleButton(cat.desc);
        btn.setData("cat", cat);
        btn.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

          @Override
          public void onValueChange(ValueChangeEvent<Boolean> event) {
            if (event.getValue() == true) {
              onClick((Category) btn.getData("cat"));
            }
          }
        });

        group.add(btn);
        cp.addButton(btn);
      }

      ToggleButton normal = (ToggleButton) cp.getButtonBar().getWidget(0);
      normal.setValue(true, true);

    }
    return cp;
  }

  private void onClick(Category cat) {
    FlowLayoutContainer vp = created.get(cat);
    if (vp == null) {
      vp = createButtons(cat);
      created.put(cat, vp);
    }
    con.setActiveWidget(vp);
    cp.setHeadingText(cat.getText());
  }

  private void configureButton(CellButtonBase<?> btn, Type type, ButtonScale scale) {
    btn.setScale(scale);
    switch (type) {

      case LEFT:
        btn.setIconAlign(IconAlign.LEFT);
        break;
      case RIGHT:
        btn.setIconAlign(IconAlign.RIGHT);
        break;
      case BOTTOM:
        btn.setIconAlign(IconAlign.BOTTOM);
        break;
      case TOP:
        btn.setIconAlign(IconAlign.TOP);
        break;
    }

    switch (type) {
      case ICON:
        setIcon(btn, scale);
        break;
      case TEXT:
        btn.setText("Add User");
        break;
      default: {
        setIcon(btn, scale);
        btn.setText("Add User");
      }
    }
  }

  private CellButtonBase<?> createButton(Category cat, Type type) {
    CellButtonBase<?> btn = null;
    switch (cat) {
      case NORMAL:
        btn = new TextButton();
        break;
      case TOGGLE:
        btn = new ToggleButton();
        break;
      case MENU:
        btn = new TextButton();
        btn.setMenu(createMenu());
        break;
      case MENUBOTTOM:
        btn = new TextButton();
        btn.setMenu(createMenu());
        btn.setArrowAlign(ButtonArrowAlign.BOTTOM);
        break;
      case SPLIT:
        btn = new SplitButton();
        btn.setMenu(createMenu());
        break;
      case SPLITBOTTOM:
        btn = new SplitButton();
        btn.setMenu(createMenu());
        btn.setArrowAlign(ButtonArrowAlign.BOTTOM);
        break;
    }
    return btn;
  }

  private Menu createMenu() {
    Menu menu = new Menu();
    menu.add(new MenuItem("Menu Item 1"));
    menu.add(new MenuItem("Menu Item 2"));
    menu.add(new MenuItem("Menu Item 3"));
    return menu;
  }

  private HTML format(String text) {
    HTML html = new HTML(text);
    html.addStyleName(style.section());
    return html;
  }

  private void setIcon(CellButtonBase<?> btn, ButtonScale scale) {
    switch (scale) {
      case SMALL:
        btn.setIcon(Resources.IMAGES.add16());
        break;
      case MEDIUM:
        btn.setIcon(Resources.IMAGES.add24());
        break;

      case LARGE:
        btn.setIcon(Resources.IMAGES.add32());
        break;
    }
  }
}
