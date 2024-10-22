/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
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
package org.exoplatform.portal.webui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.exoplatform.upload.UploadResource;
import org.exoplatform.upload.UploadService;
import org.exoplatform.upload.UploadService.UploadUnit;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIFormInputBase;



/**
 * @author <a href="mailto:haint@exoplatform.com">Nguyen Thanh Hai</a>
 *
 * @datJul 19, 2011
 */

@ComponentConfig(template = "app:/groovy/webui/form/UIUploadInput.gtmpl", events = {
   @EventConfig(listeners = UIMockUploadInput.CreateUploadIdActionListener.class),
   @EventConfig(listeners = UIMockUploadInput.RemoveUploadIdActionListener.class)})
public class UIMockUploadInput extends UIFormInputBase<String>
{
   private LinkedList<String> uploadIds = new LinkedList<String>();
   
   private boolean extended = false;
   
   private Integer limitSize;
   
   private UploadUnit limitUnit;
   
   public UIMockUploadInput(String name, String bindingExpression)
   {
      this(name, bindingExpression, 1);
   }
   
   public UIMockUploadInput(String name, String bindingExpression, int limitFile)
   {
      super(name, bindingExpression, String.class);
      setId("UIUploadInput");
      
      if (limitFile < 1)
      {
         extended = true;
         limitFile = 1;
      }
      
      for(int i = 0; i < limitFile; i++) 
      {
         uploadIds.add( new StringBuffer().append(Math.abs(this.hashCode())).append('-').append(i).toString());
      }
      UploadService service = getApplicationComponent(UploadService.class);
      for(int i = 0; i < uploadIds.size(); i++)
      {
         service.addUploadLimit(uploadIds.get(i), null); // Use the limit set by the service. Warning, the service can allow no size limit (value to 0)
      }
      setComponentConfig(UIMockUploadInput.class, null);
   }

   public UIMockUploadInput(String name, String bindingExpression,int limitFile, int limitSize)
   {
      this(name, bindingExpression, limitFile, limitSize, UploadUnit.MB);
   }
   
   public UIMockUploadInput(String name, String bindingExpression, int limitFile, int limitSize, UploadUnit unit)
   {
      super(name, bindingExpression, String.class);
      setId("UIUploadInput");
      
      if(limitFile < 1) 
      {
         extended = true;
         limitFile = 1;
      }
      
      this.limitUnit = unit;
      
      for(int i = 0; i < limitFile; i++) 
      {
         uploadIds.add(new StringBuffer().append(Math.abs(hashCode())).append('-').append(i).toString());
      }
      UploadService service = getApplicationComponent(UploadService.class);
      for(int i = 0; i < limitFile; i++)
      {
         service.addUploadLimit(uploadIds.get(i), Integer.valueOf(limitSize), unit);
      }
      setComponentConfig(UIMockUploadInput.class, null);
   }
   
   public String[] getUploadIds()
   {
      return uploadIds.toArray(new String[uploadIds.size()]);
   }
   
   public boolean isExtendedMode()
   {
      return extended;
   }
   
   public void addNewUploadId()
   {
      String lastedUploadId = uploadIds.getLast();
      String hash = lastedUploadId.substring(0, lastedUploadId.indexOf('-'));
      int newIndex = Integer.valueOf(lastedUploadId.substring(hash.length()  + 1)) + 1;
      String newUploadId = new StringBuffer().append(hash).append('-').append(newIndex).toString();
      uploadIds.addLast(newUploadId);
      UploadService service = getApplicationComponent(UploadService.class);
      service.addUploadLimit(newUploadId, limitSize, limitUnit);
   }
   
   public void removeUploadId(String uploadId)
   {
      uploadIds.remove(uploadId);
      UploadService service = getApplicationComponent(UploadService.class);
      service.removeUploadLimit(uploadId);
   }
   
   public UploadResource[] getUploadResources() {
      List<UploadResource> holder = new ArrayList<UploadResource>();
      UploadService service = getApplicationComponent(UploadService.class);
      for(int i = 0; i < uploadIds.size(); i++)
      {
         UploadResource uploadResource = service.getUploadResource(uploadIds.get(i));
         if(uploadResource == null) continue;
         holder.add(uploadResource) ;
      }
      return holder.toArray(new UploadResource[holder.size()]);
   }
   
   public UploadResource getUploadResource(String uploadId) {
      UploadService service = getApplicationComponent(UploadService.class);
      return service.getUploadResource(uploadId);
   }
   
   public InputStream[] getUploadDataAsStreams() throws FileNotFoundException 
   {
      List<InputStream> holder = new ArrayList<InputStream>();
      UploadService service = getApplicationComponent(UploadService.class);
      for(int i = 0; i < uploadIds.size(); i++)
      {
         UploadResource uploadResource = service.getUploadResource(uploadIds.get(i));
         if(uploadResource == null) continue;
         File file = new File(uploadResource.getStoreLocation());
         holder.add(new FileInputStream(file));
      }
      return holder.toArray(new InputStream[holder.size()]);
   }
   
   public InputStream getUploadDataAsStream(String uploadId) throws FileNotFoundException
   {
      UploadService service = getApplicationComponent(UploadService.class);
      UploadResource uploadResource = service.getUploadResource(uploadId);
      if(uploadResource == null) return null;
      else return new FileInputStream(new File(uploadResource.getStoreLocation()));
   }
   
   public void decode(Object input, WebuiRequestContext context) throws Exception
   {
   }

   public static class CreateUploadIdActionListener extends EventListener<UIMockUploadInput>
   {
      public void execute(Event<UIMockUploadInput> event) throws Exception
      {
         UIMockUploadInput input = event.getSource();
         input.addNewUploadId();
         event.getRequestContext().addUIComponentToUpdateByAjax(input);
      }
   }
   
   public static class RemoveUploadIdActionListener extends EventListener<UIMockUploadInput>
   {
      public void execute(Event<UIMockUploadInput> event) throws Exception
      {
         UIMockUploadInput input = event.getSource();
         WebuiRequestContext context = event.getRequestContext();
         String uploadId = context.getRequestParameter(UIComponent.OBJECTID);
         input.removeUploadId(uploadId);
         context.addUIComponentToUpdateByAjax(input);
      }
   }
}