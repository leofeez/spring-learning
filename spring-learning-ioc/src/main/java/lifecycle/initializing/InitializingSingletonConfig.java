package lifecycle.initializing;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author leofee
 */
@ComponentScan(basePackages = "service")
@Import(value = AutowiredStaticInitializingSingleton.class)
@Configuration
public class InitializingSingletonConfig {
}
