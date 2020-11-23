package lifecycle.processor;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author leofee
 */
public class MyApplicationContext extends AnnotationConfigApplicationContext {

    public MyApplicationContext(Class<?>... annotatedClasses) {
        super(annotatedClasses);
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("手工注册BeanPostProcessor前的数量：" + beanFactory.getBeanPostProcessorCount());

        // 手工注册 BeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());

        System.out.println("手工注册BeanPostProcessor前的数量：" + beanFactory.getBeanPostProcessorCount());
    }
}
