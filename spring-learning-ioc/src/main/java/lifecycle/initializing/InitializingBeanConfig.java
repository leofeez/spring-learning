package lifecycle.initializing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leofee
 */
@Configuration
public class InitializingBeanConfig {

    @Bean(name = "myInitializingBean")
    public MyInitializingBean getMyInitializingBean() {
        return new MyInitializingBean();
    }

    @Bean(name = "myCustomInitializingBean", initMethod = "init", destroyMethod = "destroy")
    public MyCustomInitMethodBean getMyCustomInitializingBean() {
        return new MyCustomInitMethodBean();
    }
}
