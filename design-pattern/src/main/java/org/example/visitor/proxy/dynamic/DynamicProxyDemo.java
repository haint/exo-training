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
package org.example.visitor.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jul 11, 2011  
 */
public class DynamicProxyDemo
{

   interface MyClass {
      void methodA(String s) ;
      void methodB(int i) ;
      String methodC(String s, int i) ;
   }

   public static void main(String[] args) {
      MyClass prox = (MyClass)Proxy.newProxyInstance(MyClass.class.getClassLoader(), new Class[] { MyClass.class }, 
         new InvocationHandler()
      {
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
         {
            System.out.println("Proxy = " + proxy.getClass());
            System.out.println("Method = " + method);
            if (args != null) {
               System.out.println("args = ");
               for (int i = 0; i < args.length; i++)
                  System.out.println("\t" + args[i]);
            }
            return null;
         }
      }) ;

      System.out.println("about to call methodA");
      prox.methodA("hello");
      System.out.println("finish calling methodA");
      prox.methodB(47);
      prox.methodC("hello", 47);
   }
}
