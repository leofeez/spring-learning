package com.leofee.proxy.jdk;

/**
 * @author leofee
 */
public class MyJdkProxyApiImpl implements MyJdkProxyApi {
    @Override
    public void hello() {
        System.out.println("hello jdk proxy!");
    }
}
