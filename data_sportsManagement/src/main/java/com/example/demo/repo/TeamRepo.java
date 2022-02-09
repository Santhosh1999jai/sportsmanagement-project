package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TeamModel;


@Repository
public interface TeamRepo extends JpaRepository<TeamModel, Integer>{

}
