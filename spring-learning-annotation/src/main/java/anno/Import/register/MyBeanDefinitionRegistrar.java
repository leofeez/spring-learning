package anno.Import.register;

import anno.Import.pojo.Mouse;
import anno.Import.pojo.Tiger;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author leofee
 */
public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 注解信息
     * @param registry BeanDefinition注册类, 把所有需要添加到容器中的bean加入
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 场景：只有当前提 bean 已经在容器中，这里才去实例化当前bean
        boolean exists = registry.containsBeanDefinition(Tiger.class.getName());
        if (exists) {
            // 这里注册bean，需要给bean进行一次封装
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Mouse.class);
            // 注册我们需要的Bean(组件)
            registry.registerBeanDefinition(Mouse.class.getName(), beanDefinition);
        }
    }
}
