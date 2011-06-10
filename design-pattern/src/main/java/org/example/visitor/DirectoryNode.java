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
package org.example.visitor;

import java.io.File;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jul 11, 2011  
 */
public class DirectoryNode implements FileSystemNode 
{

   private File directory ;
   
   public DirectoryNode(File directory)
   {
      this.directory = directory ;
   }
   
   @Override
   public void visit(PrintVisitor visitor)
   {
      visitor.visitDirectoryNode(this) ;
   }
   
   public File getDirectory()
   {
      return directory ;
   }
   
   public FileSystemNode[] getChildren() 
   {
      File[] files = directory.listFiles() ;
      FileSystemNode[] holder = new FileSystemNode[files.length] ;
      for(int i = 0; i < files.length; i++) 
      {
         if(files[i].isFile()) holder[i] = new FileNode(files[i]) ;
         else if(files[i].isDirectory()) holder[i] = new DirectoryNode(files[i]) ;
      }
      return holder ;
   }
}
