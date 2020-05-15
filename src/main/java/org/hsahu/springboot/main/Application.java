package org.hsahu.springboot.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements ApplicationRunner {

    @Value("${server.port:8888}")
    private int serverPort;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application running on localhost:{}", serverPort);
    }
}
