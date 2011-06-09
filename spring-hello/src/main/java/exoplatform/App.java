package exoplatform;

import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application-context.xml"));
  
       Hello hello = (Hello) factory.getBean("hello");
       hello.helloWorld.sayHelloWorld();
    }
}
