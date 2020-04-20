package anno.autowired.service;

import anno.autowired.dao.StudentDao;
import anno.autowired.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class PersonService {

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    @Autowired
    public PersonService(StudentDao studentDao, @Nullable TeacherDao teacherDao) {
        System.out.println("两个参数的构造器");
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }

//    @Autowired(required = false)
//    public PersonService(TeacherDao teacherDao) {
//        System.out.println("一个参数的构造器");
//        this.teacherDao = teacherDao;
//    }

    public void getStudent() {
        System.out.println(PersonService.class.getName() + "中注入的" + StudentDao.class.getName() + "为:" + studentDao);
    }

    public void getTeacher() {
        System.out.println(PersonService.class.getName() + "中注入的" + TeacherDao.class.getName() + "为:" + teacherDao);
    }
}
