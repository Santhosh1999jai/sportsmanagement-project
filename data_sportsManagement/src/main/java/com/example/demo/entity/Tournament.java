package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tournament_table")
public class Tournament {
	
	@Id
	@GeneratedValue
	private int id;
	private String teamname;
	private String finalmactch;
	private String semi_final;
	private String quater_match;

}
