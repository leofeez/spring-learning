package Import;

import anno.Import.ImportConfig;
import base.BaseTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@ContextConfiguration(classes = ImportConfig.class)
public class ImportTest extends BaseTest {

    @Test
    public void importTest() {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

}