package com.lididimi.restaurant.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final Environment env;

    public CloudinaryConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", env.getProperty("cloudinary.cloud-name"));
        config.put("api_key", env.getProperty("cloudinary.api-key"));
        config.put("api_secret", env.getProperty("cloudinary.api-secret"));
        return new Cloudinary(config);
    }
}
