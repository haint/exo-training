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

import javax.portlet.PortletPreferences;

import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.application.portlet.PortletRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.lifecycle.Lifecycle;


/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 20, 2011  
 */
@ComponentConfig(lifecycle = Lifecycle.class, template = "app:/groovy/webui/WebuiPortlet/UITestForm.gtmpl")
public class UITestForm extends UIComponent {
  
  private String text ;
  
  public UITestForm() {
    
  }
  
  public void init() {
    PortletRequestContext context = WebuiRequestContext.getCurrentInstance() ;
    PortletPreferences preferences = context.getRequest().getPreferences() ;
    text = preferences.getValue(UIWebuiPortlet.TEXT_PREFERENCE, null) ;
  }
  
  public void processRender(WebuiRequestContext context) throws Exception {
    init() ;
    super.processRender(context) ;
  }
  
  public String getText() { return text ; }
  
  public void setText(String text) { this.text = text ; }
  
}
