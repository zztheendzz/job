package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ApplyPost;
import com.example.demo.model.Cv;
import com.example.demo.model.Recruitment;
import com.example.demo.model.User;
import com.example.demo.repository.ApplyPostRepository;


@Service
public class ApplyPostService {
	@Autowired
	private ApplyPostRepository apr;
	
	@Autowired
	private CvService cvS;
	
//	lay danh sach apply ma co recruitment !=3
	public List<ApplyPost> getApplyPosts(int comId) {
		List<ApplyPost> list = new ArrayList<>();
		List<ApplyPost>  listApplyPosts= apr.findAll();
		
		for (ApplyPost applyPost : listApplyPosts) {
			if(applyPost.getRecruitment().getStatus()!=3 && applyPost.getRecruitment().getCompany().getId() == comId) {
				list.add(applyPost);
			}
		}
		return list;
	}
	
	public List<ApplyPost> getListApplyByReId(int recruitmentId) {
		List<ApplyPost>  listAPByReId= new ArrayList<>();
		List<ApplyPost>  listApplyPosts= apr.findAll();
		
		for (ApplyPost applyPost : listApplyPosts) {
			if(applyPost.getRecruitment().getId() == recruitmentId) {
				listAPByReId.add(applyPost); 
			}
		}
		return listAPByReId;
	}
	public boolean addApplyPost (User user, Recruitment recruitment) {
		List<ApplyPost> applyPosts = apr.findAll();
		
		for (ApplyPost applyPost : applyPosts) {
			if
			(applyPost.getUser().getId()==user.getId() && 
				applyPost.getRecruitment().getId()==recruitment.getId())
			{
				return false;
			}
		}
			ApplyPost applyPost = new ApplyPost();
			Cv cv= cvS.cvByUserId(user.getId());
			applyPost.setUser(user);
			applyPost.setRecruitment(recruitment);
			applyPost.setNameCv(cv.getFileName());
			apr.save(applyPost);
		return true;
	}
//	chap nhận ứng viên
	public ApplyPost approve(int idApply) {
		List<ApplyPost>  applyPosts= apr.findAll();
		ApplyPost  apl= new ApplyPost();
		
		for (ApplyPost applyPost : applyPosts) {
			if(applyPost.getId() == idApply) {
				applyPost.setStatus(1);
				apr.saveAndFlush(applyPost);
				apl=applyPost;
			}
		}
		return apl;
	}


}

