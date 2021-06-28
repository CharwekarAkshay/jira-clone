package com.colcoder.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UtilityControllerTest {
    @Autowired
    private UtilityController utilityController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void checkContext() {
        assertThat(utilityController).isNotNull();
    }

    @Test
    public void checkServerAvailability() {
        assertThat(testRestTemplate).isNotNull();
    }
}
