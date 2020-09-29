package lifecycle.aware;

import lifecycle.aware.api.PaymentApi;
import lifecycle.constants.PayType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author leofee
 */
public class PaymentTest {

    @Before
    public void createApplicationContext() {
        // 容器启动
        new AnnotationConfigApplicationContext(ApplicationContextAwareConfig.class);
    }

    @Test
    public void testPayment() {

        // 根据类型获取到对应实现类
        PaymentApi paymentApi = PaymentAware.getPayment(PayType.ALI_PAY);

        // 执行对应实现类的方法
        paymentApi.pay();
    }
}
