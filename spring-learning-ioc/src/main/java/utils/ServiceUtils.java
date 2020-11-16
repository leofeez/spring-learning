package utils;

import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;

/**
 * @author leofee
 */
public class ServiceUtils {

    private static OrderService SERVICE_INSTANCE;

    @Autowired
    private void setServiceInstance(OrderService orderService) {
        SERVICE_INSTANCE = orderService;
    }

    public static void handleService() {
        SERVICE_INSTANCE.invokeByUtils();
    }
}
