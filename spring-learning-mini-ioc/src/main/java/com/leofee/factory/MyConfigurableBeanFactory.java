package com.leofee.factory;

import com.leofee.registry.MyBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class MyConfigurableBeanFactory implements MyBeanFactory, MyBeanRegistry {

    private static final Map<String, Object> beanMap = new ConcurrentHashMap<>(256);

    @Override
    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String beanName, Supplier<T> supplier) {
        if (containBean(beanName)) {
            return (T) beanMap.get(beanName);
        }
        T bean = supplier.get();
        cacheBean(beanName, bean);
        return bean;
    }

    @Override
    public boolean containBean(String beanName) {
        return beanMap.containsKey(beanName);
    }

    @Override
    public void registerBean(String beanName, Class<?> clazz) {

    }

    @Override
    public void registerBean(String beanName, Object bean) {
        if (containBean(beanName)) {
            return;
        }
        cacheBean(beanName, bean);
    }

    private void cacheBean(String beanName, Object bean) {
        beanMap.put(beanName, bean);
    }
}
