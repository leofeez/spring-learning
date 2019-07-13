package com.leofee.anno.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 判读当前系统是不是 linux
        String osName = context.getEnvironment().getProperty("os.name");
        return osName != null && osName.contains("Linux");
    }
}
