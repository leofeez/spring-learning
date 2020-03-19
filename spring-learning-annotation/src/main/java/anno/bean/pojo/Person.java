package anno.bean.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    private String name;

    private String age;
}
