package com.leofee.definition;

public abstract class BeanDefinition {

    protected Class<?> beanClass;

    protected String beanName;

    public BeanDefinition(Class<?> beanClass, String beanName) {
        this.beanClass = beanClass;
        this.beanName = beanName;
    }

    public BeanDefinition(Class<?> beanClass) {
        this.beanName = beanClass.getName();
        this.beanClass = beanClass;
    }

    /**
     * 设置 bean class
     *
     * @param beanClass bean class
     */
    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 获取 bean 对象的 class
     *
     * @return bean 的 class
     */
    public Class<?> getBeanClass() {
        return this.beanClass;
    }

    /**
     * bean name
     *
     * @return bean name
     */
    public  String getBeanName() {
        return this.beanName;
    }
}
