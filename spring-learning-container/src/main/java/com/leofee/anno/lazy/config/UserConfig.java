package com.leofee.anno.lazy.config;

import com.leofee.anno.lazy.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class UserConfig {

    @Lazy
    @Bean("lazyUser")
    public User getUser() {
        System.out.println("开始创建 bean 。。。。。。");
        User user = new User();
        user.setUsername("hello world!");
        return user;
    }
}
