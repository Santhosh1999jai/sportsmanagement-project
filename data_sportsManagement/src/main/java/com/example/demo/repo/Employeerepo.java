package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AdminModel;
import com.example.demo.entity.EmployeeModel;

@Repository
@Transactional
public interface Employeerepo extends JpaRepository<EmployeeModel, Integer>{

	

	Optional<EmployeeModel> findByname(String name);

	 //@Query(value ="select u from EmployeeModel u where u.email = :email")
	Optional<EmployeeModel> findByemail( String email);

	Optional <EmployeeModel> findBytokens(String tokens);

}
