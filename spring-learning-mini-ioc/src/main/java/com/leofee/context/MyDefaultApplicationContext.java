package com.leofee.context;

/**
 * Application context 的基本实现类
 *
 * @author leofee
 */
public class MyDefaultApplicationContext extends MyAbstractApplicationContext {

    public MyDefaultApplicationContext() {
        super();
        refresh();
    }
}
