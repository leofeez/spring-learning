package anno.autowired.dao;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class MyDao {

    private Integer flag = 1;
}
