package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "teammodel")
public class TeamModel {

	@Id
	// @Column(name="team_id")
	private int teamid;
	private String teamname;
	private String finalmactch;
	private String semi_final;
	private String quater_match;

	@OneToMany(targetEntity = StudentTeam.class, fetch = FetchType.EAGER, mappedBy = "teammodel")
	@JsonIgnoreProperties("teammodel")
	private List<StudentTeam> studentteam;

}
