package anno.Import.config;

import anno.Import.register.MyBeanDefinitionRegistrar;
import anno.Import.selector.MyImportSelector;
import org.springframework.context.annotation.*;

/**
 * {@link Import} : 快速给容器导入一个组件, 如果不指定 bean id 则默认为全类名
 * {@link ImportSelector} : 返回需要注册到容器中的bean 的全类名的数组
 * {@link ImportBeanDefinitionRegistrar} : 支持手动添加组件到容器中，所有bean的注册可以使用BeanDefinition
 * @author leofee
 */
@EnableAspectJAutoProxy
@Import({MyImportSelector.class, MyBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfig {
}
