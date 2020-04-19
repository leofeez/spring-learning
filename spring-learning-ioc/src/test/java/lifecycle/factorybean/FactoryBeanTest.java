package lifecycle.factorybean;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author leofee
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);

        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        Object targetBean = applicationContext.getBean("lifecycle.factorybean.MyFactoryBean");
        System.out.println("FactoryBean 产生的Bean实例为:" + targetBean.getClass());

        Object factoryBeanSelf = applicationContext.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "lifecycle.factorybean.MyFactoryBean");
        System.out.println("FactoryBean 本身实例为：" + factoryBeanSelf.getClass());
    }
}
