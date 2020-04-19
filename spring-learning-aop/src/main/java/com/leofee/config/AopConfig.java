package com.leofee.config;

import com.leofee.aspects.LogAspect;
import com.leofee.service.CalculatorService;
import com.leofee.service.OrderService;
import org.springframework.context.annotation.*;

@ComponentScan(basePackageClasses = {CalculatorService.class},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = OrderService.class)})
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean
    public LogAspect getLogAspect() {
        return new LogAspect();
    }
}
