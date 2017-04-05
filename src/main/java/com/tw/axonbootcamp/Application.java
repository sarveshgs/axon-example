package com.tw.axonbootcamp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories("com.tw.axonbootcamp.query")
@EntityScan("com.tw.axonbootcamp.query.view")
@ComponentScan
@EnableTransactionManagement

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
