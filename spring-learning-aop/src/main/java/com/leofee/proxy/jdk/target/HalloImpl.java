package com.leofee.proxy.jdk.target;

import java.util.concurrent.TimeUnit;

/**
 * @author leofee
 */
public class HalloImpl implements Hallo {

    @Override
    public void hello() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world!");
    }
}
