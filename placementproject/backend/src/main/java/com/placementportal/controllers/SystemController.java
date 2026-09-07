package com.placementportal.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SystemController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "application", "Placement Portal API",
                "status", "running",
                "health", "/actuator/health"
        );
    }
}
