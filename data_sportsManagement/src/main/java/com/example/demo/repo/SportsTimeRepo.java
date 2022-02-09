package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SportsTimeTable;

@Repository
public interface SportsTimeRepo extends JpaRepository<SportsTimeTable, Integer>{

//	@Query("select l from SportsTimeTable l where sports_name:=sports_name")
//	SportsTimeTable findBysports_name(@Param("sports_name") String sports_name);

	SportsTimeTable findByfees(long fees);


}
