package lifecycle.dependcycle;

import lifecycle.dependcycle.config.DependCycleConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author leofee
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DependCycleConfig.class)
public class DependCycleTest {

    @Test
    public void testDependCycle() {

    }
}
