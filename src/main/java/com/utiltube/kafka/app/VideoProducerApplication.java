package com.utiltube.kafka.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.utiltube")
public class VideoProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoProducerApplication.class, args);
    }
}
