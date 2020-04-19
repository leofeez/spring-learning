package lifecycle.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author leofee
 */
@Data
public class Ship implements Serializable {

    private String name;

    public Ship() {
        System.out.println("ship 开始实例化.......");
    }
}
