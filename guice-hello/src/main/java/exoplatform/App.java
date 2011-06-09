package exoplatform;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Hello hello = new Hello();
 
        Injector injector = Guice.createInjector();
        injector.injectMembers(hello);

        hello.helloWorld.sayHelloWorld();
    }
}
