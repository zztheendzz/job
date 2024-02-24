package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cv;
import com.example.demo.model.User;
import com.example.demo.repository.CvRepository;
import com.example.demo.repository.UserRepository;

@Repository
public class CvService   {

	@Autowired 
	CvRepository cvRepository;
	
	@Autowired 
	UserRepository ur;
	
	public void  addCv(String cvPath, User user) {
		boolean check = true;
		List<Cv> cvs = cvRepository.findAll();
		List<User> users = ur.findAll();
		
		
		for (Cv cv : cvs) {
			if(user.getCv().get(0).getId() == cv.getId()) {
				cv.setFileName(cvPath);
				cvRepository.saveAndFlush(cv);
			}
		}
		
//		for (User user2 : users) {
//			if(user2.getId()== user.getId()) {
//				user2.getCv().get(0).setFileName(cvPath);
//				ur.saveAndFlush(user2);
//			}
//		}
//		user.getCv().get(0).setFileName(cvPath);
	}
	
	
	public Cv cvByUserId(int idU) {

			try {
				Cv  cv = new Cv();

				List<Cv> cvsList = cvRepository.findAll();
				List<User> users = ur.findAll();
				
				for (User user : users) {
					if(user.getId()==idU) {
						cv= user.getCv().get(0);
					}
				}
				return cv;
			} catch (Exception e) {
				e.printStackTrace();
				}

			return null;				
	}

}



