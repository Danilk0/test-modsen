package com.example.demomodsen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "db")
@Validated
public record DatabaseProperties(@NotEmpty
                                 String driverClassName,
                                 @NotEmpty
                                 String url,
                                 @NotEmpty
                                 String username,
                                 @NotEmpty
                                 String password) {
}
