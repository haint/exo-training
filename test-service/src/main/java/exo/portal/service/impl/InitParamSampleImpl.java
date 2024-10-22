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
import org.exoplatform.container.xml.PropertiesParam;
import org.exoplatform.container.xml.ValueParam;

import exo.portal.service.InitParamSample;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 9, 2011  
 */
public class InitParamSampleImpl implements InitParamSample {
  private String value ;
  private String foo ;
  private String bar ;
  
  public InitParamSampleImpl(InitParams initParams) {
    super() ;
    ValueParam valueParam = initParams.getValueParam("sample-value") ;
    value = valueParam.getValue() ;
    PropertiesParam propParam = initParams.getPropertiesParam("sample-properties") ;
    if(propParam != null) {
      foo = propParam.getProperty("foo") ;
      bar = propParam.getProperty("bar") ;
    }
  }

  public void dump() {
    System.out.println("Value: " + value);
    System.out.println("Property foo: " + foo);
    System.out.println("Property bar: " + bar);
  }

  public boolean expected() {
    if("sampleValue".equals(value) && "foo".equals(foo) && "bar".equals("bar")) return true ; 
    return false ;
  }
}
