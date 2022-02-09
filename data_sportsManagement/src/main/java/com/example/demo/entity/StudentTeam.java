package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "student_team")
//@Builder
public class StudentTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private String team;
	private String department;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tid", referencedColumnName = "teamid")
	@JsonIgnoreProperties("studentteam")
	private TeamModel teammodel;
//	private String finalmactch;
//	private String semi_final;
//	private String quater_match;
}
