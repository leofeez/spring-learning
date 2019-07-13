package com.leofee.anno.componentscan.config;

import com.leofee.anno.componentscan.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public Person getPerson() {
        Person person = new Person();
        person.setAge(30);
        person.setName("hello world");
        return person;
    }
}
