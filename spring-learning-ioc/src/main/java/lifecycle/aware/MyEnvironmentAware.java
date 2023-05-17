package lifecycle.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 通过 Environment 可以获取到 Property 中的value值，如：
 * application.properties 中对应 key-value
 *
 * @author leofee
 */
@Component
public class MyEnvironmentAware implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        String value = environment.getProperty("user.name");
        System.out.println("user.name is " + value);

        // 可以通过 environment 来解析占位符
        String username = environment.resolvePlaceholders("${user.name}");
        System.out.println("resolved username is:" + username);

        String property = environment.getProperty("hello");
        System.out.println("custom property is:" + property);
    }
}
