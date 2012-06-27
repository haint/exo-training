/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktop.client.widget;

import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.widget.core.client.button.TextButton;

/**
 * A desktop shortcut.
 */
public class Shortcut extends TextButton {

  /**
   * Creates a new shortcut.
   */
  public Shortcut() {
    this(null, "");
  }

  /**
   * Creates a new shortcut.
   * 
   * @param id the shortcut id
   * @param text the shortcut text
   */
  public Shortcut(String id, String text) {
    super(new ShortcutCell());
    setIconAlign(IconAlign.TOP);
    setId(id);
    setText(text);
  }

}
