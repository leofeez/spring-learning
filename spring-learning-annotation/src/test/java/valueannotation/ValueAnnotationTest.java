package valueannotation;

import anno.value.ValueConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@ContextConfiguration(classes = ValueConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ValueAnnotationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testValue() {

        Environment environment = applicationContext.getEnvironment();
        String propertyValue = environment.getProperty("spring.learning.annotation");
        System.out.println("propertyValue = " + propertyValue);

        Map<String, ValueConfig> beans = applicationContext.getBeansOfType(ValueConfig.class);

        beans.forEach((key, value) -> System.out.println("value = " + value));
    }
}
