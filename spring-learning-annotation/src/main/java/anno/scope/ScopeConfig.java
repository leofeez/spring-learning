package anno.scope;

import pojo.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScopeConfig {

    @Bean("user")
    public User getUser() {
        User user = new User();
        user.setPassword("111111");
        user.setUsername("leofee");
        return user;
    }

    /**
     * 多实例
     */
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean("user2")
    public User getUser2() {
        User user = new User();
        user.setPassword("222222");
        user.setUsername("leofee2");
        return user;
    }
}
