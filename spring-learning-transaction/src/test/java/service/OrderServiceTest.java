package service;

import configuration.TransactionConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = TransactionConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        orderService.createOrder();
    }

    @Test
    public void updateOrder() {
        orderService.updateOrder(1);
    }

    @Test
    public void updateOrderWithLock() {
        orderService.updateOrderWithLock(1);
    }
}