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
package org.exoplatform.portal.webui.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.exoplatform.portal.webui.page.UIPageBrowser;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UITabPane;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormDateTimeInput;
import org.exoplatform.webui.form.UIFormInputInfo;
import org.exoplatform.webui.form.UIFormMultiValueInputSet;
import org.exoplatform.webui.form.UIFormPopupWindow;
import org.exoplatform.webui.form.UIFormRadioBoxInput;
import org.exoplatform.webui.form.UIFormSelectBox;
import org.exoplatform.webui.form.UIFormStringInput;
import org.exoplatform.webui.form.UIFormTextAreaInput;
import org.exoplatform.webui.form.UIFormUploadInput;
import org.exoplatform.webui.form.UISearchForm;
import org.exoplatform.webui.form.wysiwyg.UIFormWYSIWYGInput;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jun 21, 2011  
 */

@ComponentConfig(lifecycle=UIFormLifecycle.class, 
   template = "system:/groovy/webui/form/UIFormWithTitle.gtmpl", 
   events={@EventConfig(listeners=UITestFormAndDialogs.SubmitActionListener.class)})
   public class UITestFormAndDialogs extends UIForm {

   public UITestFormAndDialogs() throws Exception {
      //    this.addTabPane();
      //    this.addFormInputInfo();
      //    this.addFormStringInput();
      //    this.addFormTextAreaInput();
      //    this.addFormSelectBox();
      //    this.addFormRadioBoxInput();
      //    this.addFormDateTimeInput();
      //    this.addFormUploadInput();
      //    this.addMultiValueInputSet();
      //    this.addFormPopupWindow();
      //    this.addFormWYSIWYGInput() ;
   }

   public void addMultiValueInputSet() throws Exception {
      UIFormMultiValueInputSet input = new UIFormMultiValueInputSet("UploadInput", null) ;
      input.setType(UIFormUploadInput.class) ;
      input.setConstructorParameterTypes(String.class, String.class) ;
      input.setConstructorParameterValues(new Object[] { "UIFormUploadInput", "UIFormUploadInput" }) ;
      this.addUIFormInput(input);
   }

   public void addFormInputInfo() throws Exception {
      UIFormInputInfo info = new UIFormInputInfo("UIFormInputInfo", "UIFormInputInfo", "Hello World") ;
      this.addChild(info) ;
   }

   public void addTabPane() throws Exception {
      UITabPane tab = addChild(UITabPane.class, null, null) ;
      tab.addChild(UITestConfig.class, null, null).setRendered(true) ;
      tab.addChild(UITestForm.class, null, null) ;
      tab.setSelectedTab(1) ;
   }

   public void addFormStringInput() throws Exception {
      UIFormStringInput stringInput = new UIFormStringInput("UIFormStringInput", "Hello int string input") ;
      this.addChild(stringInput) ;
   }

   public void addFormTextAreaInput() throws Exception {
      UIFormTextAreaInput texarea = new UIFormTextAreaInput("UIFormTextAreaInput", "UIFormTextAreaInput", "Input text here") ;
      this.addChild(texarea) ;
   }

   public void addFormDateTimeInput() throws Exception {
      UIFormDateTimeInput dateInput = new UIFormDateTimeInput("UIFormDateTimeInput", "UIFormDateTimeInput", new Date()) ;
      this.addChild(dateInput) ;
   }

   public void addFormSelectBox() throws Exception {
      SelectItemOption<String> option1 = new SelectItemOption<String>("option1", "option1", null) ;
      SelectItemOption<String> option2 = new SelectItemOption<String>("option2", "option2", null) ;
      List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>() ;
      options.add(option1) ;
      options.add(option2) ;

      UIFormSelectBox select = new UIFormSelectBox("UIFormSelectBox", "UIFormSelectBox", options) ;
      this.addChild(select) ;
   }

   public void addFormRadioBoxInput() throws Exception {
      SelectItemOption<String> option1 = new SelectItemOption<String>("option1", "radio1", null) ;
      SelectItemOption<String> option2 = new SelectItemOption<String>("option2", "radio2", null) ;
      List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>() ;
      options.add(option1) ;
      options.add(option2) ;

      UIFormRadioBoxInput radio = new UIFormRadioBoxInput("UIFormRadioBoxInput", "UIFormRadioBoxInput", options) ;
      this.addChild(radio) ;
   }

   public void addFormUploadInput() throws Exception {
      //UIUploadInput upload = new UIUploadInput("UIFormUploadInput", "UIFormUploadInput", 3, 1000) ;
      UIFormUploadInput upload = new UIFormUploadInput("UIFormUploadInput", "UIFormUploadInput");
      //      upload.setAutoUpload(false);
      this.addChild(upload);
   }

   public void addFormPopupWindow() throws Exception {
      UIFormPopupWindow window = addChild(UIFormPopupWindow.class, null, "PopupPageSelector") ;
      window.setWindowSize(600, 300) ;
      window.setRendered(false) ;
      UIPageBrowser browser = createUIComponent(UIPageBrowser.class, null, null) ;
      window.setUIComponent(browser) ;
   }

   public void addFormWYSIWYGInput() throws Exception {
      UIFormWYSIWYGInput form = new UIFormWYSIWYGInput("UIFormWYSIWYGInput", "UIFormWYSIWYGInput", "Test") ;
      this.addChild(form) ;
   }

   public void addSearchForm(List<SelectItemOption<String>> options) throws Exception {
      UISearchForm search = addChild(UISearchForm.class, null, null) ;
      search.setOptions(options) ;
   }

   public static class SubmitActionListener extends EventListener<UITestFormAndDialogs> {
      public void execute(Event<UITestFormAndDialogs> event) throws Exception {
         UITestFormAndDialogs form = event.getSource();
         String inputString = form.getChild(UIFormStringInput.class).getValue();

         UIFormInputInfo inputInfo = form.getChild(UIFormInputInfo.class);
         inputInfo.setValue(inputString);
      }
   }
}
