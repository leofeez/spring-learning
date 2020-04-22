package componentscan;

import anno.componentscan.config.ComponentConfig;
import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@ContextConfiguration(classes = ComponentConfig.class)
public class ComponentScanTest extends BaseTest {

    @Test
    public void test_1() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
    }
}
