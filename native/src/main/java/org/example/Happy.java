package org.example;

/**
 * Hello world!
 *
 */
public class Happy 
{

   public native void printText() ;
   
   static 
   {
      System.loadLibrary("happy") ;
   }
   
   public static void main( String[] args )
   {
      Happy happy = new Happy() ;
      happy.printText() ;
   }
}
