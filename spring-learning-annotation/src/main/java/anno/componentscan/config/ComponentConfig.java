package anno.componentscan.config;

import anno.componentscan.annotation.MyController;
import anno.componentscan.controller.WorldController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"anno.componentscan.controller"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {MyController.class})},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WorldController.class}),
                          @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})},
        useDefaultFilters = false)
public class ComponentConfig {
}
