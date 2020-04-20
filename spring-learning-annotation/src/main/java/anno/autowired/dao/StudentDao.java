package anno.autowired.dao;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class StudentDao {

    private Integer flag = 1;

    public StudentDao() {
        System.out.println(StudentDao.class.getName() + " 开始实例化......");
    }
}
