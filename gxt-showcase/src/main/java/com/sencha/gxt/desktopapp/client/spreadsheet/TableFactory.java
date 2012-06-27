/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.spreadsheet;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanFactory.Category;
import com.sencha.gxt.desktopapp.client.persistence.AutoBeanToString;

@Category({AutoBeanToString.class, TableCategory.class})
public interface TableFactory extends AutoBeanFactory {

  AutoBean<Row> row();

  AutoBean<Table> table();
}