package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.SportsTimeTableDto;

public interface StudentService {
	ResponseEntity<?>savetimetable(SportsTimeTableDto timetabledto);
	
	ResponseEntity<?>timetableupdate(SportsTimeTableDto timetabledto);

}
