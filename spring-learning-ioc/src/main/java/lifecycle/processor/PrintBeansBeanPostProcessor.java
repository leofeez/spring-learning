package lifecycle.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PrintBeansBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        // 在bean初始化之前进行前置处理
        // 在 init method 调用之前调用
        System.out.println("postProcessBeforeInitialization bean = [" + bean + "], beanName = [" + beanName + "]");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // 在bean初始化之后进行前置处理
        // 在 init method 调用之后调用
        System.out.println("postProcessAfterInitialization bean = [" + bean + "], beanName = [" + beanName + "]");
        return bean;
    }

}
