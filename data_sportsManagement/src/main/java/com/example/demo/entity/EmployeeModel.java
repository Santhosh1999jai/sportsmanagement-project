package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="employeetable")
public class EmployeeModel {
	
	@Id
	@GeneratedValue

	private int id;
	//private String empname;
	private String name;
	private String email;
	private String jwt;
	private String password;
	private String role;
	private String tokens;
	private String ActiveStatus;
	private LocalDateTime tokenCreationDate;

}
