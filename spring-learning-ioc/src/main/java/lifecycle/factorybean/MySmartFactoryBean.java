package lifecycle.factorybean;

import pojo.Ship;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;

/**
 * SmartFactoryBean 继承自 {@link FactoryBean}
 * FactoryBean 创建的实例Bean默认是在第一次获取时创建，如果需要在容器启动时同步创建Bean实例
 * 可以重写{@link #isEagerInit()}，true表示容器创建阶段就会调用{@link #getObject()}去
 * 实例化对应的Bean实例
 *
 * @author leofee
 */
public class MySmartFactoryBean implements SmartFactoryBean<Ship> {

    @Override
    public Ship getObject() throws Exception {
        return new Ship();
    }

    @Override
    public Class<?> getObjectType() {
        return Ship.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * 是否在容器启动阶段调用{@link #getObject()}去实例化真实Bean
     *
     * 一个标准的FactoryBean在容器启动时是不会同步创建对应的真实Bean实例，
     * 即使是单例，也只有在调用{@link #getObject()}时才会主动去创建对应的Bean
     * 实例，所以{@link SmartFactoryBean}在{@link FactoryBean}的基础上进行了
     * 扩展，并提供该方法，true表示需要在容器启动阶段去实例化真实的Bean实例
     */
    @Override
    public boolean isEagerInit() {
        return true;
    }
}
