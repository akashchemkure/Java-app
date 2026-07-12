package com.devops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {

        return "Welcome to Production DevOps CI/CD Pipeline";

    }

    @GetMapping("/health")
    public String health() {

        return "Application is Healthy";

    }

}
