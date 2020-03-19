package componentscan;

import anno.componentscan.config.ExcludeFilterConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExcludeFilterConfigTest {

    @Test
    public void testExcludeFilter() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ExcludeFilterConfig.class);

        String[] beanDefinitionNames = app.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}