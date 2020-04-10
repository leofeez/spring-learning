package lifecycle.aware;

import lifecycle.aware.api.TransportationApi;
import lifecycle.constants.TransportationType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器启动时，将所有实现了 {@link TransportationApi} 的实现类装载到 {@link #TRANSPORTATION} 容器中,
 * 我们只需要根据类型{@link TransportationType}就能拿到具体的实现类，从而实现一个接口多个实现
 *
 * @author leofee
 */
public class TransportationAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 所有交通工具的实现类
     */
    private static final Map<TransportationType, TransportationApi> TRANSPORTATION = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        // 准备 TransportationApi 的所有实现类
        prepareTransportation();
    }

    private void prepareTransportation() {
        // 获取容器中所有交通工具的实现类
        Map<String, TransportationApi> transportationMap = this.applicationContext.getBeansOfType(TransportationApi.class);

        transportationMap.forEach((key, value) -> {
            TRANSPORTATION.put(value.getTransportationType(), value);
        });
    }

     public static TransportationApi getTransportation(TransportationType type) {
        return TRANSPORTATION.get(type);
     }
}
