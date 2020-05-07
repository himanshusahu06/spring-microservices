package com.hsahu.application.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * Main application class to start the spring project
 */
@Slf4j
@SpringBootApplication
@ComponentScan("com.hsahu.application")
public class Application {
    public static void main(String[] args) {
        final ApplicationContext applicationContext =  SpringApplication.run(Application.class, args);
        log.info("Spring boot application is running on localhost.");
        log.info("List of beans loaded in spring's application context.");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(log::info);
    }
}
