package com.coldcoder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UtilityController {

	@Value("${server.port}")
    protected int port;
	
	@

	
	@GetMapping
	public ResponseEntity<String> checkServerAvailability() {
		return ResponseEntity.status(HttpStatus.OK).body("Server is running on Port:" + port);
	}
}
