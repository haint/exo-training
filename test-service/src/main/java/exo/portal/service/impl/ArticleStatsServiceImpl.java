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

import exo.portal.service.ArticleStatsService;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 9, 2011  
 */
public class ArticleStatsServiceImpl implements ArticleStatsService {
  private String calcMethod = "extract" ;
  private String language = "France" ;
  private String variant = "French" ;
  
  public ArticleStatsServiceImpl(InitParams initParams) {
    super() ;
    calcMethod = initParams.getValueParam("calc-method").getValue() ;
    PropertiesParam detailsForExtractMethod = initParams.getPropertiesParam("details-of-extract-method") ;
    if(detailsForExtractMethod != null) {
      language = detailsForExtractMethod.getProperty("language") ;
      variant = detailsForExtractMethod.getProperty("variant") ;
    }
  }
  
  public int calcSentences(String article) {
    System.out.println("Language: " + language);
    System.out.println("Variant: " + variant);
    if(calcMethod.equals("fast")) return 1 ;
    else if(calcMethod.equals("extract")) return 2 ;
    return 0;
  }
}
