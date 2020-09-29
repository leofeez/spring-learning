package lifecycle.aware;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = ApplicationContextAwareConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationContextAwareTest {

    @Test
    public void testApplicationContextAware() {
    }
}
