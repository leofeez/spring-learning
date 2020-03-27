package bean;

import anno.bean.config.BeanAnnoConfig;
import anno.bean.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class BeanAnnoTest {

    @Test
    public void testBeanAnno() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanAnnoConfig.class);
        String[] beanNames = applicationContext.getBeanNamesForType(Person.class);
        System.out.println(Arrays.toString(beanNames));
    }

    @Test
    public void testBeanAnno2() {
        ApplicationContext application = new AnnotationConfigApplicationContext(BeanAnnoConfig.class);
        Person leofee = (Person) application.getBean("leofee");
        System.out.println(leofee);
    }
}
