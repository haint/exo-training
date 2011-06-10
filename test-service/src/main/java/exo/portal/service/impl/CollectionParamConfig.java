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
package exo.portal.service.impl;

import java.util.List;
import java.util.Map;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 13, 2011  
 */
public class CollectionParamConfig {

  @SuppressWarnings("rawtypes")
  private List list ;
  @SuppressWarnings("rawtypes")
  private Map map ;
  
  @SuppressWarnings("rawtypes")
  public void setList(List list) { this.list = list ; }
  @SuppressWarnings("rawtypes")
  public List getList() { return list ; }

  @SuppressWarnings("rawtypes")
  public void setMap(Map map) { this.map = map ; }
  @SuppressWarnings("rawtypes")
  public Map getMap() { return map ; }
  
  public static class SampleObject {
    private String name ;
    
    public void setName(String name) { this.name = name ; }
    public String getName() { return name ; }
    
    public boolean equals(Object obj) {
      if(((SampleObject)obj).getName().equals(name)) return true ;
      return false ;
    }
  }
}
