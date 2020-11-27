package pojo;

import java.io.Serializable;

/**
 * @author leofee
 */
public class Parent implements Serializable {

    public Parent(Son son) {
        System.out.println("Parent init....");
    }
}
