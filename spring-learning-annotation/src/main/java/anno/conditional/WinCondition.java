package anno.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 自定义条件
 */
public class WinCondition implements Condition {

    /**
     * @param context 判断条件可以使用的上下文（环境）
     * @param metadata 注解的信息
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 自定义的条件

        // 获取当前IOC容器正在使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取当前的环境变量 如：系统是否是windows
        Environment environment = context.getEnvironment();

        String osName = environment.getProperty("os.name");
        System.out.println("当前的操作系统为 " + osName);

        // 判断当前系统是不是windows
        return osName != null && osName.contains("Windows");
    }
}
