package pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Moon implements Serializable {

    public Moon() {
        System.out.println("Moon......开始实例化");
    }

    public void init() {
        System.out.println("Moon......开始初始化");
    }

    public void destroy() {
        System.out.println("Moon......开始销毁");
    }
}
