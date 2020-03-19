package componentscan;

import anno.componentscan.config.CustomComponentConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomComponentConfigTest {

    @Test
    public void testCustomComponent() {
        ApplicationContext app = new AnnotationConfigApplicationContext(CustomComponentConfig.class);

        String[] beanDefinitionNames = app.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("自定义TypeFilter扫描到的类： " + beanDefinitionName);
        }
    }

}