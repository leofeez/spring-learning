package anno.scope;

import anno.scope.config.UserConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    @Test
    public void testSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserConfig.class);

        Object user1 = applicationContext.getBean("user");

        Object user2 = applicationContext.getBean("user");

        // Spring 默认实例化bean是单例的，所以这里输出true
        System.out.println(user1 == user2);


        Object user3 = applicationContext.getBean("user2");

        Object user4 = applicationContext.getBean("user2");
        System.out.println(user3 == user4);
    }
}
