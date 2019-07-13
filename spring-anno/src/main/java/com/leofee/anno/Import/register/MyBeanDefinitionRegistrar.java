package com.leofee.anno.Import.register;

import com.leofee.anno.Import.pojo.Mouse;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 注解信息
     * @param registry BeanDefinition注册类, 把所有需要添加到容器中的bean加入
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 场景：只有当前提 bean 已经在容器中，这里才去实例化当前bean

        boolean dogExists = registry.containsBeanDefinition("Dog");

        if (dogExists) {

            // 这里注册bean，需要给bean进行一次封装
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Mouse.class);

            // 注册
            registry.registerBeanDefinition("mouse", beanDefinition);
        }

    }
}
