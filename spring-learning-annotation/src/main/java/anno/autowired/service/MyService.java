package anno.autowired.service;

import anno.autowired.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    @Qualifier("getMyDao")
    @Autowired
    private MyDao myDao;

    public void print() {
        System.out.println(myDao);
    }
}
