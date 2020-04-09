package lifecycle.factorybean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author leofee
 */
@Configuration
@Import(value = {MyFactoryBean.class, MySmartFactoryBean.class})
public class FactoryBeanConfig {


}
