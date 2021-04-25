package com.leofee.proxy.jdk.handler;

import com.leofee.proxy.jdk.target.Hallo;

import java.time.LocalDateTime;

/**
 * 代理类
 * @author leofee
 * @date 2020/11/29
 */
public class StaticProxyHandler implements Hallo {

    /** 真正的目标类实例*/
    private final Hallo target;

    public StaticProxyHandler(Hallo target) {
        this.target = target;
    }

    /**
     * 实现同样接口方法，在这里对目标方法进行增强
     */
    @Override
    public void hello() {
        System.out.println("static jdk proxy before say hello! time now is " + LocalDateTime.now());

        // 调用目标方法
        target.hello();

        System.out.println("static jdk proxy after say hello!" + LocalDateTime.now());
    }
}
