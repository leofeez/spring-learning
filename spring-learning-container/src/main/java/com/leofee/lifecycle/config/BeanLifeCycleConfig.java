package com.leofee.lifecycle.config;

import com.leofee.lifecycle.pojo.Bike;
import com.leofee.lifecycle.pojo.Car;
import com.leofee.lifecycle.processor.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ComponentScan(basePackageClasses = {Bike.class, MyBeanPostProcessor.class})
public class BeanLifeCycleConfig {

    @Bean(name = "benz", initMethod = "init", destroyMethod = "destroy")
    public Car getBenz() {
        Car car = new Car();
        car.setCarBrand("Benz");
        car.setCarPrice(new BigDecimal(1000000));
        return car;
    }
}
