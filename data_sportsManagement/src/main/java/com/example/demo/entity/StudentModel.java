package com.example.demo.entity;

import javax.persistence.Entity;
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
@Table(name = "studenttable")
public class StudentModel {

	
	@Id
	private String id;
	private String studentname;
	private String paymentstatus;

	private String address;
	private String department;
	private int age;
	
	public StudentModel(String id, String studentname, String address, String paymentstatus) {
		this.id=id;
		this.studentname=studentname;
		this.address=address;
		this.paymentstatus=paymentstatus;
	}
	
	
//	private String finalmactch;
//	private String semi_final;
//	private String quater_match;

}
