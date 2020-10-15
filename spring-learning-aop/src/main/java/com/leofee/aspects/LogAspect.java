package com.leofee.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Slf4j
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.leofee.service.CalculatorService.div(..))")
    public void pointCut() {

    }

    /**
     * 在目标方法执行前切入
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        joinPoint.getSignature();
        if (log.isDebugEnabled()) {
            log.debug("method: " + methodName + "准备运行, 参数列表为：{" + Arrays.asList(joinPoint.getArgs())+ "}");
        }
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        if (log.isDebugEnabled()) {
            log.debug("method: {} 执行结束", joinPoint.getSignature().getName());
        }
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        if (log.isDebugEnabled()) {
            log.debug("method: {} 执行结束, 返回值为：{}", joinPoint.getSignature().getName(), result);
        }
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        if (log.isDebugEnabled()) {
            log.debug("method: {} 执行异常, 异常信息为：{}", joinPoint.getSignature().getName(), exception);
        }
    }

    /**
     * 利用注解的方式切入
     */
    @Around("@annotation(com.leofee.annotation.LogPrint)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Class<?> declaringType = joinPoint.getSignature().getDeclaringType();

        joinPoint.getArgs();

        System.out.println(declaringType + "." + methodName + ", 开始执行....,参数列表为：" + Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        System.out.println(declaringType + "." + methodName + ", 执行结束....,返回值为：" + result);

        return result;
    }
}
