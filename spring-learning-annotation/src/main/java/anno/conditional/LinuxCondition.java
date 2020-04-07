package anno.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现{@link org.springframework.context.annotation.Condition}
 */
public class LinuxCondition implements Condition {
    /**
     * 根据该方法返回值用于确定是否执行Bean 的实例化
     *
     * @param context 应用上下文的信息
     * @param metadata 注解的信息
     * @return true 则实例化Bean，否则 false
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 判读当前系统是不是 linux
        String osName = context.getEnvironment().getProperty("os.name");
        return osName != null && osName.contains("Linux");
    }
}
