package lifecycle.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycleConfigTest {

    @Test
    public void getBenz() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);

        System.out.println("IOC 容器创建完成......");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        // factory bean 生成的真实目标Bean是在容器创建完成后才开始实例化
        Object myFactoryBean = applicationContext.getBean("lifecycle.factorybean.MyFactoryBean");
        System.out.println("myFactoryBean:" + myFactoryBean.getClass());

        System.out.println("IOC 容器准备销毁......");
        applicationContext.close();
    }
}