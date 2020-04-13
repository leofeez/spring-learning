package lifecycle.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author leofee
 */
@AllArgsConstructor
@Getter
public enum PayType {

    /**
     * 支付宝
     */
    ALI_PAY("1"),

    /**
     * 微信
     */
    WE_CHAT("2"),

    /**
     * 信用卡
     */
    CREDIT_CARD("3"),;

    private String value;
}
