package lifecycle.listener;

import lifecycle.listener.event.MyApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义监听器
 *
 * @author leofee
 */
public class MyEventListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.printf("监听到事件 %s", event.getClass());
        System.out.println();
        System.out.printf("事件source为 %s", event.getSource());
    }
}
