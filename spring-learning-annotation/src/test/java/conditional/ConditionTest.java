package conditional;

import anno.ConditionConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ConditionTest {

    @Test
    public void getLinux() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}