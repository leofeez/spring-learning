package com.leofee.proxy.cglib.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author leofee
 */
@Slf4j
public class MyCglibInvocationHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("代理方式开始执行......");
        }
        Object result = methodProxy.invokeSuper(o, args);

        if (log.isDebugEnabled()) {
            log.debug("代理方法执行结束......");
        }
        return result;
    }
}
