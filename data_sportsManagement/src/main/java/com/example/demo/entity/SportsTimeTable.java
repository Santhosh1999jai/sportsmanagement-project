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
@Table(name="sportsTimeAndFees")
public class SportsTimeTable {
	
	@Id
	private int id;
	private String sports_name;
	private String timedetails;
	private long fees;
}
