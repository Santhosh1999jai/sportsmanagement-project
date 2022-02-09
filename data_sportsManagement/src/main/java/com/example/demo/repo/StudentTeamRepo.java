package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentTeam;

@Repository
public interface StudentTeamRepo extends JpaRepository<StudentTeam, Integer>{

}
