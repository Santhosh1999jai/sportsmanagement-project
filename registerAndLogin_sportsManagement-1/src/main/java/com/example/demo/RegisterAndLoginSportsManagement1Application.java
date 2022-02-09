package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@PropertySource("classpath:message.properties")
@EnableSwagger2
public class RegisterAndLoginSportsManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(RegisterAndLoginSportsManagement1Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
