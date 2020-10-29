package com.leofee.processor;

import com.leofee.factory.MyConfigurableBeanFactory;

public interface MyBeanFactoryPostProcessor {

    void postProcessBeanFactory(MyConfigurableBeanFactory beanFactory);
}
