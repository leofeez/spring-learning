package com.leofee.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

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
        System.out.println("method: " + methodName + "准备运行, 参数列表为：{" + Arrays.asList(joinPoint.getArgs())+ "}");
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("除法结束。。。。。。");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法正常结束，返回值为：" + result);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("除法计算异常，异常信息为：" + exception);
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知, 开始执行....");
        Object result = joinPoint.proceed();
        System.out.println("环绕通知, 开始结束....");

        return result;
    }
}
