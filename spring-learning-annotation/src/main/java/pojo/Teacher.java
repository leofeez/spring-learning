package pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author leofee
 */
@Scope(SCOPE_PROTOTYPE)
@Component
public class Teacher {
}
