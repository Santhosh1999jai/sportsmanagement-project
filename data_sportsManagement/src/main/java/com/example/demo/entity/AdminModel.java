package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="admintable")
public class AdminModel {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String password;
	private String jwt;
	private String tokens;
	private String role;
	private String ActiveStatus;
	private LocalDateTime tokenCreationDate;
}
