/*
 * Copyright (C) 2012 eXo Platform SAS.
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

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.core.UIDropDownControl;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.form.UIFormInputBase;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Hai Thanh Nguyen</a>
 * @version $Id$
 *
 */
public class UIMockDropDownInputBase extends UIFormInputBase<String>
{
   public UIMockDropDownInputBase(String ... options) throws Exception {
      List<SelectItemOption<String>> holder = new ArrayList<SelectItemOption<String>>();
      for(String opt : options) {
         SelectItemOption<String> item = new SelectItemOption<String>(opt);
         holder.add(item);
      }
      UIDropDownControl dropDownControl = new UIDropDownControl();
      dropDownControl.setAction("test");
      dropDownControl.setOptions(holder);
      addChild(dropDownControl);
   }

   @Override
   public void decode(Object input, WebuiRequestContext context) throws Exception
   {
      
   }
}
