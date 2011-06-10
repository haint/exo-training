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

import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.application.portlet.PortletRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.lifecycle.Lifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 21, 2011  
 */

@ComponentConfig(lifecycle = Lifecycle.class,
                 template = "app:/groovy/webui/TestComponent/UITestComponent.gtmpl",
                 events = {@EventConfig(listeners = UITestComponent.ChangeTextActionListener.class)})
public class UITestComponent extends UIComponent {
  
  
  public static final String TEXT_VARIABLE_NAME = "myText" ;
  
  private String myText ;
  
  public String getMyText() { return myText ; }
  
  public void setMyText(String myText) { this.myText = myText ; }
  
  public void processRender(WebuiRequestContext context) throws Exception {
    super.processRender(context) ;
  }
  
  public static class ChangeTextActionListener extends EventListener<UITestComponent> {

    public void execute(Event<UITestComponent> event) throws Exception {
      UITestComponent component = event.getSource() ;
      component.setMyText("Hello My JAVA World !!!") ;
      
      PortletRequestContext context = (PortletRequestContext) event.getRequestContext() ;
      context.addUIComponentToUpdateByAjax(component);
      context.setApplicationMode(PortletMode.HELP) ;
    }
  }
}
