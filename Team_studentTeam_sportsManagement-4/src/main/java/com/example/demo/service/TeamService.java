package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.TeamDto;

public interface TeamService {
	
	ResponseEntity<?>saveteam(TeamDto teamdto);

	ResponseEntity<?>updatetournament(TeamDto teamdto);
}
