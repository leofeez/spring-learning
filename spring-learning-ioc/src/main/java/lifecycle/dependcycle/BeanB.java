package lifecycle.dependcycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 */
@Component
public class BeanB {

    @Autowired
    private BeanA beanA;
}
