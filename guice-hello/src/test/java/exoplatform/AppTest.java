package exoplatform;

import com.google.inject.Guice;
import com.google.inject.Injector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    /**
     * 
     */
    public void testIoCContructor() 
    {
        Hello hello = new Hello();
        
        Injector injector = Guice.createInjector();
        injector.injectMembers(hello);
  
        hello.helloWorld.sayHelloWorld();
        
        assertEquals("Hello World!", hello.helloWorld.sayHelloWorld());
    }
}
