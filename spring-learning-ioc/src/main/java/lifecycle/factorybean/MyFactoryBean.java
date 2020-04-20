package lifecycle.factorybean;

import pojo.Train;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;

/**
 * FactoryBean，就是单个对象的工厂类，和普通的Bean不一样，该工厂类所持有的对象引用应该是
 * {@link #getObject()}实际创建并返回的Bean而不是它本身。尽管Spring容器在启动时会以普通Bean
 * 创建的方式一样去创建FactoryBean。
 *
 * FactoryBean支持单例和多例，根据{@link #isSingleton()}返回值来确定是否是单例，默认为true。
 *
 * FactoryBean生成的Bean，支持懒加载或者在容器启动阶段就同步创建Bean，如果需要同步创建Bean则可以
 * 使用{@link SmartFactoryBean}，该接口继承{@link FactoryBean}并提供是否支持同步创建Bean的实例
 * {@link SmartFactoryBean#isEagerInit()}
 *
 * @author leofee
 */
public class MyFactoryBean implements FactoryBean<Train> {

    /**
     * 返回我们需要创建的Bean实例
     */
    @Override
    public Train getObject() throws Exception {
        Train train = new Train();
        return train;
    }

    /**
     * 返回创建Bean实例的类型
     */
    @Override
    public Class<?> getObjectType() {
        return Train.class;
    }

    /**
     * 是否是单例
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
