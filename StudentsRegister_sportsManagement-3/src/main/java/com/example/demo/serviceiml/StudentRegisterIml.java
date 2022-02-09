package com.example.demo.serviceiml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentRegisterDto;
import com.example.demo.entity.SportsTimeTable;
import com.example.demo.entity.StudentModel;
import com.example.demo.repo.SportsTimeRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.StudentRegister;

@Service
public class StudentRegisterIml implements StudentRegister {

	@Autowired
	Environment env;

	@Autowired
	StudentRepo studentrepo;

	@Autowired
	SportsTimeRepo sportstimerepo;

	@Override
	public ResponseEntity<?> studentRegister(StudentRegisterDto studentregisterdto) {
		StudentModel studentmodel = StudentModel.builder().id(studentregisterdto.getId().toString())
				.address(studentregisterdto.getAddress()).department(studentregisterdto.getDepartment())
				.paymentstatus(studentregisterdto.getPaymentstatus()).age(studentregisterdto.getAge())
				.studentname(studentregisterdto.getStudentname()).build();
		studentrepo.save(studentmodel);

		return ResponseEntity.ok(studentmodel);
	}

	@Override
	public ResponseEntity<?> paymentstatus(StudentRegisterDto studentregisterdto) {
		try {
			StudentModel studentmodel = studentrepo.findById(studentregisterdto.getId()).get();

			SportsTimeTable sportstimetable = sportstimerepo.findByfees(studentregisterdto.getFees());

			if (sportstimetable.getSports_name().equalsIgnoreCase(studentmodel.getDepartment())
					&& sportstimetable.getFees() == studentregisterdto.getFees()) {
				studentmodel.setPaymentstatus("PAIDED");

				studentrepo.save(studentmodel);

				return ResponseEntity.ok(studentmodel);
			}
		}

		catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), env.getProperty("error")));
		}
		return null;
	}

	@Override
	public ResponseEntity<?> updatestudent(StudentRegisterDto studentregisterdto) {
		try {
			StudentModel model = studentrepo.findById(studentregisterdto.getId()).get();

			StudentModel modell = StudentModel.builder().id(model.getId()).address(studentregisterdto.getAddress())
					.department(studentregisterdto.getDepartment()).age(studentregisterdto.getAge()).build();
			studentrepo.save(modell);

			return ResponseEntity.ok(modell);
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), env.getProperty("error")));
		}
	}

	@Override
	public ResponseEntity<?> findbyid(String id) {
		Optional<StudentModel> model = studentrepo.findById(id);
		if(model.isPresent()){
		return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "Sucessfully", model));
	}
		else
		{
return ResponseEntity.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), ("error")));

		}
		}
	

	@Override
	public ResponseEntity<?> getbyage() {

		List<StudentModel> student = studentrepo.findAll();

		List<Integer> age1 = student.stream().filter(a -> a.getAge() > 18).map(a -> a.getAge())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "Sucessfully", age1));
	}

}
