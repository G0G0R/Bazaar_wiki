package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationLauncher {

    private ApplicationLauncher() {
    }

    static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

}
