package anno.lazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pojo.User;

@Configuration
public class LazyConfig {

    @Lazy
    @Bean("lazyUser")
    public User getUser() {
        System.out.println("开始创建 bean 。。。。。。");
        User user = new User();
        user.setUsername("hello world!");
        return user;
    }
}
