package anno.autowired.dao;

import org.springframework.stereotype.Repository;

/**
 * @author leofee
 */
@Repository
public class TeacherDao {

    public TeacherDao() {
        System.out.println(TeacherDao.class.getName() + " 开始实例化......");
    }
}
