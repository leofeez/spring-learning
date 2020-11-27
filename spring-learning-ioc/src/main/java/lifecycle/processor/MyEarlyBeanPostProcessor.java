package lifecycle.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 用于测试自定义的 BeanPostProcessor过早实例化
 *
 * @author leofee
 */
public class MyEarlyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 在bean初始化之后进行前置处理
        // 在 init method 调用之后调用
        System.out.println("postProcessAfterInitialization bean = [" + bean + "], beanName = [" + beanName + "]");
        return bean;
    }
}
