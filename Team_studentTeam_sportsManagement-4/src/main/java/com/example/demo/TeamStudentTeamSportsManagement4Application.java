package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:message.properties")

public class TeamStudentTeamSportsManagement4Application {

	public static void main(String[] args) {
		SpringApplication.run(TeamStudentTeamSportsManagement4Application.class, args);
	}

}
