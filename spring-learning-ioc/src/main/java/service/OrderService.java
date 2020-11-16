package service;

import org.springframework.stereotype.Service;

/**
 * @author leofee
 */
@Service
public class OrderService {

    public void invokeByUtils() {
        System.out.println("service utils 成功通过静态方法调用......");
    }
}
