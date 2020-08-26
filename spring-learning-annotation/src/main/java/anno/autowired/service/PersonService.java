package anno.autowired.service;

import anno.autowired.dao.StudentDao;
import anno.autowired.dao.TeacherDao;
import anno.autowired.dao.WorkerDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Data
@Component
public class PersonService {

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    /**
     * 属性注入
     */
    @Autowired
    private WorkerDao workerDao;

    /**
     * 构造器注入
     */
    @Autowired
    public PersonService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * setter 方法注入
     */
    @Autowired
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
}
