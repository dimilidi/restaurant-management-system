package com.lididimi.restaurant.config;

import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {
        "smtp_username=dummyUsername",
        "smtp_password=dummyPassword",
})
class CloudinaryConfigTest {

    @Autowired
    private Environment env;

    @Autowired
    private CloudinaryConfig cloudinaryConfig;

    @BeforeEach
    void setup() {
        System.setProperty("cloudinary.cloud-name", "your_cloud_name");
        System.setProperty("cloudinary.api-key", "your_api_key");
        System.setProperty("cloudinary.api-secret", "your_api_secret");
    }

    @Test
    void testCloudinaryBean() {
        String cloudName = env.getProperty("cloudinary.cloud-name");
        String apiKey = env.getProperty("cloudinary.api-key");
        String apiSecret = env.getProperty("cloudinary.api-secret");

        assertNotNull(cloudName, "Cloudinary cloud-name should not be null");
        assertNotNull(apiKey, "Cloudinary api-key should not be null");
        assertNotNull(apiSecret, "Cloudinary api-secret should not be null");

        Cloudinary cloudinary = cloudinaryConfig.cloudinary();
        assertNotNull(cloudinary, "Cloudinary bean should not be null");
    }
}
