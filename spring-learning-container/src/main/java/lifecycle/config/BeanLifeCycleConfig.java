package lifecycle.config;

import lifecycle.pojo.Bike;
import lifecycle.pojo.Car;
import lifecycle.processor.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ComponentScan(basePackageClasses = {Bike.class, MyBeanPostProcessor.class})
public class BeanLifeCycleConfig {

    @Bean(name = "benz", initMethod = "init", destroyMethod = "destroy")
    public Car getBenz() {
        Car car = new Car();
        car.setCarBrand("Benz");
        car.setCarPrice(new BigDecimal(1000000));
        return car;
    }
}
