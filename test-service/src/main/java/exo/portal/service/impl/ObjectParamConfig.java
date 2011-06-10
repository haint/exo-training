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

import java.util.Date;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 10, 2011  
 */
public class ObjectParamConfig {
  
  private String stringField ;
  private boolean boolField ;
  private int intField ;
  private long longField ;
  private double doubleField ;
  private Date dateField ;
  
  public String getStringField() {
    return stringField;
  }
  public void setStringField(String stringField) {
    this.stringField = stringField;
  }
  public boolean isBoolField() {
    return boolField;
  }
  public void setBoolField(boolean boolField) {
    this.boolField = boolField;
  }
  public int getIntField() {
    return intField;
  }
  public void setIntField(int intField) {
    this.intField = intField;
  }
  public long getLongField() {
    return longField;
  }
  public void setLongField(long longField) {
    this.longField = longField;
  }
  public double getDoubleField() {
    return doubleField;
  }
  public void setDoubleField(double doubleField) {
    this.doubleField = doubleField;
  }
  public Date getDateField() {
    return dateField;
  }
  public void setDateField(Date dateField) {
    this.dateField = dateField;
  }
}
