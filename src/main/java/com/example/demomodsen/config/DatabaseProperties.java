package com.example.demomodsen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "db")
@Validated
public record DatabaseProperties(String driverClassName,
                                 String url,
                                 String username,
                                 String password) {
}
