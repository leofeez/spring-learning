package anno.componentscan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "anno.componentscan.controller")
public class ComponentConfig {
}
