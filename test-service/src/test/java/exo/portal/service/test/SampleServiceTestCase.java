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

import junit.framework.TestCase;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.upload.MimeTypeUploadPlugin;
import org.exoplatform.upload.UploadService;

import exo.portal.service.CollectionParamSample;
import exo.portal.service.ComponentPluginSample;
import exo.portal.service.ExternalComponentPluginSample;
import exo.portal.service.InitParamSample;
import exo.portal.service.NewCommer;
import exo.portal.service.ObjectParamSample;
import exo.portal.service.impl.ExternalComponentPluginSampleImpl;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 10, 2011  
 */
public class SampleServiceTestCase extends TestCase {
  private NewCommer newcommer ;
  private InitParamSample simpleParams ;
  private ObjectParamSample objectParams ;
  private CollectionParamSample collectionParams ;
  private ComponentPluginSample componentPlugin ;
  private ExternalComponentPluginSampleImpl externalPlugin ;
  private UploadService uploadService ;
  private MimeTypeUploadPlugin mimetypePlugin ;

  public void setUp() throws Exception {
    PortalContainer container = PortalContainer.getInstance() ;
//    newcommer = (NewCommer) container.getComponentInstance(NewCommer.class) ;
//    simpleParams = (InitParamSample) container.getComponentInstance(InitParamSample.class) ;
//    objectParams = (ObjectParamSample) container.getComponentInstance(ObjectParamSample.class) ;
//    collectionParams = (CollectionParamSample) container.getComponentInstance(CollectionParamSample.class) ;
//    componentPlugin = (ComponentPluginSample) container.getComponentInstance(ComponentPluginSample.class) ;
//    externalPlugin = (ExternalComponentPluginSampleImpl) container.getComponentInstanceOfType(ExternalComponentPluginSampleImpl.class) ;
    uploadService = (UploadService) container.getComponentInstanceOfType(UploadService.class) ;
//    mimetypePlugin = uploadService.getPlugin() ;
  }
  
  public void testUploadService() {
    assertNotNull(uploadService) ;
//    assertNotNull(mimetypePlugin) ;
//    System.out.println("\n\n\n" + mimetypePlugin.getMimeType("tmp.rtf") + "\n\n\n") ;
//    System.out.println("\n\n\n" + mimetypePlugin.getMimeType("tmp.unknown") + "\n\n\n") ;
  }
  
//  public void testNewCommer() {
//    newcommer.test() ;
//  }
//  
//  public void testSimpleParams() {
////    simpleParams.dump() ;
//    assertNotNull(simpleParams) ;
//    assertTrue(simpleParams.expected()) ;
//  }
//  
//  public void testObjectParams() {
////    objectParams.dump() ;
//    assertNotNull(objectParams) ;
//    assertTrue(objectParams.expected()) ;
//  }
//  
//  public void testCollectionParams() {
//    assertNotNull(collectionParams) ;
//    assertTrue(collectionParams.expected()) ;
//  }
//  
//  public void testComponentPlugin() {
//    assertNotNull(componentPlugin) ;
//    assertTrue(componentPlugin.expected()) ;
//  }
//  
//  public void testExternalPlugin() {
//    assertNotNull(externalPlugin) ;
//    assertTrue(externalPlugin.expected()) ;
//  }
}
