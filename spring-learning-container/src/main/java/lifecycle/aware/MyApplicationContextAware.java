package lifecycle.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * @author leofee
 */
public class MyApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("set my applicationContext !");
        this.applicationContext = applicationContext;

        // 利用Spring 容器 自定义其他操作
        getMyOsName();
    }

    /**
     * 只要持有了 {@link ApplicationContext} 我们就可以做一些其他的操作
     *
     * @return 操作系统名称
     */
    public String getMyOsName() {
        Environment environment = this.applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println("当前操作系统为: " + osName);
        return osName;
    }
}
