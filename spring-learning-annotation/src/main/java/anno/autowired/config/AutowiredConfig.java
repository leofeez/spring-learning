package anno.autowired.config;

import anno.autowired.dao.StudentDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"anno.autowired.*"})
@Configuration
public class AutowiredConfig {

    @Bean
    public StudentDao getMyDao() {
        System.out.println("---------start----------");
        System.out.println("通过 @Bean 方式生成" + StudentDao.class.getName() + "实例");
        StudentDao studentDao = new StudentDao();
        studentDao.setFlag(2);
        System.out.println("--------- end ----------");
        return studentDao;
    }

    @Component
    private static class NestedAutowiredConfig {

        @Bean
        public StudentDao getDao2() {
            System.out.println("---------start----------");
            System.out.println("通过 Nested @Bean 方式生成" + StudentDao.class.getName() + "实例");
            StudentDao studentDao = new StudentDao();
            studentDao.setFlag(2);
            System.out.println("--------- end ----------");
            return studentDao;
        }
    }
}
