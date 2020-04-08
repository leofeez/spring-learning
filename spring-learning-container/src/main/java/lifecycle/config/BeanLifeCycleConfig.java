package lifecycle.config;

import lifecycle.aware.MyApplicationContextAware;
import lifecycle.aware.TransportationAware;
import lifecycle.factorybean.MyFactoryBean;
import lifecycle.pojo.Bike;
import lifecycle.pojo.Car;
import lifecycle.processor.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

/**
 * @author leofee
 */
@Configuration
@ComponentScan(basePackageClasses = {Bike.class, MyBeanPostProcessor.class, TransportationAware.class, MyApplicationContextAware.class},
        basePackages = {"lifecycle.api.*", "lifecycle.listener.*"})
@Import(value = {MyFactoryBean.class})
public class BeanLifeCycleConfig {

    @Bean(name = "benz", initMethod = "init", destroyMethod = "destroy")
    public Car getBenz() {
        Car car = new Car();
        car.setCarBrand("Benz");
        car.setCarPrice(new BigDecimal(1000000));
        return car;
    }
}
