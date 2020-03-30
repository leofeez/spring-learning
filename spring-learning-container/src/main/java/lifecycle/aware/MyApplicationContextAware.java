package lifecycle.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * @author leofee
 */
public class MyApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("set my applicationContext !");

        this.applicationContext = applicationContext;

        Environment environment = this.applicationContext.getEnvironment();
        System.out.println("MyApplicationContextAware.setApplicationContext.os.name" + environment.getProperty("os.name"));
    }
}
