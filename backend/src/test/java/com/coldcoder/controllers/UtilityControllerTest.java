package com.coldcoder.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UtilityControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private Long port;

	@Test
	public void checkServerStatus() {
		String response = restTemplate.getForObject("http://localhost:" + port + "/api", String.class);
		assertThat(response).contains("Server is running on");
		assertThat(response).contains(port.toString());
	}

}
