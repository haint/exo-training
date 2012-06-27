/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.explorer.client.thumbs.ExampleThumbs;

/**
 * Model object to represent a GXT example - contains the example widget itself,
 * as well as a name, icon, and some display details about the example.
 * 
 */
public class Example extends NamedModel {

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  public static @interface Detail {
    /**
     * Visible name of the example
     */
    String name();

    /**
     * Category this example belongs in
     */
    String category();

    /**
     * Whether or not to use a FitLayout when drawing the example. Defaults to
     * false.
     */
    boolean fit() default false;

    /**
     * Other classes, besides the type annotated with {@literal @}Detail to have
     * their source included
     */
    Class<?>[] classes() default {};

    /**
     * Other files useful for demonstrating the example
     */
    String[] files() default {};

    /**
     * Name of the icon method to use in the clientbundle file. See
     * {@link #iconClientBundle()} for the class this method will be called on
     */
    String icon();

    /**
     * ClientBundle type to use to create the icon for this example
     */
    Class<?> iconClientBundle() default ExampleThumbs.class;
  }

  public static ModelKeyProvider<Example> KP = new ModelKeyProvider<Example>() {
    @Override
    public String getKey(Example item) {
      return item.getName();
    }
  };

  public IsWidget example;
  private ImageResource icon;
  private boolean closable = true;
  private HideMode hideMode = HideMode.DISPLAY;
  private boolean fill;
  private List<Source> sources = new ArrayList<Source>();
  
  public Example(String name, ImageResource thumb, IsWidget example, boolean fill) {
    super(name);
    this.icon = thumb;
    this.example = example;
    this.fill = fill;
  }

  public IsWidget getExample() {
    return example;
  }

  public HideMode getHideMode() {
    return hideMode;
  }

  public ImageResource getIcon() {
    return icon;
  }

  public String getId() {
    if (getName().equals("% Columns")) {
      return "percentcolumns";
    }
    return getName().replaceAll(" ", "").toLowerCase();
  }

  public SafeHtml getImage() {
    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    builder.appendHtmlConstant(AbstractImagePrototype.create(icon).getHTML());
    return builder.toSafeHtml();
  }
  
  public void setImage(SafeHtml image) {
    
  }

  public boolean isClosable() {
    return closable;
  }

  public boolean isFill() {
    return fill;
  }

  public void setClosable(boolean closable) {
    this.closable = closable;
  }

  public void setFill(boolean fill) {
    this.fill = fill;
  }

  public void setHideMode(HideMode hideMode) {
    this.hideMode = hideMode;
  }

  public void setIcon(ImageResource icon) {
    this.icon = icon;
  }

  public void setExample(IsWidget example) {
    this.example = example;
  }

  public List<Source> getSources() {
    return sources;
  }

}
