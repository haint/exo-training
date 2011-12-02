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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;


/**
 * @author <a href="mailto:haithanh0809@gmail.com">Hai Thanh Nguyen</a>
 * @version $Id$
 *
 */
public class  ApplicationManager
{
   private static ApplicationManager instance;
   
   private Map<Class<?>, Application> appPool;
   
   private Map<Class<?>, Application> sysPool;
   
   private LinkedList<Class<?>> keys;
   
   private int poolSize = 10;
   
   static {
      instance = new ApplicationManager();
   }

   public static ApplicationManager getInstance() {
      return instance;
   }
   
   private ApplicationManager() {
      appPool = new HashMap<Class<?>, Application>(poolSize);
      sysPool = new HashMap<Class<?>, Application>();
      sysPool.put(Display.class, new Display());
      sysPool.put(ControlPanel.class, new ControlPanel());
      keys = new LinkedList<Class<?>>();
   }
   
   public Application register(Application app) {
      if(keys.size() == poolSize)  {
         Class<?> clazz = keys.removeFirst();
         appPool.remove(clazz);
      }
      keys.addLast(app.getClass());
      Application oldVersion = appPool.put(app.getClass(), app);
      return oldVersion;
   }
   
   public Application unregister(Application app) {
      return appPool.remove(app);
   }
   
   public Application getApplication(Class<?> clazz) {
      return appPool.get(clazz);
   }
   
   public Application getSystemApp(Class<?> clazz) {
      return sysPool.get(clazz);
   }
}
