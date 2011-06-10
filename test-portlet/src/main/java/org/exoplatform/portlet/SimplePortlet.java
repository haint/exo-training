package org.exoplatform.portlet;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.WindowState;
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
import javax.portlet.RenderResponse;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 16, 2011  
 */
public class SimplePortlet extends GenericPortlet {

  private static final String NORMAL_VIEW = "/jsp/normal.jsp" ;

  private static final String MAXIMIZED_VIEW = "/jsp/maximized.jsp" ;

  private static final String HELP_VIEW = "/jsp/help.jsp" ;

  private PortletRequestDispatcher normalView ;
  private PortletRequestDispatcher maximizedView ;
  private PortletRequestDispatcher helpView ;

  public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException{
    if(WindowState.MINIMIZED.equals(request.getWindowState())) {
      return ;
    }

    if(WindowState.NORMAL.equals(request.getWindowState())) {
      normalView.include(request, response) ;
    } else  {
      maximizedView.include(request, response) ;
    }
  }
  
  protected void doEdit(RenderRequest request, RenderResponse response) throws PortletException, java.io.IOException {
    response.getWriter().write("Edit something else !!!") ;
  }
  
  public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    helpView.include(request, response) ;
  }
  
  public void init(PortletConfig config) throws PortletException {
    super.init(config) ;
    normalView = config.getPortletContext().getRequestDispatcher(NORMAL_VIEW) ;
    maximizedView = config.getPortletContext().getRequestDispatcher(MAXIMIZED_VIEW) ;
    helpView = config.getPortletContext().getRequestDispatcher(HELP_VIEW) ;
  }
  
  public void destroy() {
    normalView = null ;
    maximizedView = null ;
    helpView = null ;
    super.destroy() ;
  }
}
