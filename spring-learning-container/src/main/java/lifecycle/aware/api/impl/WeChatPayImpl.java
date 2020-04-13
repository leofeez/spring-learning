package lifecycle.aware.api.impl;

import lifecycle.aware.api.PaymentApi;
import lifecycle.constants.PayType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class WeChatPayImpl implements PaymentApi {

    @Override
    public void pay() {
        System.out.println("微信支付......");
    }

    @Override
    public PayType getPayType() {
        return PayType.WE_CHAT;
    }
}
