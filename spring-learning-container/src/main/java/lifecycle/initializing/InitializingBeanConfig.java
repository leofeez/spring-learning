package lifecycle.initializing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializingBeanConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MyInitializingBean getMyInitializingBean() {
        MyInitializingBean myInitializingBean = new MyInitializingBean();
        return myInitializingBean;
    }
}
