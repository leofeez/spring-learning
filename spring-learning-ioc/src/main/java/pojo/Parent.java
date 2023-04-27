package pojo;

import java.io.Serializable;

/**
 * @author leofee
 */
public class Parent implements Serializable {

    private String id;

    private String name;


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

    @Override
    public String toString() {
        return "Parent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
