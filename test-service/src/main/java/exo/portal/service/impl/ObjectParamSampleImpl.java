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

import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.exoplatform.container.xml.InitParams;

import exo.portal.service.ObjectParamSample;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 10, 2011  
 */
public class ObjectParamSampleImpl implements ObjectParamSample {
  private ObjectParamConfig config ;
  
  public ObjectParamSampleImpl(InitParams initParams) {
    super() ;
    config = (ObjectParamConfig) initParams.getObjectParam("sample.config").getObject() ;
  }
  
  public void dump() {
    System.out.println("String: " + config.getStringField()) ;
    System.out.println("Boolean: " + config.isBoolField());
    System.out.println("Integer: " +config.getIntField()) ;
    System.out.println("Long: " + config.getLongField()) ;
    System.out.println("Double: " + config.getDoubleField()) ;
    GregorianCalendar cal = new GregorianCalendar() ;
    cal.setTimeZone(TimeZone.getTimeZone("GMT")) ;
    cal.setTime(config.getDateField()) ;
    System.out.println("Date: " + cal.getTime().toGMTString()) ;
  }
  
  public boolean expected() {
    GregorianCalendar calGMT = new GregorianCalendar(2011, 5, 10, 16, 5, 59);
    calGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
    
    GregorianCalendar calUTC = new GregorianCalendar(2011, 5, 10, 16, 5, 59);
    calUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    
    if("stringValue".equals(config.getStringField()) 
        && config.isBoolField() 
        && config.getIntField() == 1 
        && config.getLongField() == 9000000000L 
        && config.getDoubleField() == 1.234 
        && calGMT.getTimeInMillis() == config.getDateField().getTime() 
        && calUTC.getTimeInMillis() == config.getDateField().getTime()) return true ;
    return false ;
  }
}
