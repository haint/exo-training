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

import org.exoplatform.container.component.ComponentPlugin;

import exo.portal.service.ComponentPluginSample;


/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 13, 2011  
 */
public class ComponentPluginSampleImpl implements ComponentPluginSample {
  private int countCP = 0 ;
  private int countACP = 0 ;
  private int countCP1 = 0 ;
  
  public void dump() {
    
  }

  public boolean expected() {
    if(countCP == 1 && countACP == 1 && countCP1 == 1) return true ;
    return false;
  }
  
  public void register1(ComponentPlugin plugin) { 
    countCP++ ; 
  }
  
  public void register2(AbstractPlugin plugin) { 
    countACP++ ; 
  }
  
  public void register3(ComponentPlugin1 plugin) { 
    countCP1++ ; 
  }
  

  public static abstract class AbstractPlugin implements ComponentPlugin {
    public String getName() {return null; }
    public void setName(String s) {}
    public String getDescription() { return null; }
    public void setDescription(String s) {}
  }
  
  public static class ComponentPlugin1 implements ComponentPlugin {
    public String getName() { return "plugin1"; }
    public void setName(String s) { }
    public String getDescription() { return null; }
    public void setDescription(String s) { }
  }
  
  public static class ComponentPlugin2 extends AbstractPlugin { 
    public String getName() { return "plugin2"; }
  }
  
  public static class ComponentPlugin3 extends ComponentPlugin1 { 
    public String getName() { return "plugin3"; }
  }
}
