package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RegisterService;

@RestController
@PreAuthorize(" hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")

public class TemplateController {

	@Autowired
	RegisterService registerservice;
	
	
	@GetMapping(value = "/getbyrestkkkid/{id}", produces = "application/json")
	public ResponseEntity<?> getbyrest(@PathVariable (value="id")String id, HttpServletRequest request) {
		return registerservice.getbyidrest(id, request);

	}
	
}
