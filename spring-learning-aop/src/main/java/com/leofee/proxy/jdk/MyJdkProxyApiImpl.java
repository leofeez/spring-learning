package com.leofee.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author leofee
 */
public class MyJdkProxyApiImpl implements MyJdkProxyApi {
    @Override
    public void hello() {
        System.out.println("hello jdk proxy!");
    }
}
