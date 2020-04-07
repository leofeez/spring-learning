package anno.Import.config;

import anno.Import.factorybean.MyFactoryBean;
import anno.Import.pojo.Dog;
import anno.Import.register.MyBeanDefinitionRegistrar;
import anno.Import.selector.MyImportSelector;
import anno.componentscan.Person;
import anno.ConditionConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;

/**
 * {@link Import} : 快速给容器导入一个组件, 如果不指定 bean id 则默认为全类名
 * {@link ImportSelector} : 返回需要注册到容器中的bean 的全类名的数组
 * {@link ImportBeanDefinitionRegistrar} : 支持手动添加组件到容器中，所有bean的注册可以使用BeanDefinition
 */
@Import({ConditionConfig.class,
        Dog.class, MyImportSelector.class,
        MyBeanDefinitionRegistrar.class,
        MyFactoryBean.class})
@Configuration
public class ImportConfig {

    @Bean("nick")
    public Person getPerson() {
        Person person = new Person();
        person.setName("nick");
        return person;
    }
}
