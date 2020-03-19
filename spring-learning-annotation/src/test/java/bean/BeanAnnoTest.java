package bean;

import anno.bean.config.BeanAnnoConfig;
import anno.bean.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class BeanAnnoTest {

    @Test
    public void testBeanAnno1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanAnnoConfig.class);
        String[] beanNames = applicationContext.getBeanNamesForType(Person.class);
        System.out.println(Arrays.toString(beanNames));
    }
}
