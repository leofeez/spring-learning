package lifecycle.aware.api.impl;

import lifecycle.aware.api.TransportationApi;
import lifecycle.constants.TransportationType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class CarImpl implements TransportationApi {

    @Override
    public void run() {
        System.out.println("汽车在运行......");
    }

    @Override
    public TransportationType getTransportationType() {
        return TransportationType.CAR;
    }
}
