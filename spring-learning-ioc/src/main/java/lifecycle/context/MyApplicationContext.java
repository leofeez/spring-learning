package lifecycle.context;

import lifecycle.aware.ApplicationContextAwareConfig;
import lifecycle.listener.MyEventListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * @author leofee
 */
public class MyApplicationContext extends AnnotationConfigApplicationContext {

    public MyApplicationContext(Class<?>... annotatedClasses) {
        super(annotatedClasses);
    }


    /**
     * 添加自定义的PropertySource
     */
    @Override
    protected void initPropertySources() {
        MutablePropertySources propertySources = getEnvironment().getPropertySources();
        Properties properties = new Properties();
        properties.setProperty("hello", "leofee's property!");
        propertySources.addLast(new PropertiesPropertySource("my_properties", properties));
    }

    public static void main(String[] args) {
        MyApplicationContext context = new MyApplicationContext(ApplicationContextAwareConfig.class);

        // 通过容器中的Environment就可以获取对应的value值
        String value = context.getEnvironment().getProperty("hello");
        System.out.println("initPropertySources 添加自定义属性 hello:" + value);
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        MyEventListener myEventListener = new MyEventListener();
        super.addApplicationListener(listener);
    }
}
