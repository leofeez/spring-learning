package componentscan;

import anno.componentscan.config.IncludeFilterConfig;
import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@ContextConfiguration(classes = IncludeFilterConfig.class)
public class IncludeFilterConfigTest extends BaseTest {

    @Test
    public void testIncludeFilter() {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}