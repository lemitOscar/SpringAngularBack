package com.osca.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/angularDb");
        dataSource.setUsername("root");
        dataSource.setPassword("5656");
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate template() {
        return new NamedParameterJdbcTemplate(mysqlDataSource());
    }

    @Bean
    public MapSqlParameterSource param() {
        return new MapSqlParameterSource();
    }
}