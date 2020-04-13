package lifecycle.aware;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextAwareTest {

    @Test
    public void testApplicationContextAware() {
        // 容器启动
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextAwareConfig.class);
    }
}
