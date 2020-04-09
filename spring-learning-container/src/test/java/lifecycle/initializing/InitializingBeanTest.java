package lifecycle.initializing;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InitializingBeanTest {

    @Test
    public void testInitializingBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitializingBeanConfig.class);
    }
}
