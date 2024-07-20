package com.lididimi.restaurant.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Adjust the path and locations according to your setup
        registry.addResourceHandler("/messages/**")
                .addResourceLocations("classpath:/messages/");
    }
}