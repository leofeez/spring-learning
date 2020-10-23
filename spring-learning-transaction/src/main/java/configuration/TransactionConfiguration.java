package configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@ComponentScan(basePackages = {"configuration", "dao", "service"})
@EnableTransactionManagement
@Configuration
public class TransactionConfiguration {

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClass(dataSourceProperties.getDriverClass());
        dataSource.setJdbcUrl(dataSourceProperties.getUrl());
        dataSource.setInitialPoolSize(5);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSourceProperties dataSourceProperties) throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource(dataSourceProperties));
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
