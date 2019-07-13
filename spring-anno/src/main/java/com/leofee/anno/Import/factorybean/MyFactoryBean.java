package com.leofee.anno.Import.factorybean;

import com.leofee.anno.Import.pojo.Monkey;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Monkey> {

    public Monkey getObject() throws Exception {
        System.out.println("开始实例化.....Monkey");
        return new Monkey();
    }

    public Class<?> getObjectType() {
        return Monkey.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
