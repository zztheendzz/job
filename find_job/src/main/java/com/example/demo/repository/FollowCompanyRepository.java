package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FollowCompany;

@Repository
public interface FollowCompanyRepository extends JpaRepository<FollowCompany, Integer> {

	
	  @Query(value= "SELECT * FROM FollowCompany fc WHERE fc.user_id = :user_id")
	  List<FollowCompany> getListByUserId(@Param("user_id") Integer user_id);
	 
}
