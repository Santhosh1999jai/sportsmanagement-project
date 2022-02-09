package com.example.demo.serviceiml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SportsTimeTableDto;
import com.example.demo.entity.SportsTimeTable;
import com.example.demo.repo.SportsTimeRepo;
import com.example.demo.service.StudentService;
@Service
public class StudentServiceiml implements StudentService{

	@Autowired
	SportsTimeRepo timerepo;
	
	@Override
	public ResponseEntity<?> savetimetable(SportsTimeTableDto timetabledto) {
		SportsTimeTable timetable=SportsTimeTable.builder().sports_name(timetabledto.getSports_name())
				.fees(timetabledto.getFees()).timedetails(timetabledto.getTimedetails()).build();
		timerepo.save(timetable);
		return ResponseEntity.ok(timetable);
	}

	@Override
	public ResponseEntity<?> timetableupdate(SportsTimeTableDto timetabledto) {
		SportsTimeTable time=timerepo.findById(timetabledto.getId()).get();
		
		SportsTimeTable timetable=SportsTimeTable.builder().id(time.getId()).sports_name(timetabledto.getSports_name())
				.fees(timetabledto.getFees()).timedetails(timetabledto.getTimedetails()).build();
		timerepo.save(timetable);
		timerepo.save(timetable);
		return ResponseEntity.ok(timetable);
	}

}
