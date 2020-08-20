package anno.Import;

import anno.Import.register.MyBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义注解，用于开启 {@link MyBeanDefinitionRegistrar} 的功能
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyBeanDefinitionRegistrar.class)
public @interface EnableMyAnnotation {
}
