package com.leofee.proxy.jdk.handler;

import com.leofee.proxy.jdk.target.Hallo;

/**
 * @author leofee
 * @date 2020/11/29
 */
public class StaticProxyHandler implements Hallo {

    private final Hallo target;

    public StaticProxyHandler(Hallo target) {
        this.target = target;
    }

    @Override
    public void hello() {

        System.out.println("static jdk proxy before say hello!");

        target.hello();

        System.out.println("static jdk proxy after say hello!");
    }
}
