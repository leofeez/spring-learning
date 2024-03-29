package pojo;

import java.io.Serializable;

/**
 * @author leofee
 */
public class Parent implements Serializable {

    private String id;

    private String name;

    private Son son;

    public Parent() {}

    public Parent(Son son) {
        System.out.println("Parent init....");
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", son=" + son +
                '}';
    }
}
