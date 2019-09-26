package com.leofee.lifecycle.pojo;

public class Train {

    public Train() {
        System.out.println("Train 开始实例化......");
    }

    public void init() {
        System.out.println("Train 开始初始化......");
    }

    public void destroy() {
        System.out.println("Train 开始销毁......");
    }
}
