package lifecycle.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author leofee
 */
@AllArgsConstructor
@Getter
public enum TransportationType {

    /**
     * 汽车
     */
    CAR("1"),

    /**
     * 自行车
     */
    BIKE("2"),

    /**
     * 火车
     */
    TRAIN("3"),;

    private String value;
}
