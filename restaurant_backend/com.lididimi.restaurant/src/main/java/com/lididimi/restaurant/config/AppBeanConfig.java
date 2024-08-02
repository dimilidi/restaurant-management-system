package com.lididimi.restaurant.config;

import com.lididimi.restaurant.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class AppBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
                                                       RoleRepository roleRepository,
                                                       ResourceLoader resourceLoader) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        System.out.println("Initializing DataSource");
        System.out.println(roleRepository.count() );

        if (roleRepository.count() == 0) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(resourceLoader.getResource("classpath:data.sql"));
            initializer.setDatabasePopulator(populator);
        }

        return initializer;
    }


}
