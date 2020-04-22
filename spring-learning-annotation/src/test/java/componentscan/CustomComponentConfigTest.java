package componentscan;

import anno.componentscan.config.CustomComponentConfig;
import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@ContextConfiguration(classes = CustomComponentConfig.class)
public class CustomComponentConfigTest extends BaseTest {

    @Test
    public void testCustomComponent() {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

}