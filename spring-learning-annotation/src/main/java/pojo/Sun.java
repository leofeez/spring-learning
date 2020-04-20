package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Sun implements Serializable {


    private Moon moon;

    @Autowired
    public Sun(Moon moon) {
        System.out.println("Sun......开始实例化, moon = " + moon);
    }

    /**
     * {@link Autowired} 可以用在方法上，如果容器中存在对应的bean
     * 则会自动注入到方法的参数里
     */
    @Autowired
    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    public Moon getMoon() {
        return this.moon;
    }

    public Sun() {
        System.out.println("Sun......开始实例化");
    }

    public void init() {
        System.out.println("Sun......开始初始化");
    }

    public void destroy() {
        System.out.println("Sun......开始销毁");
    }
}
