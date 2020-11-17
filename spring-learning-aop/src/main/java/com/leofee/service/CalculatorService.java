package com.leofee.service;

import com.leofee.annotation.LogPrint;
import com.leofee.annotation.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Autowired
    private OrderService orderService;

    @LogPrint
    public int div(int i, int j) {
        return i/j;
    }

    @Timer
    @LogPrint
    public int add(int i, int j) {
        return i + j;
    }
}
