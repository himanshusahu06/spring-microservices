package com.hsahu.application.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class Application implements ApplicationRunner {

    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        final ApplicationContext applicationContext =  SpringApplication.run(Application.class, args);
        log.debug("List of beans loaded in spring's application context.");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(log::debug);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Spring boot application is running on localhost at port {}", port);
    }
}
