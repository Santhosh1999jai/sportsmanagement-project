package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AdminModel;


@Repository
public interface AdminRepo extends JpaRepository<AdminModel, Integer>{

	Optional<AdminModel> findByname(String name);

	Optional<AdminModel> findByemail(String email);
	
	Optional <AdminModel> findBytokens(String tokens);

	

	
}
