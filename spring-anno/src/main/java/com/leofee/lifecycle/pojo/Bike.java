package com.leofee.lifecycle.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Bike implements InitializingBean, DisposableBean {

    public Bike() {
        System.out.println("Bike 开始实例化.......");
    }

    public void destroy() throws Exception {
        System.out.println("Bike destroyed.......");    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Bike afterPropertiesSet.......");
    }
}
