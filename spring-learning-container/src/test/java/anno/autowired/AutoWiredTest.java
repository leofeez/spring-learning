package anno.autowired;

import anno.autowired.config.AutowiredConfig;
import anno.autowired.pojo.Sun;
import anno.autowired.service.MyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoWiredTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig.class);

        MyService myService = applicationContext.getBean(MyService.class);

        myService.print();
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig.class);

        Sun sun = applicationContext.getBean(Sun.class);

        System.out.println(sun.getMoon());
    }
}