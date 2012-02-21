/*
 * Copyright (C) 2011 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.portal.webui;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormStringInput;
import org.exoplatform.webui.form.UIFormTextAreaInput;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Hai Thanh Nguyen</a>
 * @version $Id$
 * 
 */
@ComponentConfig(lifecycle = UIFormLifecycle.class, template = "app:/groovy/webui/form/UIMockForm.gtmpl", events = {
   @EventConfig(listeners = UIMockForm.SaveActionListener.class),
   @EventConfig(listeners = UIMockForm.ResetActionListener.class)})
public class UIMockForm extends UIForm
{
   public UIMockForm() throws Exception
   {
      setActions(new String[] { "Save", "Reset" });
      addUIFormInput(new UIMockDropDownInputBase("item1", "item2", "item3"));
      addUIFormInput(new UIFormStringInput("input", "input", null));
      addUIFormInput(new UIFormTextAreaInput("output", "output", null));
      //Test UIDropDownControl
//      addUIFormInput(new UIMockDropDownControl("item1", "item2", "item3"));
   }
   
   public static class SaveActionListener extends EventListener<UIMockForm> 
   {
      /**
       * @see org.exoplatform.webui.event.EventListener#execute(org.exoplatform.webui.event.Event)
       */
      @Override
      public void execute(Event<UIMockForm> event) throws Exception
      {
         UIMockForm uiform = event.getSource();
         UIFormStringInput input = uiform.getUIStringInput("input");
         String value = input.getValue();
         UIFormTextAreaInput output = uiform.getUIFormTextAreaInput("output");
         output.setValue(value);
      }
   }
   
   public static class ResetActionListener extends EventListener<UIMockForm> 
   {

      /**
       * @see org.exoplatform.webui.event.EventListener#execute(org.exoplatform.webui.event.Event)
       */
      @Override
      public void execute(Event<UIMockForm> event) throws Exception
      {
         UIMockForm form = event.getSource();
         form.reset();
      }
   }
}
