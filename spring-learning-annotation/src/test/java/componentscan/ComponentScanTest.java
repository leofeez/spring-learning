package anno.componentscan;

import anno.componentscan.config.ComponentConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ComponentScanTest {

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(ComponentConfig.class);
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
    }
}
