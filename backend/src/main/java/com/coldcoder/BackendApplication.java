package com.coldcoder;

import com.coldcoder.services.ProjectResourceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	
	@Bean
	CommandLineRunner init(ProjectResourceService projectResourceService) {
		return (args) -> {
			projectResourceService.deleteAll();
			projectResourceService.init();
		};
	}
}
