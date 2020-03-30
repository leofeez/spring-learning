package lifecycle.aware;

import lifecycle.api.Transportation;
import lifecycle.constants.TransportationType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器启动时，将所有实现了 {@link Transportation} 的实现类装载到 {@link #TRANSPORTATION} 容器中,
 * 我们只需要根据类型{@link TransportationType}就能拿到具体的实现类，从而实现一个接口多个实现
 *
 * @author leofee
 */
@Component
public class TransportationAware implements ApplicationContextAware {

    /**
     * 所有交通工具的实现类
     */
    private static final Map<TransportationType, Transportation> TRANSPORTATION = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        // 获取容器中所有交通工具的实现类
        Map<String, Transportation> transportationMap = applicationContext.getBeansOfType(Transportation.class);

        transportationMap.forEach((key, value) -> {
            TRANSPORTATION.put(value.getTransportationType(), value);
        });
    }

     public static Transportation getTransportation(TransportationType type) {
        return TRANSPORTATION.get(type);
     }
}
