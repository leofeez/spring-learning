package com.leofee.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author leofee
 */
@Slf4j
@Aspect
public class TimerAspect {

    @Pointcut("@annotation(com.leofee.annotation.Timer)")
    public void pointCut() {}


    @Around("pointCut()")
    public Object timerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Class<?> clazz = proceedingJoinPoint.getSignature().getDeclaringType();
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info(clazz + "." + methodName + "开始执行，当前系统时间为：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(LocalDateTime.now()));

        Object result = proceedingJoinPoint.proceed();

        log.info(clazz + "." + methodName + "执行结束，当前系统时间为：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(LocalDateTime.now()));
        return result;
    }
}
