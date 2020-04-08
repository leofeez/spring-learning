package lifecycle.factorybean;

import lifecycle.pojo.Train;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Train> {
    @Override
    public Train getObject() throws Exception {
        Train train = new Train();
        return train;
    }

    @Override
    public Class<?> getObjectType() {
        return Train.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
