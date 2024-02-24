package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SaveJob;


@Repository
public interface SaveJobRepository  extends JpaRepository<SaveJob, Integer> {

//		Optional<FollowCompany> findByfullName(String fullName);
}
