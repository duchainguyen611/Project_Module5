package com.ra;

import com.ra.uploadFile.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Module5Application {

    public static void main(String[] args) {
        SpringApplication.run(Module5Application.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}
