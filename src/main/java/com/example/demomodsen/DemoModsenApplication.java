package com.example.demomodsen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ConfigurationPropertiesScan
public class DemoModsenApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoModsenApplication.class, args);
    }

}
