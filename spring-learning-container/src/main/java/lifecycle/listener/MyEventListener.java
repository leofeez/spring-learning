package lifecycle.listener;

import com.alibaba.fastjson.JSON;
import lifecycle.listener.event.MyApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器
 *
 * @author leofee
 */
@Component
public class MyEventListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println(String.format("监听到事件 [%s]", JSON.toJSONString(event)));
    }
}
