package com.leofee.lifecycle.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Car implements Serializable {

    public Car() {
        System.out.println("Car constructor.......");
    }

    public void init() {
        System.out.println("Car initial.......");
    }

    public void destroy() {
        System.out.println("Car destroyed ......");
    }

    private BigDecimal carPrice;

    private String carBrand;
}
