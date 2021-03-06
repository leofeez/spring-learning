package com.leofee.service;

import com.leofee.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDao orderDao;

    @Autowired
    private CalculatorService calculatorService;


    public OrderService(@Autowired(required = false)OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void createOrder() {
        orderDao.insert();
    }
}
