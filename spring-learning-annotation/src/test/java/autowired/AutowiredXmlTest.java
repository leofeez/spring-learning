package autowired;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author leofee
 */
public class AutowiredXmlTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("AutoConfigurationContext.xml");
    }
}
