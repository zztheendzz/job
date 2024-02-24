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
//import com.example.demo.model.ApplyPost;
//
//@Repository
//@Transactional
//@Component("ApplyPostDao")
//public class ApplyPostDao {
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
//
//	public List<ApplyPost>getAllListApplyPost(){
//		List<ApplyPost>  listApplyPost =  getSession().createQuery("from ApplyPost",ApplyPost.class).getResultList();
//
//		return listApplyPost;
//	}
//	
//	
//}
