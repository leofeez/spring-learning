package com.leofee.anno.autowired.config;

import com.leofee.anno.autowired.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.leofee.anno.autowired.*"})
@Configuration
public class AutowiredConfig {

    @Bean
    public MyDao getMyDao() {
        MyDao myDao = new MyDao();
        myDao.setFlag(2);
        return myDao;
    }

}
