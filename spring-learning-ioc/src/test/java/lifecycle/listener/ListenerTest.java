package lifecycle.listener;

import lifecycle.listener.event.MyApplicationEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author leofee
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ListenerConfig.class)
public class ListenerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        String source = "my application event content";
        MyApplicationEvent event = new MyApplicationEvent(source);
        applicationContext.publishEvent(event);
    }
}
