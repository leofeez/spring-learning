package com.leofee.registry;

public interface MyBeanRegistry {

    /**
     * 根据class 注册到容器
     *
     * @param beanName bean 的名称
     * @param clazz    bean 对应的 class
     */
    void registerBean(String beanName, Class<?> clazz);

    /**
     * 根据实例注册到容器
     *
     * @param beanName bean 的名称
     * @param bean     bean实例
     */
    void registerBean(String beanName, Object bean);
}
