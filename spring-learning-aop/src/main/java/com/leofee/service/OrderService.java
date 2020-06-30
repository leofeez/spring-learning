package com.leofee.service;

import com.leofee.annotation.LogPrint;
import com.leofee.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @LogPrint
    public void createOrder() {
        orderDao.insert();
    }
}
