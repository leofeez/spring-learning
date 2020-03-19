package anno.componentscan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 自定义 TypeFilter 扫描
 */
@Configuration
@ComponentScan(basePackages = {"anno.componentscan.controller", "anno.componentscan.service"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)},
        useDefaultFilters = false)
public class CustomComponentConfig {
}
