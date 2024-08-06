package com.lididimi.restaurant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Period;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "bills.retention")
public class RetentionProperties {

    private Period period;
}
