package com.leofee.definition;

public class MyAnnotationBeanDefinition extends MyBeanDefinition {

    public MyAnnotationBeanDefinition(Class<?> beanClass, String beanName) {
        super(beanClass, beanName);
    }

    public MyAnnotationBeanDefinition(Class<?> beanClass) {
        super(beanClass);
    }
}
