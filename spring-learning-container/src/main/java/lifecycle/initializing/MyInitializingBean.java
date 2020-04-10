package lifecycle.initializing;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 通过实现了{@link InitializingBean}，在{@link BeanFactory} 创建该实例之后进行
 * 初始化。
 * 实现了该接口之后，在 {@link BeanFactory} 创建实例之后进行初始化操作或者仅仅检查
 * 是否所有必要的属性都已经设置完毕。
 *
 * @author leofee
 */
public class MyInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyInitializingBean 初始化......afterPropertiesSet");
    }
}
