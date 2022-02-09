package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.RegisterDto;

public interface RegisterService {
	
	ResponseEntity<?>register(RegisterDto registerdto);
	
	ResponseEntity<?>login(RegisterDto registerdto);
	
	ResponseEntity<?>logout(RegisterDto registerDto);
	
	ResponseEntity<?>getbyidrest(String id,HttpServletRequest request);

	String forgetpassword(RegisterDto registerdto);
	
	ResponseEntity<?>resetpasswrod(RegisterDto registerdto);

	String save(RegisterDto registerdto);
}
