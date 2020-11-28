package com.leofee.service;

import com.leofee.proxy.cglib.MyCglibProxy;
import com.leofee.proxy.cglib.handler.MyCglibInvocationHandler;
import com.leofee.proxy.jdk.handler.MyProxyInvocationHandler;
import com.leofee.proxy.jdk.MyJdkProxyApi;
import com.leofee.proxy.jdk.MyJdkProxyApiImpl;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author leofee
 */
public class ProxyTest {

    @Test
    public void testJdkProxy() {
        MyJdkProxyApi target = new MyJdkProxyApiImpl();
        InvocationHandler handler = new MyProxyInvocationHandler(target);
        MyJdkProxyApi proxyInstance = (MyJdkProxyApi) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        proxyInstance.hello();

        MyProxyInvocationHandler.getInstance(target).hello();
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
