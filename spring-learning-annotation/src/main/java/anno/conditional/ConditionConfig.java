package anno.conditional;

import anno.componentscan.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    /**
     * 当前系统为windows才会实例化
     */
    @Conditional(value = {WinCondition.class})
    @Bean("jack")
    public Person getPerson() {
        System.out.println("开始创建person......");
        Person person = new Person();
        person.setName("jack");
        return person;
    }

    /**
     * 当前系统为linux才会实例化
     */
    @Conditional(value = {LinuxCondition.class})
    @Bean("leofee")
    public Person leofee() {
        System.out.println("开始创建leofee......");
        Person person = new Person();
        person.setName("leofee");
        return person;
    }
}
