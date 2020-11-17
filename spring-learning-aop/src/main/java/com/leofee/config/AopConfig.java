package com.leofee.config;

import com.leofee.aspects.LogAspect;
import com.leofee.aspects.TimerAspect;
import com.leofee.service.CalculatorService;
import com.leofee.service.OrderService;
import org.springframework.context.annotation.*;

/**
 * {@code excludeFilters} 为了防止 {@code OrderService} 干扰，所以过滤掉。
 * @author leofee
 */
@ComponentScan(basePackageClasses = {CalculatorService.class, OrderService.class})
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean
    public LogAspect getLogAspect() {
        return new LogAspect();
    }

    @Bean
    public TimerAspect getTimerAspect() {
        return new TimerAspect();
    }
}
