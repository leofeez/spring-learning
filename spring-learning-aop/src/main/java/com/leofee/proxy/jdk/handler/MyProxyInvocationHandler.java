package com.leofee.proxy.jdk.handler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK动态代理
 *
 * <ul>
 * <li>1. Handler 实现 {@link InvocationHandler}
 * <li>2. Handler 构造方法将目标类的实例传入
 * <li>3. 当前的ClassLoader，{@code target.getClass().getClassLoader()}
 * <li>4. 目标类实现的接口，{@code target.getClass().getInterfaces()}
 * <li>5. 利用Proxy.newProxyInstance创建代理类实例
 *
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
        log.info("JDK 动态代理方法执行开始......proxy: {} , method: {}", proxy.getClass(), method.getName());
        Object result = method.invoke(target, args);
        log.info("JDK 动态代理方法执行结束......");
        return result;
    }

    /**
     * 创建代理类实例
     *
     * @param target 目标类实例
     * @return 代理类实例
     */
    public static Object newProxyInstance(Object target) {
        InvocationHandler handler = new MyProxyInvocationHandler(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
