package componentscan;

import anno.componentscan.config.IncludeFilterConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class IncludeFilterConfigTest {

    @Test
    public void testIncludeFilter() {
        ApplicationContext app = new AnnotationConfigApplicationContext(IncludeFilterConfig.class);
        Arrays.stream(app.getBeanDefinitionNames()).forEach(System.out::println);
    }
}