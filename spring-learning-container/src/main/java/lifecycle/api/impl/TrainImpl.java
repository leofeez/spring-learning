package lifecycle.api.impl;

import lifecycle.api.Transportation;
import lifecycle.constants.TransportationType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class TrainImpl implements Transportation {

    @Override
    public void run() {
        System.out.println("火车在运行......");
    }

    @Override
    public TransportationType getTransportationType() {
        return TransportationType.TRAIN;
    }
}
