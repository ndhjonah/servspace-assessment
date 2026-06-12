package com.servspacce.auth;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
public class ApiKeyController {

    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyController(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @PostMapping("/api/keys/create")
    public ResponseEntity<Map<String, String>> createKey(
            @RequestBody Map<String, String> body) {

        String ownerName = body.get("ownerName");

        if (ownerName == null || ownerName.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "ownerName is required"));
        }

        String newKey = "sk-" + UUID.randomUUID().toString().replace("-", "");

        apiKeyRepository.save(new ApiKey(ownerName, newKey));

        return ResponseEntity.ok(Map.of(
                "message", "Key created successfully",
                "apiKey",  newKey
        ));
    }

    @GetMapping("/api/data")
    public ResponseEntity<Map<String, String>> getData() {
        return ResponseEntity.ok(Map.of(
                "message", "Access granted! You are authenticated.",
                "data",    "Welcome to ServSpace protected data."
        ));
    }
}