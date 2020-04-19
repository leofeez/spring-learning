package lifecycle.processor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PostProcessorTest {

    @Test
    public void testPostProcessor() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PostProcessorConfig.class);
    }
}
