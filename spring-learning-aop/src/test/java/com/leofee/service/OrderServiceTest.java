package com.leofee.service;

import com.leofee.config.TransactionConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionConfig.class);

        OrderService orderService = applicationContext.getBean(OrderService.class);

        orderService.createOrder();
    }
}