/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.portal.webui.test;

import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;

import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.application.portlet.PortletRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormStringInput;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 20, 2011  
 */

@ComponentConfig(lifecycle = UIFormLifecycle.class, template = "system:/groovy/webui/form/UIForm.gtmpl",
                 events = {
  @EventConfig(listeners = UITestConfig.SaveActionListener.class),
  @EventConfig(listeners = UITestConfig.CancelActionListener.class)
})
public class UITestConfig extends UIForm {
  
  final public static String TEXT_STRING_INPUT = "UITestPortletTextStringInput" ;

  public UITestConfig() {
    PortletRequestContext portletRequestContext = WebuiRequestContext.getCurrentInstance() ;
    PortletPreferences preferences = portletRequestContext.getRequest().getPreferences() ;
    String text = preferences.getValue(UIWebuiPortlet.TEXT_PREFERENCE, null) ;
    addChild(new UIFormStringInput(TEXT_STRING_INPUT, text)) ;
  }
  
  public static class SaveActionListener extends EventListener<UITestConfig> {
    public void execute(Event<UITestConfig> event) throws Exception {
      UITestConfig config = event.getSource() ;
      UIFormStringInput stringInput = config.getUIStringInput(TEXT_STRING_INPUT) ;
      PortletRequestContext context = WebuiRequestContext.getCurrentInstance() ;
      PortletPreferences preferences = context.getRequest().getPreferences() ;
      preferences.setValue(UIWebuiPortlet.TEXT_PREFERENCE, stringInput.getValue()) ;
      preferences.store() ;
      context = (PortletRequestContext) event.getRequestContext() ;
      context.setApplicationMode(PortletMode.VIEW) ;
    }
  }
  
  public static class CancelActionListener extends EventListener<UITestConfig> {
    public void execute(Event<UITestConfig> event) throws Exception {
      PortletRequestContext context = (PortletRequestContext) event.getRequestContext() ;
      context.setApplicationMode(PortletMode.VIEW) ;
    }
  }
}
