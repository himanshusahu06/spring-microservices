package org.hsahu.microservice.limitsservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.hsahu.microservice.limitsservice")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
