package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.main")
@EnableJpaRepositories(basePackages = "com.main")
public class ApplicationLauncher {

    private ApplicationLauncher() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

}
