package anno.componentscan.config;

import anno.componentscan.annotation.MyController;
import anno.componentscan.controller.HelloController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = { "anno.componentscan.controller", "anno.componentscan.service"},
        useDefaultFilters = false,
        includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {HelloController.class}),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {MyController.class})})
@Configuration
public class IncludeFilterConfig {
}
