package com.leofee.context;

import com.leofee.factory.BeanFactory;

public interface ApplicationContext extends BeanFactory {

    /**
     * 容器刷新
     */
    void refresh();

    BeanFactory getBeanFactory();

}
