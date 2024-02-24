package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SaveJob;
import com.example.demo.model.User;
import com.example.demo.repository.SaveJobRepository;
import com.example.demo.repository.UserRepository;


@Service
public class  SaveJobService  {

	@Autowired
	private SaveJobRepository sjr;
	@Autowired
	private UserRepository ur;
	@Autowired
	private RecruitmentService rs;
	
	
	public List<SaveJob> getSaveJobs() {
		List<SaveJob>  listSaveJobs = sjr.findAll();
		List<SaveJob>  list = new ArrayList<>();
		
		for (SaveJob saveJob : listSaveJobs) {
			
			if(saveJob.getStatus()!=3) {
				list.add(saveJob);
			}
			
		}
		return list;
	}
//	lay list savejob cua 1 user theo id cua user
	public List<SaveJob> getSaveJobsFromUser(int idU) {
		List<SaveJob>  listSaveJobs=new ArrayList<>();
		List<User> uList = ur.findAll();
		List<SaveJob>  list=new ArrayList<>();
		for (User user : uList) {
			if(user.getId() == idU)
			{
				 listSaveJobs = user.getSaveJobList();
				break;
			}
		}
		for (SaveJob saveJob : listSaveJobs) {
			
			if(saveJob.getStatus()!=3) {
				list.add(saveJob);
			}
			
		}
		return list;
	}
	
	public Boolean addSaveJob(User user, int idRe) {
		List<SaveJob>  listSaveJobs = getSaveJobsFromUser(user.getId());
		SaveJob sJob = new SaveJob();
		for (SaveJob saveJob : listSaveJobs) {
			if(saveJob.getRecruitment().getId() == idRe)
			{
				return null;
			}
		}
		sJob.setUser(user);
		sJob.setRecruitment(rs.getRecruitmentById(idRe));
		sjr.save(sJob);
		
		return true;
	}
	public void deleteSj( int idSj) {
		List<SaveJob>  listSaveJobs = sjr.findAll();
		for (SaveJob saveJob : listSaveJobs) {
			if(saveJob.getId()==idSj ) {
				saveJob.setStatus(3);
				sjr.saveAndFlush(saveJob);
				break;
			}
			
		}

	}

	
	
}
