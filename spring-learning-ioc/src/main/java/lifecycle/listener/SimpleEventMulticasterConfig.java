package lifecycle.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Spring 事件异步处理配置
 *
 * @author leofee
 */
@Configuration
public class SimpleEventMulticasterConfig {

    /**
     * 配置线程池，可以使Spring监听器异步执行，若不配置则采用同步的执行方式
     */
    @Bean
    public ThreadPoolTaskExecutor simpleThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(100);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("simple-thread");
        return executor;
    }


    @Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster(ThreadPoolTaskExecutor simpleThreadPoolExecutor) {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(simpleThreadPoolExecutor);
        return multicaster;
    }
}
