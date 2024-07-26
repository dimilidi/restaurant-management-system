package com.lididimi.restaurant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "categories.api")
@Data
public class CategoryApiConfig {
    private String baseUrl;
}