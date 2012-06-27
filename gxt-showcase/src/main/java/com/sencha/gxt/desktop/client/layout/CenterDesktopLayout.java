/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktop.client.layout;

import java.util.Random;

import com.google.gwt.user.client.Element;
import com.sencha.gxt.widget.core.client.Window;

public class CenterDesktopLayout extends LimitedDesktopLayout implements DesktopLayout {

  private static final int MINIMUM = 50;
  private static final int VARIANCE = 50;

  private int left;
  private int top;

  private Random random = new Random();

  @Override
  public DesktopLayoutType getDesktopLayoutType() {
    return DesktopLayoutType.CENTER;
  }

  @Override
  public void layoutDesktop(Window requestWindow, RequestType requestType, Element element, Iterable<Window> windows,
      int containerWidth, int containerHeight) {

    if (requestType == RequestType.LAYOUT || requestType == RequestType.RESIZE) {
      left = MINIMUM;
      top = MINIMUM;
    }

    super.layoutDesktop(requestWindow, requestType, element, windows, containerWidth, containerHeight);
  }

  @Override
  protected void layoutWindow(Window window, int containerWidth, int containerHeight, int width, int height) {

    int offset = window.getHeader().getOffsetHeight();

    if (((left + VARIANCE + width) > containerWidth) || ((top + VARIANCE + height) > containerHeight)) {
      left = MINIMUM;
      top = MINIMUM;
    }

    left += offset + random.nextInt(VARIANCE);
    top += offset + random.nextInt(VARIANCE);

    window.setPixelSize(width, height);
    window.setPosition(left, top);
  }

}
