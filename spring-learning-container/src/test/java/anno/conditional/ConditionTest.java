package anno.conditional;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionTest {

    @Test
    public void testCondition() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
