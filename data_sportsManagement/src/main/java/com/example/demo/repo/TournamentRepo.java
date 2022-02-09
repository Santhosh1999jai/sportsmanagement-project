package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Tournament;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Integer>{

}
