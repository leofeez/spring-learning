package com.leofee.anno.Import;

import com.leofee.anno.Import.config.ImportConfig;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportConfigTest {

    @Test
    public void getBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        // 这里获取到的bean其实是 Monkey
        // 如果需要获取MyFactoryBean 需要在beanName 前加上&
        System.out.println(applicationContext.getBean("MyFactoryBean"));
        System.out.println(applicationContext.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "MyFactoryBean"));
    }
}