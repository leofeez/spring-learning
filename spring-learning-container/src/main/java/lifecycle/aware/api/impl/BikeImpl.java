package lifecycle.aware.api.impl;

import lifecycle.aware.api.TransportationApi;
import lifecycle.constants.TransportationType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class BikeImpl implements TransportationApi {

    @Override
    public void run() {
        System.out.println("自行车在运行......");
    }

    @Override
    public TransportationType getTransportationType() {
        return TransportationType.BIKE;
    }
}
