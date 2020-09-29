package lifecycle.aware;

import lifecycle.aware.api.PaymentApi;
import lifecycle.constants.PayType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器启动时，将所有实现了 {@link PaymentApi} 的实现类装载到 {@link #PAYMENT_MAP} 容器中,
 * 我们只需要根据类型{@link PayType}就能拿到具体的实现类，从而实现一个接口多个实现动态调用
 *
 * @author leofee
 */
public class PaymentAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 所有支付方式的实现类
     */
    private static final Map<PayType, PaymentApi> PAYMENT_MAP = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        // 准备 PaymentApi 的所有实现类
        preparePaymentTypes();
    }

    private void preparePaymentTypes() {
        // 获取容器中所有支付方式的实现类
        Map<String, PaymentApi> paymentApiList = this.applicationContext.getBeansOfType(PaymentApi.class);

        paymentApiList.forEach((key, value) -> {
            PAYMENT_MAP.put(value.getPayType(), value);
        });
    }

     public static PaymentApi getPayment(PayType type) {
        return PAYMENT_MAP.get(type);
     }
}
