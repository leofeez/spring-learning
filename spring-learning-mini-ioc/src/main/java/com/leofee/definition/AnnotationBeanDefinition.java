package com.leofee.definition;

public class AnnotationBeanDefinition extends BeanDefinition {

    public AnnotationBeanDefinition(Class<?> beanClass, String beanName) {
        super(beanClass, beanName);
    }

    public AnnotationBeanDefinition(Class<?> beanClass) {
        super(beanClass);
    }
}
