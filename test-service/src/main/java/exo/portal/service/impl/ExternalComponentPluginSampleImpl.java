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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 13, 2011  
 */
public class ExternalComponentPluginSampleImpl {
  private List<ExternalSamplePlugin> plugins ;
  
  public void register(ExternalSamplePlugin plugin) {
    if(plugins == null) plugins = new ArrayList<ExternalSamplePlugin>() ;
    plugins.add(plugin) ;
  }

  public void dump() {
  }

  public boolean expected() {
    if(plugins.size() == 1 &&
             plugins.get(0).getName().equals("ExternalSamplePlugin") &&
             plugins.get(0).getValue().equals("sampleValue")) return true ;
    return false;
  }
}
