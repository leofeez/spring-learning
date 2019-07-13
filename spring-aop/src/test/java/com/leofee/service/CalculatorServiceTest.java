package com.leofee.service;

import com.leofee.config.AopConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class CalculatorServiceTest {

    @Test
    public void div() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);

        CalculatorService calculatorService = applicationContext.getBean(CalculatorService.class);

        calculatorService.div(1, 2);
    }
}