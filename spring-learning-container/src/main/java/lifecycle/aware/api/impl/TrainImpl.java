package lifecycle.aware.api.impl;

import lifecycle.aware.api.TransportationApi;
import lifecycle.constants.TransportationType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class TrainImpl implements TransportationApi {

    @Override
    public void run() {
        System.out.println("火车在运行......");
    }

    @Override
    public TransportationType getTransportationType() {
        return TransportationType.TRAIN;
    }
}
