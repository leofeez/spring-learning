package lifecycle.initializing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.BeanFactory;

/**
 * 实现了{@link InitializingBean}，在{@link BeanFactory}
 *
 * @author leofee
 */
public class MyInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyInitializingBean 初始化......afterPropertiesSet");
    }

    public void init() {
        System.out.println("MyInitializingBean 初始化......init");
    }

    public void destroy() {
        System.out.println("MyInitializingBean 销毁......destroy");
    }
}
