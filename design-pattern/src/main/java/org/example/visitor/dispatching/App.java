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
package org.example.visitor.dispatching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.example.visitor.dispatching.Visitor.Drink;
import org.example.visitor.dispatching.Visitor.Party;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jul 11, 2011  
 */
public class App
{
   public static class FriendGenerator {
      private static Random random = new Random() ;
      
      public static Friend generate() {
         switch (random.nextInt(3))
         {
            case 0 :
               return new Friend.FriendsInHighSchool() ;
            case 1 :
               return new Friend.FriendsFromNeighbour() ;
            case 2:
               return new Friend.NewFriends() ;
            default :
               return null ;
         }
      }
   }
   
   public static void main(String[] args) throws Exception 
   {
      List<Friend> list = new ArrayList<Friend>() ;
      for(int i = 0; i < 10; i++) {
         list.add(FriendGenerator.generate()) ;
      }
      
      Party party = new Party() ;
      Iterator<Friend> i = list.iterator() ;
      while(i.hasNext()) {
         Friend friend = i.next() ;
         friend.accept(party) ;
         System.out.println(party);
      }
      
      i = list.iterator() ;
      Drink drink = new Drink() ;
      while(i.hasNext()) {
         Friend friend = i.next() ;
         friend.accept(drink) ;
      }
   }
}
