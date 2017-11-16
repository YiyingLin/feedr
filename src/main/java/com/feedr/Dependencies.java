package com.feedr;

import com.feedr.util.EnvProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@PropertySource("classpath:properties/application.properties")
public class Dependencies {

    private Environment env;

    @Autowired
    public Dependencies(Environment env) {
        this.env = env;
    }

    @Bean(name="dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(EnvProperties.DATASOURCE_DRIVER));
        dataSource.setUsername(env.getProperty(EnvProperties.DATASOURCE_USERNAME));
        dataSource.setPassword(env.getProperty(EnvProperties.DATASOURCE_PASSWORD));
        dataSource.setUrl(env.getProperty(EnvProperties.DATASOURCE_URL));
        return dataSource;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}
