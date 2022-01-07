package lifecycle.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 * @author leofee
 */
public class MyApplicationEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(String source) {
        super(source);
        System.out.println(source);
    }
}
