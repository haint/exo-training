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
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Hai Thanh Nguyen</a>
 * @version $Id$
 *
 */
public class Main extends HLayout implements EntryPoint
{
   
   public Main()
   {
      setHeight100();
      setWidth100();
      setLayoutMargin(5);
   }

   /**
    * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
    */
   @Override
   public void onModuleLoad()
   {
      ApplicationManager manager = ApplicationManager.getInstance();
      Display display = (Display)manager.getSystemApp(Display.class);
      ControlPanel controlPanel = (ControlPanel)manager.getSystemApp(ControlPanel.class);
      addMember(controlPanel);
      addMember(display);
      draw();
   }
}
