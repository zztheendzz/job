package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.Company;
import com.example.demo.model.Cv;
import com.example.demo.model.ERole;
import com.example.demo.model.User;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.CvRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private CompanyRepository cr;
	
	@Autowired
	private CvRepository cvR;
	
	public List<User> getListUsers() {
		List<User>  listUser= ur.findAll();
		return listUser;
	}
	public void updateUser(User updateUser) {
		User u = getUserFromId(updateUser.getId());
		u.setEmail(updateUser.getEmail()); 
		u.setFullName(updateUser.getFullName()); 
		u.setAddress(updateUser.getAddress());
		u.setPhoneNumber(updateUser.getPhoneNumber());
		u.setDescription(updateUser.getDescription());
		u.setImage(updateUser.getImage());
		ur.saveAndFlush(u);
	}
	public User getUserFromId(int id) {
		List<User> users = ur.findAll();
		for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	public User getUserByName(String name) {
		User user = new User();
		for (User u : getListUsers()) {
			if(u.getEmail().equals(name)) {
				user= u;
			}	
		}
		return user;
	}
	/* khi tạo tài khoản sẽ tạo luôn cty/cv tuỳ theo role của tk */
	public void saveUser(UserDTO uDto) {
		User user =new User(uDto);
		List<Company> companies = new ArrayList<>();
		Company company = new Company();
		Cv cv = new Cv();
		companies.add(company);
		if(user.geteRole() == ERole.ROLE_COMPANY) {
			company.setUser(user);
			cr.saveAndFlush(company);
		}else if (user.geteRole() == ERole.ROLE_USER) {
			cv.setUser(user);
			cvR.saveAndFlush(cv);
		} {
			ur.save(user);
		}	
		
	}
	public void saveUser(User uDto) {
		ur.save(uDto);
	}


}
