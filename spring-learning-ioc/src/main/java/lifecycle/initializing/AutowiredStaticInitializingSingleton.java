package lifecycle.initializing;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import utils.ServiceUtils;

/**
 * @author leofee
 */
public class AutowiredStaticInitializingSingleton implements SmartInitializingSingleton, BeanFactoryAware {

    private AutowireCapableBeanFactory beanFactory;

    /**
     * 通过{@link AutowireCapableBeanFactory} 手工注入
     */
    @Override
    public void afterSingletonsInstantiated() {
        if (beanFactory != null) {
            beanFactory.autowireBean(new ServiceUtils());
        }
    }

    /**
     * 获取 {@link BeanFactory} 的实例
     *
     * @param beanFactory Spring BeanFactory
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof AutowireCapableBeanFactory) {
            this.beanFactory = (AutowireCapableBeanFactory) beanFactory;
        }
    }
}
