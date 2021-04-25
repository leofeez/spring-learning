package com.leofee.service;

import com.leofee.proxy.cglib.Hallo;
import com.leofee.proxy.cglib.handler.MyCglibInvocationHandler;
import com.leofee.proxy.jdk.handler.MyProxyInvocationHandler;
import com.leofee.proxy.jdk.handler.StaticProxyHandler;
import com.leofee.proxy.jdk.target.HalloImpl;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author leofee
 */
public class ProxyTest {

    @Test
    public void testStaticJdkProxy() {
        // 目标类实例
        com.leofee.proxy.jdk.target.Hallo target = new HalloImpl();
        // 获取代理类对象
        StaticProxyHandler handler = new StaticProxyHandler(target);
        // 通过代理对象调用目标方法即可实现增强
        handler.hello();
    }

    @Test
    public void testJdkDynamicProxy() {
        // 目标类实例
        com.leofee.proxy.jdk.target.Hallo target = new HalloImpl();
        // 获取代理类对象
        com.leofee.proxy.jdk.target.Hallo proxyInstance = (com.leofee.proxy.jdk.target.Hallo)MyProxyInvocationHandler.newProxyInstance(target);
        // 通过代理对象调用目标方法即可实现增强
        proxyInstance.hello();
    }

    @Test
    public void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类
        enhancer.setSuperclass(Hallo.class);
        // 设置方法的回调
        enhancer.setCallback(new MyCglibInvocationHandler());
        // 创建代理对象
        Hallo proxyInstance = (Hallo) enhancer.create();
        // 执行代理方法
        proxyInstance.hello();
    }
}
