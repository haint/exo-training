/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktop.client.theme.base.taskbutton;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.frame.TableFrame.TableFrameResources;
import com.sencha.gxt.theme.base.client.frame.TableFrame.TableFrameStyle;

public interface TaskButtonTableFrameResources extends TableFrameResources, ClientBundle {
  /**
   * Provides unique scoping for task button styles.
   */
  public interface TaskButtonTableFrameStyle extends TableFrameStyle {
  }

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource background();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource backgroundOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource backgroundPressedBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  @Override
  ImageResource bottomBorder();

  @Override
  ImageResource bottomLeftBorder();

  ImageResource bottomLeftOverBorder();

  ImageResource bottomLeftPressedBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource bottomOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource bottomPressedBorder();

  @Override
  ImageResource bottomRightBorder();

  ImageResource bottomRightOverBorder();

  ImageResource bottomRightPressedBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  @Override
  ImageResource leftBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  ImageResource leftOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  ImageResource leftPressedBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  @Override
  ImageResource rightBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  ImageResource rightOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  ImageResource rightPressedBorder();

  @Source({"com/sencha/gxt/theme/base/client/frame/TableFrame.css", "TaskButtonTableFrame.css"})
  @Override
  TaskButtonTableFrameStyle style();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  @Override
  ImageResource topBorder();

  @Override
  ImageResource topLeftBorder();

  ImageResource topLeftOverBorder();

  ImageResource topLeftPressedBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource topOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource topPressedBorder();

  @Override
  ImageResource topRightBorder();

  ImageResource topRightOverBorder();

  ImageResource topRightPressedBorder();

}
