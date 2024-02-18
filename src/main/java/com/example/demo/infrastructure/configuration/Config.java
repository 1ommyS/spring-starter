package com.example.demo.infrastructure.configuration;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @PostConstruct
    public void init() {
        System.out.println(dbUrl);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
