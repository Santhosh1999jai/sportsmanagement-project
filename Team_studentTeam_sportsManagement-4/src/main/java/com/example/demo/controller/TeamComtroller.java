package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TeamDto;
import com.example.demo.service.TeamService;

@RestController
@RequestMapping("/teamcontroller")
@PreAuthorize(" hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")

public class TeamComtroller {

	@Autowired
	TeamService teamservice;

	@PostMapping(value = "/saveteam", produces = "application/json")
	public ResponseEntity<?> saveteam(@RequestBody TeamDto teamdto) {
		ResponseEntity<?> output = teamservice.saveteam(teamdto);
		return ResponseEntity.ok(output);

	}

	@PostMapping(value = "/savetournament", produces = "application/json")
	public ResponseEntity<?> savetournament(@RequestBody TeamDto teamdto) {
		ResponseEntity<?> output = teamservice.updatetournament(teamdto);
		return ResponseEntity.ok(output);

	}

}
