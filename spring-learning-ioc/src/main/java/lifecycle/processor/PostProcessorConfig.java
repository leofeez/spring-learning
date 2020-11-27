package lifecycle.processor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pojo.Parent;
import pojo.Son;

@ComponentScan(basePackages = "lifecycle.processor")
@Configuration
public class PostProcessorConfig {

    /**
     * 这种方式向Spring容器中添加BeanPostProcessor会导致当前的配置类过早的进行实例化，
     * 进一步导致当前的配置类不能有效的被所有的BeanPostProcessor所拦截处理。
     * 因为当前@Bean 方法是实例方法，必须先实例化PostProcessorConfig才能对实例方法进行调用处理
     *
     *具体原因为：
     * <p> 在解析@Bean注解的时候，Spring会给对应的需要注册到容器的中的BeanDefinition设置factoryMethod 和 factoryBeanName，
     *  <pre>
     * ConfigurationClassBeanDefinitionReader.loadBeanDefinitionsForBeanMethod
     *
     * if (metadata.isStatic()) {
     *      // 静态的 @Bean 方法
     *      // static @Bean method
     * 	    beanDef.setBeanClassName(configClass.getMetadata().getClassName());
     * 	    beanDef.setFactoryMethodName(methodName);
     * }
     * else {
     *      // 实例的 @Bean 方法
     * 	    // instance @Bean method
     * 	    beanDef.setFactoryBeanName(configClass.getBeanName());
     * 	    beanDef.setUniqueFactoryMethodName(methodName);
     * }
     *  </pre>
     * 然后在注册并实例化BeanPostProcessor时：
     * <pre>
     *     AbstractAutowireCapableBeanFactory#createBeanInstance
     *
     *     // factoryMethodName 不为空，则利用factoryMethod进行实例化
     *     if (mbd.getFactoryMethodName() != null) {
     * 			return instantiateUsingFactoryMethod(beanName, mbd, args);
     *     }
     * </pre>
     * 利用factoryMethod实例化：
     * <pre>
     *     ConstructorResolver#instantiateUsingFactoryMethod
     * if (factoryBeanName != null) {
     *      if (factoryBeanName.equals(beanName)) {
     * 				throw new BeanDefinitionStoreException(mbd.getResourceDescription(), beanName,
     * 						"factory-bean reference points back to the same bean definition");
     *                        }
     *          // 这一步如果factoryBeanName不为空，则需要对factoryBean进行预先实例化
     *          // 所以就会导致配置类比BeanPostProcessor实例化早
     * 			factoryBean = this.beanFactory.getBean(factoryBeanName);
     * 			if (mbd.isSingleton() && this.beanFactory.containsSingleton(beanName)) {
     * 				throw new ImplicitlyAppearedSingletonException();
     *            }
     * 			factoryClass = factoryBean.getClass();
     * 			isStatic = false;
     * 		}
     * 		else {
     * 			// It's a static factory method on the bean class.
     * 			if (!mbd.hasBeanClass()) {
     * 				throw new BeanDefinitionStoreException(mbd.getResourceDescription(), beanName,
     * 						"bean definition declares neither a bean class nor a factory-bean reference");
     *          }
     * 			factoryBean = null;
     * 			factoryClass = mbd.getBeanClass();
     * 			isStatic = true;
     *      }
     * }
     *
     * </pre>
     *
     * @see PostProcessorRegistrationDelegate.BeanPostProcessorChecker
     */
//    @Bean
//    BeanPostProcessor getEarlyBeanPostProcessor() {
//        return new MyEarlyBeanPostProcessor();
//    }

    /**
     * 用static方法可以避免上述情况的发生，因为static方法是属于当前类，不是属性当前类的实例
     *
     * @see ConstructorResolver#instantiateUsingFactoryMethod
     */
    @Bean
    static BeanPostProcessor earlyBeanPostProcessor() {
        return new MyEarlyBeanPostProcessor();
    }

    @Bean
    BeanDefinitionRegistryPostProcessor getEarlyBeanDefinitionRegistryPostProcessor() {
        return new MyEarlyBeanDefinitionRegistryPostProcessor();
    }

    @Bean
    Parent parent() {
        return new Parent(son());
    }

    @Bean
    Son son() {
        return new Son();
    }

}