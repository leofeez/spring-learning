package lifecycle.processor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "lifecycle.processor")
@Configuration
public class PostProcessorConfig {

    /**
     * 这种方式向Spring容器中添加BeanPostProcessor会导致当前的配置类过早的进行实例化，
     * 进一步导致当前的配置类不能有效的被所有的BeanPostProcessor所拦截处理。
     *
     * 因为当前@Bean 方法是实例方法，必须先实例化PostProcessorConfig才能对实例方法进行调用处理
     *
     * @see PostProcessorRegistrationDelegate.BeanPostProcessorChecker
     */
    @Bean
    BeanPostProcessor getEarlyBeanPostProcessor() {
        return new MyEarlyBeanPostProcessor();
    }

    /**
     * 用static方法可以避免上述情况的发生，因为static方法是属于当前类，不是属性当前类的实例
     *
     * @see ConstructorResolver#instantiateUsingFactoryMethod
     */
    @Bean
    static BeanPostProcessor earlyBeanPostProcessor() {
        return new MyEarlyBeanPostProcessor();
    }

}