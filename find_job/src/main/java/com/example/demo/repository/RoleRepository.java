package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {

//		Optional<FollowCompany> findByfullName(String fullName);
}

