package anno.Import;

import anno.Import.selector.MyImportSelector;
import org.springframework.context.annotation.*;

/**
 * <li>{@link Import @Import} : 快速给容器导入一个组件, 如果不指定 bean id 则默认为全类名
 * <li>{@link ImportSelector} : 返回需要注册到容器中的bean 的全类名的数组
 * <li>{@link ImportBeanDefinitionRegistrar} : 支持手动添加组件到容器中，所有bean的注册可以使用BeanDefinition
 * @author leofee
 */
@EnableMyAnnotation
@EnableAspectJAutoProxy
@Import({MyImportSelector.class})
@Configuration
public class ImportConfig {
}
