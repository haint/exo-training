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
package org.exoplatform.portlet;

import java.io.IOException;

import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 20, 2011  
 */
public class Jsr286EventListenerPortlet extends GenericPortlet {
  
  @RenderMode(name="view")
  public void viewNormal(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    getPortletContext().getRequestDispatcher("/jsp/view.jsp").include(request, response) ;
  }
  
  @ProcessEvent(qname="{http://mycompany.com/events}contactInfo")
  public void processEvent(EventRequest request, EventResponse response) throws PortletException, IOException {
    String contactInfo = (String) request.getEvent().getValue() ;
    ContactInfoBean bean = new ContactInfoBean() ;
    bean.setName("No name") ;
    bean.setEmail("No email") ;
    
    try {
      bean.setName(contactInfo.split(",")[0]) ;
      bean.setEmail(contactInfo.split(",")[1]) ;
    } catch(Exception e) {
      e.printStackTrace() ;
    }
    request.getPortletSession().setAttribute("contactInfo", bean) ;
  }
}
