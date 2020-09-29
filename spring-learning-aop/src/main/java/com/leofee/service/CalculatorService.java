package com.leofee.service;

import com.leofee.annotation.LogPrint;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int div(int i, int j) {
        return i/j;
    }

    @LogPrint
    public int add(int i, int j) {
        return i + j;
    }
}
