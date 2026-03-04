package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationLauncher {

    private ApplicationLauncher() {

    }

    static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

}
