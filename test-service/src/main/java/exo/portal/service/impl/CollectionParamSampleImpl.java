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

import org.exoplatform.container.xml.InitParams;

import exo.portal.service.CollectionParamSample;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 13, 2011  
 */
public class CollectionParamSampleImpl implements CollectionParamSample {
  private CollectionParamConfig config ;
  
  public CollectionParamSampleImpl(InitParams initParams) {
    super() ;
    config = (CollectionParamConfig) initParams.getObjectParam("sample.collection.config").getObject() ;
  }

  public void dump() {
    
  }

  public boolean expected() {
    CollectionParamConfig.SampleObject obj = new CollectionParamConfig.SampleObject() ;
    obj.setName("sample") ;
    if(config.getList().size() == 2
        && config.getMap().size() == 2
        && config.getList().get(0).equals("sample")
        && config.getList().get(1).equals(obj)
        && config.getMap().get("string").equals("sample")
        && config.getMap().get("object").equals(obj)) return true ;
    return false;
  }
}
