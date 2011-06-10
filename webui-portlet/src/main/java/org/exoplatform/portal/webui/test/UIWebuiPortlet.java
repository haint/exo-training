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

import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 20, 2011  
 */

@ComponentConfig(lifecycle = UIApplicationLifecycle.class)
public class UIWebuiPortlet extends UIPortletApplication {
  public static final String TEXT_PREFERENCE = "text" ;
  
  public UIWebuiPortlet() throws Exception {
    super() ;
    this.addChild(UITestFormAndDialogs.class, null, null) ;
  }
  
  
  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
//    getChildren().clear() ;
//    PortletRequestContext pcontext = (PortletRequestContext) context ;
//    PortletMode currentMode = pcontext.getApplicationMode() ;
//    if(PortletMode.VIEW.equals(currentMode)) {
//      if(this.getChild(UITestFormAndDialogs.class) == null) this.addChild(UITestFormAndDialogs.class, null, null) ;
//      if(this.getChild(UITestForm.class) == null) this.addChild(UITestForm.class, null, null) ; 
//    } else if(PortletMode.HELP.equals(currentMode)) {
//      if(this.getChild(UITestComponent.class) == null) this.addChild(UITestComponent.class, null, null) ;
//    } else if(PortletMode.EDIT.equals(currentMode)){
//      if(this.getChild(UITestConfig.class) == null) this.addChild(UITestConfig.class, null, null) ;
//    }
    super.processRender(app, context) ;
  }
}
