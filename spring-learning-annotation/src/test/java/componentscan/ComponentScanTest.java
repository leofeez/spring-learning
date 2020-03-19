package componentscan;

import anno.componentscan.config.ComponentConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ComponentScanTest {

    @Test
    public void test_1() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ComponentConfig.class);
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
    }
}
