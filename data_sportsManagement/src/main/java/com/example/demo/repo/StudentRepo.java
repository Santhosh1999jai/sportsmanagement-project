package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentModel;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, String>{

	
}
