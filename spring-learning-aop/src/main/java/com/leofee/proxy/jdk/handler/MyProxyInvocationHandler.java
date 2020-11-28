package com.leofee.proxy.jdk.handler;

import com.leofee.proxy.jdk.MyJdkProxyApi;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author leofee
 */
@Slf4j
public class MyProxyInvocationHandler implements InvocationHandler {

    private final MyJdkProxyApi target;

    public MyProxyInvocationHandler(MyJdkProxyApi target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (log.isInfoEnabled()) {
            log.info("JDK 代理方法执行开始......proxy: {} , method: {}", proxy.getClass(), method.getName());
        }
        Object result = method.invoke(target, args);
        if (log.isInfoEnabled()) {
            log.info("JDK 代理方法执行结束......");
        }
        return result;
    }

    public static MyJdkProxyApi getInstance(MyJdkProxyApi target) {
        InvocationHandler handler = new MyProxyInvocationHandler(target);
        return (MyJdkProxyApi) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
