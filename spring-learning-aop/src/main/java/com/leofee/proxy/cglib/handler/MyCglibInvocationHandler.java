package com.leofee.proxy.cglib.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author leofee
 */
@Slf4j
public class MyCglibInvocationHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib dynamic proxy before say hello! time now is: " + LocalDateTime.now());

        Object result = methodProxy.invokeSuper(o, args);

        System.out.println("cglib dynamic proxy after  say hello! time now is: " + LocalDateTime.now());
        return result;
    }
}
