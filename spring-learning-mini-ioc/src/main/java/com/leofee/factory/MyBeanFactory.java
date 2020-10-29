package com.leofee.factory;

import java.util.function.Supplier;

public interface MyBeanFactory {

    Object getBean(String beanName);

    <T> T getBean(String beanName, Supplier<T> supplier);

    boolean containBean(String beanName);
}
