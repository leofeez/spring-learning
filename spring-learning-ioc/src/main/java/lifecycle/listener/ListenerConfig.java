package lifecycle.listener;

import lifecycle.listener.event.MyApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leofee
 */
@Configuration
public class ListenerConfig {

    @Bean
    public ApplicationListener<MyApplicationEvent> myEventListener() {
        return new MyEventListener();
    }
}
