package com.leofee.processor;

import com.leofee.factory.ConfigurableBeanFactory;

public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableBeanFactory beanFactory);
}
