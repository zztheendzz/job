package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;
import com.example.demo.model.FollowCompany;
import com.example.demo.model.User;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.FollowCompanyRepository;

@Repository
public class FollowCompanyService   {
	@Autowired
	FollowCompanyRepository fcR;
	
	@Autowired
	CompanyRepository cr;
	
	@Autowired
	CompanyService cs;

	public Boolean isFollow(User user, int idC) {
		System.out.println("daylaemail");
//		true : theo doi thanh cong
//		fales : ban phai dang nhap
//		khac : ban da theo doi 
		List<FollowCompany> list = fcR.findAll();
		Company company = new Company();
		company = cs.getComByid(idC);
		FollowCompany fc= new FollowCompany();
		boolean check = true;
		
		for (FollowCompany followCompany : list) {
			if(followCompany.getUser().getId()==user.getId() && followCompany.getCompany().getId()==idC)
			{
				check = false;
			}
		}
		if(check) {
					fc.setCompany(company);
					fc.setUser(user);

					fcR.save(fc);
					return true;
				}

		return false;
		
	}
	public void deleteFl(User user, int idC) {
		List<FollowCompany> followCompanies = fcR.findAll();
		for (FollowCompany fc : followCompanies) {
			if(fc.getUser().getId()==user.getId() && fc.getCompany().getId()==idC)
			{
				fcR.delete(fc);
			}
		}
	}
	
}

