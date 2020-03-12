package anno.companentscan;

import anno.componentscan.config.ComponentConfig;
import anno.componentscan.config.CustomComponentConfig;
import anno.componentscan.config.PersonConfig;
import anno.componentscan.controller.WorldController;
import anno.componentscan.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    /**
     * 从 xml 配置方式中获取bean
     */
    @org.junit.Test
    public void getPersonFromXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Object person = context.getBean("person");

        System.out.println(person);
    }

    /**
     * 从 java 配置方式中获取bean
     *
     * 如果java配置方式不指定bean 的 id,
     * 则默认以实例化bean的方法名作为bean id
     */
    @org.junit.Test
    public void getPersonFromConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PersonConfig.class);

        System.out.println(context.getBean(Person.class));
        System.out.println(context.getBean("getPerson"));
    }

    @org.junit.Test
    public void getBeanDefinitions() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersonConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    /**
     * 包含
     * {@code @ComponentScan} 可以利用 filter 特性进行 include 和 exclude
     *
     */
    @org.junit.Test
    public void getBeanDefinitionsInclude() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    /**
     * 排除
     * exclude {@link WorldController}
     */
    @org.junit.Test
    public void getBeanDefinitionsExclude() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    /**
     * 排除
     * exclude {@link WorldController}
     */
    @org.junit.Test
    public void getBeanDefinitionsIncludeCustom() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CustomComponentConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}