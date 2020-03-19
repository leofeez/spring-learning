package anno.bean.config;

import anno.bean.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    /**
     * 如果不定义返回的Bean的名称，则默认为方法名
     *
     * @return person
     */
//    @Bean(name = "jack")
    @Bean
    public Person getPerson() {
        Person person = new Person();
        person.setAge("28");
        person.setName("Jack");
        return person;
    }
}
