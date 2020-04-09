package lifecycle.factorybean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author leofee
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);

        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
