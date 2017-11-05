package com.feedr;

import com.feedr.util.EnvProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        if (env.getProperty(EnvProperties.ALLOW_CORS, Boolean.class)) {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("OPTIONS");
            config.addAllowedMethod("GET");
            config.addAllowedMethod("POST");
            config.addAllowedMethod("PUT");
            config.addAllowedMethod("DELETE");
            source.registerCorsConfiguration("/**", config);
            return new CorsFilter(source);
        } else {
            return new CorsFilter(source);
        }
    }
}
