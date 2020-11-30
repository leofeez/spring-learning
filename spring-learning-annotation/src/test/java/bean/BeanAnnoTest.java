package bean;

import anno.bean.BeanAnnoConfig;
import base.BaseTest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import pojo.Person;

import java.util.Arrays;

@ContextConfiguration(classes = BeanAnnoConfig.class)
public class BeanAnnoTest extends BaseTest {

    @Test
    public void testBeanAnno() {
        String[] beanNames = applicationContext.getBeanNamesForType(Person.class);
        System.out.println(Arrays.toString(beanNames));
    }

    @Test
    public void testBeanAnno2() {
        Person leofee = (Person) applicationContext.getBean("jack");
        System.out.println(ToStringBuilder.reflectionToString(leofee, ToStringStyle.JSON_STYLE));
    }
}
