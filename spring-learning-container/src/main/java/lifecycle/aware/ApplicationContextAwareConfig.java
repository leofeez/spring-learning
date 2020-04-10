package lifecycle.aware;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author leofee
 */
@Import(value = {MyApplicationContextAware.class, TransportationAware.class})
@ComponentScan(basePackages = "lifecycle.aware.api.impl")
@Configuration
public class ApplicationContextAwareConfig {
}
