package org.hsahu.microservice.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.hsahu.microservice.currencyconversion.feignproxy")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
