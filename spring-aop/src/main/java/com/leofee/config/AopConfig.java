package com.leofee.config;

import com.leofee.aspects.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "com.leofee.service")
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean
    public LogAspect getLogAspect() {
        return new LogAspect();
    }
}
