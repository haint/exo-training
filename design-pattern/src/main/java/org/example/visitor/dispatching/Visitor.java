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

import org.example.visitor.dispatching.Friend.FriendsFromNeighbour;
import org.example.visitor.dispatching.Friend.FriendsInHighSchool;
import org.example.visitor.dispatching.Friend.NewFriends;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen Thanh Hai
 *          haint@exoplatform.com
 * Jul 11, 2011  
 */
public interface Visitor
{
   public void visit(Friend friend) ;
   
   public static class Party implements Visitor 
   {
      private String name ;
      
      public String toString() { return name ; }
      
      public void visit(Friend friend)
      {
         if(friend.getClass().equals(FriendsInHighSchool.class)) {
            this.name = "FriendsInHighSchool" ;
         } else if(friend.getClass().equals(FriendsFromNeighbour.class)) {
            this.name = "FriendsFromNeighbour" ;
         } else if(friend.getClass().equals(NewFriends.class)) {
            this.name = "NewFriends" ;
         }
      }
   }
   
   public static class Drink implements Visitor {

      public void visit(Friend friend)
      {
         if(friend.getClass().equals(FriendsInHighSchool.class)) {
            System.out.println("Drink and FriendsInHighSchool") ;
         } else if(friend.getClass().equals(FriendsFromNeighbour.class)) {
            System.out.println("Drink and FriendsFromNeighbour") ;
         } else if(friend.getClass().equals(NewFriends.class)) {
            System.out.println("Drink and NewFriends") ;
         }
      }
   }
}
