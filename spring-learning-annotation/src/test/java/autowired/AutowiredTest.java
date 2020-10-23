package autowired;

import anno.autowired.config.AutowiredConfig;
import anno.autowired.dao.StudentDao;
import anno.autowired.service.PersonService;
import base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

/**
 * @author leofee
 */
@ContextConfiguration(classes = AutowiredConfig.class)
public class AutowiredTest extends BaseTest {

    @Test
    public void testAutowired() {
        Map<String, StudentDao> daoMap = applicationContext.getBeansOfType(StudentDao.class);

        System.out.println("---------start----------");
        System.out.println("容器中的MyDao实例如下:");
        daoMap.forEach((key, value) -> {
            System.out.println(value);
        });
        System.out.println("--------- end ----------");

        Map<String, PersonService> beans = applicationContext.getBeansOfType(PersonService.class);
        PersonService personService = beans.entrySet().iterator().next().getValue();
        Assert.assertNotNull(personService.getStudentDao());
        Assert.assertNotNull(personService.getTeacherDao());
        Assert.assertNotNull(personService.getWorkerDao());
    }
}
