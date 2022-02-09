package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SportsTimeTableDto;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/timetable")
public class StudentController {

	@Autowired
	StudentService studentservice;

	@PostMapping(value = "/save", produces = "application/json")
	public ResponseEntity<?> savetime(@RequestBody SportsTimeTableDto timetabledto) {
		ResponseEntity<?> output = studentservice.savetimetable(timetabledto);
		return ResponseEntity.ok(output);

	}
	@PutMapping(value = "/update", produces = "application/json")
	public ResponseEntity<?> updatetime(@RequestBody SportsTimeTableDto timetabledto) {
		ResponseEntity<?> output = studentservice.timetableupdate(timetabledto);
		return ResponseEntity.ok(output);

	}

}
