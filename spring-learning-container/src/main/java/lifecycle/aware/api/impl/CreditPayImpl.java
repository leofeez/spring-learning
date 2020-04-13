package lifecycle.aware.api.impl;

import lifecycle.aware.api.PaymentApi;
import lifecycle.constants.PayType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class CreditPayImpl implements PaymentApi {

    @Override
    public void pay() {
        System.out.println("信用卡支付......");
    }

    @Override
    public PayType getPayType() {
        return PayType.CREDIT_CARD;
    }
}
