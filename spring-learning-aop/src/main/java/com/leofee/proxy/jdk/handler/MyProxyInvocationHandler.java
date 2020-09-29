package com.leofee.proxy.jdk.handler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author leofee
 */
@Slf4j
public class MyProxyInvocationHandler implements InvocationHandler {

    private final Object target;

    public MyProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("JDK 代理方法执行开始......proxy: {} , method: {}", proxy.getClass(), method.getName());
        }
        Object result = method.invoke(target, args);
        if (log.isDebugEnabled()) {
            log.debug("JDK 代理方法执行结束......");
        }
        return result;
    }
}
