package anno.componentscan.config;

import anno.componentscan.service.WorldService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackages = {"anno.componentscan.controller", "anno.componentscan.service"},
        useDefaultFilters = true,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WorldService.class})})
@Configuration
public class ExcludeFilterConfig {
}
