package anno.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import pojo.Person;

@Configuration
public class BeanAnnoConfig {

    /**
     * 如果不定义返回的Bean的名称，则默认为方法名
     *
     * @return person
     */
//    @Bean(name = "jack")
    @Bean
    public Person getPerson() {
        Person person = new Person();
        person.setAge(28);
        person.setName("Jack");
        return person;
    }

    @Bean(name = "jack")
    public Person person() {
        Person person = new Person();
        person.setAge(28);
        person.setName("Jack");

        System.out.println("@Bean 注解执行 " + person.getName());
        return person;
    }


    @Bean
    public Person hello() {
        return person();
    }
}
