package componentscan;

import anno.componentscan.config.IncludeFilterConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IncludeFilterConfigTest {

    @Test
    public void testIncludeFilter() {
        ApplicationContext app = new AnnotationConfigApplicationContext(IncludeFilterConfig.class);

        String[] beanDefinitionNames = app.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}