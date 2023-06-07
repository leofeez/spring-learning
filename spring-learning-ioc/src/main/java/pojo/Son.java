package pojo;

import java.io.Serializable;

/**
 * @author leofee
 */
public class Son implements Serializable {

    private String name;

    public Son() {
        System.out.println("Son create....");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                '}';
    }
}
