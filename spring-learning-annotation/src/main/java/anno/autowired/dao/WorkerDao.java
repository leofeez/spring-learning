package anno.autowired.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerDao {


    @Autowired
    public WorkerDao(StudentDao studentDao) {
        System.out.println(studentDao);
    }
}
