package lifecycle.api.impl;

import lifecycle.api.Transportation;
import lifecycle.constants.TransportationType;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class CarImpl implements Transportation {

    @Override
    public void run() {
        System.out.println("汽车在运行......");
    }

    @Override
    public TransportationType getTransportationType() {
        return TransportationType.CAR;
    }
}
