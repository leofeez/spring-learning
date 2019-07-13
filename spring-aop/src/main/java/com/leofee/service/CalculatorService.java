package com.leofee.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int div(int i, int j) {
        return i/j;
    }
}
