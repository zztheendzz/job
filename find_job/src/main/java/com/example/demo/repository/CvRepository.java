package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cv;

@Repository
public interface CvRepository  extends JpaRepository<Cv, Integer> {

	@Query(value= "SELECT cv FROM Cv u WHERE Cv.user_id = :user_id")
	Cv findByUserId(@Param("user_id") Integer user_id);
	
}


