package lifecycle.initializing;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import utils.ServiceUtils;

public class InitializingBeanTest {

    @Test
    public void testInitializingBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitializingBeanConfig.class);
    }


    @Test
    public void testAutowiredStatic() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitializingSingletonConfig.class);
        ServiceUtils.handleService();
    }
}
