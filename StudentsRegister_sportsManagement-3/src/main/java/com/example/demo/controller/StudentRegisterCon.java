package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentRegisterDto;
import com.example.demo.service.StudentRegister;

@RestController
@RequestMapping("/student")
@PreAuthorize(" hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
public class StudentRegisterCon {

	@Autowired
	StudentRegister studentregister;

	@PostMapping(value = "/savestudent", produces = "application/json")
	public ResponseEntity<?> savestudent(@RequestBody StudentRegisterDto studentregisterdto) {
		ResponseEntity<?> output = studentregister.studentRegister(studentregisterdto);
		return ResponseEntity.ok(output);

	}

	@PutMapping(value = "/updatestudent", produces = "application/json")
	public ResponseEntity<?> studentupdate(@RequestBody StudentRegisterDto studentregisterdto) {
		ResponseEntity<?> output = studentregister.updatestudent(studentregisterdto);
		return ResponseEntity.ok(output);

	}
	
	@GetMapping(value="/findallbyage",produces="application/json")
	public ResponseEntity<?>findall(){
		ResponseEntity<?>output=studentregister.getbyage();
		return ResponseEntity.ok(output);
	}
	
	

}
