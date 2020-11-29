package com.leofee.proxy.jdk.target;

/**
 * @author leofee
 */
public class HalloImpl implements Hallo {

    @Override
    public void hello() {
        System.out.println("hello jdk proxy!");
    }
}
