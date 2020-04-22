package conditional;

import anno.conditional.ConditionConfig;
import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@ContextConfiguration(classes = ConditionConfig.class)
public class ConditionTest extends BaseTest {

    @Test
    public void getLinux() {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}