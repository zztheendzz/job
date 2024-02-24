package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Recruitment;

@Repository
public interface RecruitmentRepository  extends JpaRepository<Recruitment, Integer> {

		Optional<Recruitment> findById(int id);
		
		  @Query(value= "SELECT * FROM Recruitment r WHERE r.company_id = :company_id")
		  List<Recruitment> getListByCompanyId(@Param("company_id") Integer company_id);
		  


}


