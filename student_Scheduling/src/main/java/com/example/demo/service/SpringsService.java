package com.example.demo.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentModel;
import com.example.demo.repo.StudentRepo;
import com.example.demo.ser.StudentService;

@Service
public class SpringsService implements StudentService{
	
	@Autowired
	StudentRepo studentrepo;
	
	
	
	@Override
	@Scheduled(cron = "*/10 * * * * *")
	public ResponseEntity<?> getall() {
		List<StudentModel> model = studentrepo.findAll();

		Stream<StudentModel> studentstream = model.stream();
		Stream<StudentModel>filterstudent=studentstream.filter(m->m.getPaymentstatus().equals("NOTPAID"));
		//Stream<StudentModel> filterstudent = studentstream.filter(m -> m.paymentstatus().equals("notpaid"));
//		Stream<String>mapstream=
		Stream<Object> mapstream = filterstudent
				.map(m -> new StudentModel(m.getId(), m.getStudentname(), m.getAddress(), m.getPaymentstatus()));
		mapstream.forEach(System.out::println);
		return null;
	}

}
