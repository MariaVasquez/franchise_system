package com.mariavasquez.franchis.system.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthCheckController {
    @GetMapping("/test")
    public Mono<String> test() {
        return Mono.just("✅ Service is up and running");
    }
}
