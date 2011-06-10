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
package exoplatform;

import javax.jcr.Credentials;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import junit.framework.TestCase;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.CredentialsImpl;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.IdentityRegistry;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 14, 2011  
 */
public class JcrTestCase extends TestCase {

  private RepositoryService repoService ;
  private IdentityRegistry identityRegistry ;  
  private static final Log log = ExoLogger.getLogger(JcrTestCase.class) ;
  
  public void setUp() throws Exception {
    ExoContainer container = ExoContainerContext.getCurrentContainer()  ;
    repoService = (RepositoryService) container.getComponentInstance(RepositoryService.class) ;
    identityRegistry = (IdentityRegistry) container.getComponentInstanceOfType(IdentityRegistry.class) ;
    
    if(System.getProperty("java.security.auth.login.config") == null)
        System.setProperty("java.security.auth.login.config", 
          Thread.currentThread().getContextClassLoader().getResource("login.config").toString()) ;
  }
  
  public void testWorkspaceNames() throws Exception {
    String[] workspaceNames = repoService.getDefaultRepository().getWorkspaceNames() ;
    for(String s : workspaceNames) {
      log.info("testWorkspaceNames: " + s) ;
    }
    log.info("\n\n ------------ setUp(): " + identityRegistry.hashCode() + " ------------ \n\n") ;
  }
  
  public void testLogin() throws Exception {
    log.info("----------------testLogin()-------------------------") ;
    Credentials credentical = new CredentialsImpl("root", "exo".toCharArray()) ;
    Repository repository = repoService.getRepository("repository") ;
    Session session = repository.login(credentical, "production") ;
//    assertNotNull(session) ;
//    assertEquals(session.getUserID(), "root") ;
//    assertEquals(session.getWorkspace().getName(), "production") ;
    log.info("Login successfully !!!") ;
  }
  
//  public void testAddingNode() throws Exception {
//    log.info("----------------testAddingNode()---------------------") ;
//    Credentials credentical = new CredentialsImpl("exo", "exo".toCharArray()) ;
//    Repository repository = repoService.getRepository("repository") ;
//    Session session = repository.login(credentical, "production") ;
//    assertNotNull(session) ;
//    
//    Node rootNode = session.getRootNode() ;
//    Node encyclopedia = rootNode.addNode("wiki:encyclopedia") ;
//    Node p = encyclopedia.addNode("wiki:entry") ;
//    p.setProperty("wiki:title", "Java") ;
//    p.setProperty("wiki:content", "java") ;
//    p.setProperty("wiki:category", new String[] { "programing language", "sun" }) ;
//    
//    Node n = encyclopedia.addNode("wiki:entry") ;
//    n.setProperty("wiki:title", "Song") ;
//    n.setProperty("wiki:content", "song") ;
//    n.setProperty("wiki:category", new String[] { "song", "music" }) ;
//    session.save() ;
//  }
//  
//  public void testBrowsingContent() throws Exception {
//    log.info("----------------testBrowsingContent()-----------------") ;
//    Credentials credentical = new CredentialsImpl("exo", "exo".toCharArray()) ;
//    Repository repository = repoService.getRepository("repository") ;
//    Session session = repository.login(credentical, "production") ;
//    assertNotNull(session) ;
//    
//    Node rootNode = session.getRootNode() ;
//    Node encyclopedia = rootNode.getNode("wiki:encyclopedia") ;
//    NodeIterator iterator = encyclopedia.getNodes() ;
//    while(iterator.hasNext()) {
//      Node node = iterator.nextNode() ;
//      log.info("Name: " + node.getName()) ;
//      log.info("Title: " + node.getProperty("wiki:title").getString()) ;
//      log.info("Content: " + node.getProperty("wiki:content").getString()) ;
//      log.info("Path: " + node.getPath()) ;
//      Value[] categories = node.getProperty("wiki:category").getValues() ;
//      for(Value c : categories) {
//        log.info("Category: " + c.getString()) ;
//      }
//    }
//  }
//  
//  public void testSearchContentWithXPath() throws Exception {
//    log.info("----------------testSearchContentWithXPath()-----------------") ;
//    Credentials credentical = new CredentialsImpl("exo", "exo".toCharArray()) ;
//    Repository repository = repoService.getRepository("repository") ;
//    Session session = repository.login(credentical, "production") ;
//    Workspace ws = session.getWorkspace() ;
//    QueryManager qm = ws.getQueryManager() ;
//    Query query = qm.createQuery("//wiki:encyclopedia/wiki:entry[@wiki:title = 'Java']", Query.XPATH) ;
//    QueryResult result = query.execute() ;
//    NodeIterator iterator = result.getNodes() ;
//    while(iterator.hasNext()) {
//      Node node = iterator.nextNode() ;
//      log.info("Name: " + node.getName()) ;
//      log.info("Title: " + node.getProperty("wiki:title").getString()) ;
//      log.info("Content: " + node.getProperty("wiki:content").getString()) ;
//      log.info("Path: " + node.getPath()) ;
//      Value[] categories = node.getProperty("wiki:category").getValues() ;
//      for(Value c : categories) {
//        log.info("Category: " + c.getString()) ;
//      }
//    }
//  }
//  
//  public void testSearchingContentWithLuceneFuzzy() throws Exception {
//    log.info("----------------testSearchContentWithLuceneFuzzy()-----------------") ;
//    Credentials credentical = new CredentialsImpl("exo", "exo".toCharArray()) ;
//    Repository repository = repoService.getRepository("repository") ;
//    Session session = repository.login(credentical, "production") ;
//    Workspace ws = session.getWorkspace() ;
//    QueryManager qm = ws.getQueryManager() ;
//    Query query = qm.createQuery("SELECT * FROM nt:unstructured WHERE CONTAINS( * , 'java')", Query.SQL) ;
//    QueryResult result = query.execute() ;
//    NodeIterator iterator = result.getNodes() ;
//    while(iterator.hasNext()) {
//      Node node = iterator.nextNode() ;
//      log.info("Name: " + node.getName()) ;
//      log.info("Title: " + node.getProperty("wiki:title").getString()) ;
//      log.info("Content: " + node.getProperty("wiki:content").getString()) ;
//      log.info("Path: " + node.getPath()) ;
//      Value[] categories = node.getProperty("wiki:category").getValues() ;
//      for(Value c : categories) {
//        log.info("Category: " + c.getString()) ;
//      }
//    }
//  }
}
