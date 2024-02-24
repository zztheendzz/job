package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {

//		Optional<FollowCompany> findByfullName(String fullName);
	  @Query(value= "SELECT cate FROM Category cate WHERE cate.id = :id")
	  Category getCateById(@Param("id") Integer id);
}


