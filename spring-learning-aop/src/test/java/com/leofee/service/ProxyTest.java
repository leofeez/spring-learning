package com.leofee.service;

import com.leofee.proxy.cglib.MyCglibProxy;
import com.leofee.proxy.cglib.handler.MyCglibInvocationHandler;
import com.leofee.proxy.jdk.handler.MyProxyInvocationHandler;
import com.leofee.proxy.jdk.handler.StaticProxyHandler;
import com.leofee.proxy.jdk.target.Hallo;
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
        Hallo target = new HalloImpl();
        // 获取代理类对象
        StaticProxyHandler handler = new StaticProxyHandler(target);
        // 通过代理对象调用目标方法即可实现增强
        handler.hello();
    }

    @Test
    public void testJdkDynamicProxy() {
        // 目标类实例
        Hallo target = new HalloImpl();
        // 获取代理类对象
        Hallo proxyInstance = (Hallo)MyProxyInvocationHandler.newProxyInstance(target);
        // 通过代理对象调用目标方法即可实现增强
        proxyInstance.hello();
    }

    @Test
    public void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyCglibProxy.class);
        enhancer.setCallback(new MyCglibInvocationHandler());
        MyCglibProxy proxyInstance = (MyCglibProxy) enhancer.create();
        proxyInstance.hello();
    }
}
