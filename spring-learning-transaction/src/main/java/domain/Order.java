package domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author leofee
 */
@Data
public class Order implements Serializable {

    private Integer orderId;

    private String orderCode;

    private LocalDateTime createTime;

    private Integer version;
}
