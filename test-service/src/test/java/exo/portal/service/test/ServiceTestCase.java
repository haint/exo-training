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
package exo.portal.service.test;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.services.log.ExoLogger;

import exo.portal.service.NewCommer;

import junit.framework.TestCase;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 9, 2011  
 */
public class ServiceTestCase extends TestCase {
  private NewCommer newcommer ;
  private ExoLogger logger ;
  
  public void setUp() throws Exception {
//    StandaloneContainer myContainer = StandaloneContainer.getInstance() ;
    ExoContainer myContainer = ExoContainerContext.getCurrentContainer() ;
//    PortalContainer myContainer = PortalContainer.getInstance() ;
//    RootContainer myContainer = RootContainer.getInstance() ;
    this.newcommer = (NewCommer) myContainer.getComponentInstance(NewCommer.class) ;
//    this.logger = (ExoLogger) myContainer.getComponentInstance(ExoLogger.class) ;
  }
  
  public void testService() {
    this.newcommer.test() ;
  }
}
