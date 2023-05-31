package anno.lookup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pojo.School;
import pojo.Teacher;

/**
 * @author leofee
 */
@Import({School.class, Teacher.class})
@Configuration
public class LookUpConfig {
}
