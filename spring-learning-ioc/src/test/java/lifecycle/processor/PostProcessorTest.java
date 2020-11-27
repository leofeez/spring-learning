package lifecycle.processor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class PostProcessorTest {

    @Test
    public void testPostProcessor() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PostProcessorConfig.class);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(PostProcessorConfig.class);
        System.out.println("beanNamesForType = " + Arrays.toString(beanNamesForType));
    }

    @Test
    public void addBeanPostProcessor() {
        ApplicationContext applicationContext = new MyApplicationContext(PostProcessorConfig.class);
    }
}
