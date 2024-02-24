package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Integer> {

		Optional<Company> findByNameCompany(String fullName);

		
}


