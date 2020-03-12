package anno.lazy;

import anno.lazy.config.UserConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    /**
     * 这里不会输出 {@link UserConfig} 打印的日志
     */
    @Test
    public void getLazyUser() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserConfig.class);

        System.out.println("IOC 容器创建完成 ... ...");

    }

    /**
     * 这里会输出 创建bean 的日志
     */
    @Test
    public void getLazyUser2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserConfig.class);

        System.out.println("IOC 容器创建完成 ... ...");

        Object lazyUser = applicationContext.getBean("lazyUser");
    }
}
