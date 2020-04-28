package com.leofee.definition;

public interface BeanDefinition {

    /**
     * 设置 bean class
     *
     * @param beanClass bean class
     */
    void setBeanClass(Class<?> beanClass);

    /**
     * 获取 bean 对象的 class
     *
     * @return bean 的 class
     */
    Class<?> getBeanClass();

    /**
     * bean name
     *
     * @return bean name
     */
    String getBeanName();
}
