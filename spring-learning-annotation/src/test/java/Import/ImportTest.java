package Import;

import anno.Import.ImportConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ImportTest {

    @Test
    public void importTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

}