package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentRegisterDto;
import com.example.demo.service.StudentRegister;

@RestController
@RequestMapping("/payment")
//@PreAuthorize(" hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")

public class PaymentController {

	@Autowired
	StudentRegister studentregister;

	@PostMapping(value = "/paymentsection", produces = "application/json")
	public ResponseEntity<?> paymentsection(@RequestBody StudentRegisterDto studentregisterdto) {
		ResponseEntity<?> output = studentregister.paymentstatus(studentregisterdto);
		return ResponseEntity.ok(output);

	}
	@GetMapping(value="/getbyid/{id}",produces="application/json")
	public ResponseEntity<?>getbyid(@PathVariable String id){
		return studentregister.findbyid(id);
		
		
	}

}
