package anno.value;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource("classpath:application.properties")
@Configuration
public class ValueConfig {

    @Value("${spring.learning.annotation}")
    private String name;


}
