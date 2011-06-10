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
package org.example.visitor.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jul 11, 2011  
 */
public class PoolManager
{
   private List<PoolItem> items = new ArrayList<PoolItem>() ;
   
   public void add(Object item) {
      items.add(new PoolItem(item)) ;
   }
   
   public ReleasableReference get() {
      for(int i = 0; i < items.size(); i++) {
         PoolItem item = items.get(i) ;
         if(!item.isUse()) {
            item.setUse(true) ;
            return new ReleasableReference(item) ;
         }
      }
      return null ;
   }
}
