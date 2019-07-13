package com.leofee.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspect {

    @Pointcut("execution(* com.leofee.service.CalculatorService.div(..))")
    public void pointCut() {

    }

    /**
     * 在目标方法执行前切入
     */
    @Before("pointCut()")
    public void logStart() {
        System.out.println("除法准备运行, 参数列表为：{}");
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("除法结束。。。。。。");
    }

    @AfterReturning("pointCut()")
    public void logReturn() {
        System.out.println("除法正常结束，返回值为：");
    }

    @AfterThrowing("pointCut()")
    public void logException() {
        System.out.println("除法计算异常，异常信息为：");
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知, 开始执行....");
        Object result = joinPoint.proceed();
        System.out.println("环绕通知, 开始结束....");

        return result;
    }
}
