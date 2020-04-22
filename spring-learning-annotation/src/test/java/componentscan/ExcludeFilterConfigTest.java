package componentscan;

import anno.componentscan.config.ExcludeFilterConfig;
import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@ContextConfiguration(classes = ExcludeFilterConfig.class)
public class ExcludeFilterConfigTest extends BaseTest {

    @Test
    public void testExcludeFilter() {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

}