package com.colcoder.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class UtilityController {

    @GetMapping
    public ResponseEntity<String> checkServer() {
        return ResponseEntity.status(OK).body("Server up and running on port: 8080");
    }
}
