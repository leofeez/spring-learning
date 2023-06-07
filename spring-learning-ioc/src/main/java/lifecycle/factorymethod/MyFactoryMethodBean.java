package lifecycle.factorymethod;

import pojo.Son;

/**
 * @author leofee
 */
public class MyFactoryMethodBean {

    public Son getSon() {
        return new Son();
    }
}
