package lifecycle.aware.api;

import lifecycle.constants.PayType;

/**
 * @author leofee
 */
public interface PaymentApi {

    /**
     * 支付
     */
    void pay();

    /**
     * 支付类型
     *
     * @return 支付类型
     */
    PayType getPayType();
}
