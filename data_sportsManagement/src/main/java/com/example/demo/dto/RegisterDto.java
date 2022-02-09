package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RegisterDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String jwt;
	private String role;
	private String tokens;
	private LocalDateTime tokenCreationDate;

	
	
	
	//private String empname;
	//private String username;
	//private String empmail;



}
