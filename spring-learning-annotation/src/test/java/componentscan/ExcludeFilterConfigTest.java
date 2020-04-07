package componentscan;

import anno.componentscan.config.ExcludeFilterConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ExcludeFilterConfigTest {

    @Test
    public void testExcludeFilter() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ExcludeFilterConfig.class);
        Arrays.stream(app.getBeanDefinitionNames()).forEach(System.out::println);
    }

}