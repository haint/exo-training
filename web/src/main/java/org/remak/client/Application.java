/*
 * Copyright (C) 2011 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.remak.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Hai Thanh Nguyen</a>
 * @version $Id$
 *
 */
public class Application extends HLayout implements EntryPoint
{
   @Override
   public void onModuleLoad()
   {
//      final String skinCookieName = "skin_name_2_4";
//      String currentSkin = Cookies.getCookie(skinCookieName);
//      if (currentSkin == null) {
//          currentSkin = "Graphite";
//      }
//      Cookies.setCookie(skinCookieName, currentSkin);
      
      setHeight100();
      setWidth100();
      setLayoutMargin(20);
      
      SectionStack leftSideLayout = new SectionStack();
      leftSideLayout.setWidth(250);
      leftSideLayout.setVisibilityMode(VisibilityMode.MUTEX);
      leftSideLayout.setAnimateSections(true);
      
      SectionStackSection mainSection = new SectionStackSection("Main Example Items");
      mainSection.setExpanded(true);
      leftSideLayout.setSections(mainSection);
      
      HLayout rightSideLayout = new HLayout();
      
      addMember(leftSideLayout);
      addMember(rightSideLayout);
      
      draw();
   }
}
