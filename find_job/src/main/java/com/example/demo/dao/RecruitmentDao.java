//package com.example.demo.dao;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.demo.model.Recruitment;
//
//@Repository
//@Transactional
//@Component("RecruitmentDao")
//public class RecruitmentDao {
//	
//	
//	@Autowired
//	private SessionFactory factory;
//
//	public Session getSession() {
//		Session session = factory.getCurrentSession();
//		if (session == null) {
//			session = factory.openSession();
//		}
//		return session;
//	}
//	
////	  Lấy tất cả danh sách categories
//	public List<Recruitment> getAllRecruitment(){
//		List<Recruitment>  recruitmentList = getSession().createQuery("from Recruitment",Recruitment.class).getResultList();
//		return recruitmentList;
//		
//	}
//	
//
//}
