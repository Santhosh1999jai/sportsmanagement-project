package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.StudentRegisterDto;

public interface StudentRegister {

	ResponseEntity<?> studentRegister(StudentRegisterDto studentregisterdto);

	ResponseEntity<?> paymentstatus(StudentRegisterDto studentregisterdto);

	ResponseEntity<?> updatestudent(StudentRegisterDto studentregisterdto);
	
	ResponseEntity<?> findbyid(String id);

	ResponseEntity<?> getbyage();
}
