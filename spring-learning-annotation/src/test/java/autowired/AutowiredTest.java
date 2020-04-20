package autowired;

import anno.autowired.config.AutowiredConfig;
import anno.autowired.dao.StudentDao;
import anno.autowired.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author leofee
 */
public class AutowiredTest {

    @Test
    public void testAutowired() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig.class);

        Map<String, StudentDao> daoMap = applicationContext.getBeansOfType(StudentDao.class);

        System.out.println("---------start----------");
        System.out.println("容器中的MyDao实例如下:");
        daoMap.forEach((key, value) -> {
            System.out.println(value);
        });
        System.out.println("--------- end ----------");

        Map<String, PersonService> beans = applicationContext.getBeansOfType(PersonService.class);
        PersonService personService = beans.entrySet().iterator().next().getValue();
        personService.getStudent();
        personService.getTeacher();
    }
}
