package componentscan;

import anno.componentscan.config.CustomComponentConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class CustomComponentConfigTest {

    @Test
    public void testCustomComponent() {
        ApplicationContext app = new AnnotationConfigApplicationContext(CustomComponentConfig.class);
        Arrays.stream(app.getBeanDefinitionNames()).forEach(System.out::println);
    }

}