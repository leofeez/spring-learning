package lifecycle.xml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author leofee
 */
public class XmlAppCtxTest {

    @Test
    public void xml(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Object leofee = context.getBean("leofee");
        System.out.println(leofee);
    }
}
