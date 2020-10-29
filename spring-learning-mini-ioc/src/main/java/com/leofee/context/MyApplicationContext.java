package com.leofee.context;

import com.leofee.factory.MyBeanFactory;

public interface MyApplicationContext extends MyBeanFactory {

    /**
     * 容器刷新
     */
    void refresh();

    MyBeanFactory getBeanFactory();

}
