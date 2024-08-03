package com.lididimi.restaurant.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {


    private final Environment env;
/*
   // @Value("${cloudinary.cloud-name}")
    private String cloudName;

    //@Value("${cloudinary.api-key}")
    private String apiKey;

    //@Value("${cloudinary.api-secret}")
    private String apiSecret;*/

    public CloudinaryConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
   /*     config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);*/

        config.put("cloud_name", env.getProperty("cloudinary.cloud-name"));
        config.put("api_key", env.getProperty("cloudinary.api-key"));
        config.put("api_secret", env.getProperty("cloudinary.api-secret"));
        return new Cloudinary(config);
    }
}
