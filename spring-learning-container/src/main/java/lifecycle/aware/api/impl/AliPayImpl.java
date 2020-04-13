package lifecycle.aware.api.impl;

import lifecycle.aware.api.PaymentApi;
import lifecycle.constants.PayType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class AliPayImpl implements PaymentApi {

    @Override
    public void pay() {
        System.out.println("支付宝支付......");
    }

    @Override
    public PayType getPayType() {
        return PayType.ALI_PAY;
    }
}
