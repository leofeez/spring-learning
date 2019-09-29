package com.leofee.transaction.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceProperties {

    @Value("${user}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${url}")
    private String url;

    @Value("${driverClass}")
    private String driverClass;
}
