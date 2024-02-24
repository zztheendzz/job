package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Company;
import com.example.demo.model.FollowCompany;
import com.example.demo.model.Recruitment;
import com.example.demo.model.User;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.FollowCompanyRepository;
import com.example.demo.repository.RecruitmentRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CompanyService{
	@Autowired 
	private CompanyRepository companyRepository;
	@Autowired
	private RecruitmentRepository rr;
	@Autowired
	private UserRepository ur;
	@Autowired
	private FollowCompanyRepository fr;
	


	
	public List<Company> getListCompanies() {
		List<Company>  listCompany= companyRepository.findAll();
		return listCompany;
	}
//		Optional<FollowCompany> findByfullName(String fullName);
	public Company getCompanyFromRui(int idR) {
//		idR = id of recruitment
		Recruitment recruitment = new Recruitment();
		List<Recruitment>  listRecruitment= rr.findAll();
		
		for (Recruitment recruitment2 : listRecruitment) {
			if(recruitment2.getId() == idR) {
				recruitment = recruitment2;
			}
			
		}
		Company  company = recruitment.getCompany();
		return company;
	}
	
	public Company getCompanyFromUser(int idU) {
//		idU = id of User
		Company  c = new Company();
		List<Company> companies = companyRepository.findAll();
		List<User> users = ur.findAll();
		
		for (User user : users) {
			if(user.getId()==idU) {
				c= user.getCompany().get(0);
			}
		}
		
//		for (Company company : companies) {
//			if(company.getUser().getId()==idU) {
//				c= company;
//			}
//		}
		return c;
	}
	
	public List<Company> getListFollowCompanyFromUser(int idU) {

		List<FollowCompany> followCompanys = fr.getListByUserId(idU);
		
		if(followCompanys == null) {
			return Collections.emptyList();
		}
		else {
			List<Company> companies = new ArrayList<>();
			for (int i = 0; i < followCompanys.size(); i++) {
				companies.add(followCompanys.get(i).getCompany());
				
			}
			System.out.println(followCompanys.size()+"sizecompany");
			return companies;
		}
	}
	
	public void updateCompany(Company company, User user) {

		List<Company>  companies= companyRepository.findAll();
		List<User>  users= ur.findAll();
		
		Company c = user.getCompany().get(0);
		
				if (user.getCompany().size()!=0) 
			{
		
					c.setEmail(company.getEmail());
					c.setNameCompany(company.getNameCompany());
					c.setAddress(company.getAddress());
					c.setPhoneNumber(company.getPhoneNumber());
					c.setDescription(company.getDescription());
					if(company.getLogo()!= null) {
						c.setLogo(company.getLogo());
					}
					companyRepository.saveAndFlush(c);	

			}


		
//		for (Company c : companies) {
//			if(c.getId() == company.getId()) {
//				check = false;
//				c.setEmail(company.getEmail());
//				c.setNameCompany(company.getNameCompany());
//				c.setAddress(company.getAddress());
//				c.setPhoneNumber(company.getPhoneNumber());
//				c.setDescription(company.getDescription());
//				if(company.getLogo()!= null) {
//				c.setLogo(company.getLogo());
//				}
//				companyRepository.saveAndFlush(c);
//				break;
//			}
		
		}
//	hiển thị công ty nổi bật : lấy ra ds công ty tuyển nhiều nhất
	
	public List<Company> companyMap() {
		
		List<Company> companies = companyRepository.findAll();
		List<Company>coms = new ArrayList<>();
		 Map<Company, Integer> map = new HashMap<>();
			for (Company company : companies) {
				map.put(company, company.getRecruitmentList().size());
				
			}
		 
		 Map<Company, Integer> sortedMapByValue =  map.entrySet().stream()
         .sorted((o1,o2)->o2.getValue().compareTo(o1.getValue()))
         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue,newValue)->oldValue,LinkedHashMap::new));
		 
		 Set<Company> set = sortedMapByValue.keySet();
		 
	        for (Company key : set) {
	        	coms.add(key);
	        	if(coms.size()>=3) {
	        		break;
	        	}
	        }
	        System.out.println("Original map: " + map);
	        System.out.println("Sorted map by value: " + sortedMapByValue);
		return coms;
	}
		
	
	
	public Company getComByid(int  idCom) {
		List<Company>  companies= companyRepository.findAll();
		Company company = new Company();
		for (Company c : companies) {
			if(c.getId() == idCom) {
				return c;

			}
			
		}
		return company;
	}

	
}


