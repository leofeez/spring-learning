package lifecycle.xml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Parent;

/**
 * @author leofee
 */
public class XmlAppCtxTest {

    @Test
    public void xml(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Object leofee = context.getBean("leofee");
        Parent p = (Parent) context.getBean("parent");
        System.out.println(leofee);
        System.out.println(p);
    }
}
