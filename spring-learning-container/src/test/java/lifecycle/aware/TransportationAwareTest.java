package lifecycle.aware;


import lifecycle.api.Transportation;
import lifecycle.config.BeanLifeCycleConfig;
import lifecycle.constants.TransportationType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransportationAwareTest {

    @Test
    public void testTransportationRun() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);

        Transportation transportation = TransportationAware.getTransportation(TransportationType.BIKE);
        transportation.run();
    }
}
