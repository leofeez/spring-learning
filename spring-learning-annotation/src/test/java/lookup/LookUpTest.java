package lookup;

import anno.lookup.LookUpConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.School;
import pojo.Sun;

import java.util.Arrays;

/**
 * @author leofee
 */
public class LookUpTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(LookUpConfig.class);
        School school = applicationContext.getBean(School.class);
        System.out.println(school.getTeacher());
        School school2 = applicationContext.getBean(School.class);
        System.out.println(school2.getTeacher());
        // 结果为false，两次获取的teacher都是不一样的
        System.out.println(school2.getTeacher() == school.getTeacher());
    }
}
