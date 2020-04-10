package lifecycle.aware;


import lifecycle.aware.api.TransportationApi;
import lifecycle.constants.TransportationType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextAwareTest {

    @Test
    public void testApplicationContextAware() {
        // 容器启动
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextAwareConfig.class);

        // 根据类型获取到对应实现类
        TransportationApi transportationApi = TransportationAware.getTransportation(TransportationType.BIKE);
        transportationApi.run();
    }
}
