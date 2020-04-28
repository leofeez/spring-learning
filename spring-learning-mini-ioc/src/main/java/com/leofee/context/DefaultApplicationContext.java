package com.leofee.context;

/**
 * Application context 的基本实现类
 *
 * @author leofee
 */
public class DefaultApplicationContext extends AbstractApplicationContext {

    public DefaultApplicationContext() {
        super();
        refresh();
    }
}
