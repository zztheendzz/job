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
//import com.example.demo.model.User;
//
//
//
//@Repository
//@Transactional
//@Component("UserDao")
//public class UserDao { 
//
//	
////	@Autowired
////	private UserRepository ur;
//	
//	@Autowired
//	private SessionFactory factory;
//
//	public Session getSession() {
//		
//		
//		Session session = factory.getCurrentSession();
//		if (session == null) {
//			session = factory.openSession();
//		}
//		return session;
//	}
//	
////	  Lấy tất cả danh sách user
//	public List<User> getAllListUser(){
//		List<User>  listUsers = null;
//
//
////		from User => User là tên class, truy vấn theo tên lớp chứ k còn theo tên bảng
//		listUsers = getSession().createQuery("from User",User.class).getResultList();
//		
//
//		return listUsers;
//	}
//	
//	
////	
////// thêm mới
////	public void saveUser() {
////
////
////	} 
////	public void saveUser(User user) {
////		
////		getSession().save(user);
////	} 
////
//////	 update
////	public void update( User newUser) {
////		List<User>getListUser = getListUser();
////		
////		try{
////			
////			for(User u : getListUser ) {
////				if(u.getId()==newUser.getId()) {
////				getSession().evict(u);
////				u = newUser;
////				getSession().update(u);
////				break;
////				}
////			}
////		}catch(Exception e) {
////			e.printStackTrace();
////		}
////
////	} 
//////	Lấy ds user có status!=3
////	public List<User> getListUser(){
////		List<User>  listUsers = null;
////		String hql = " FROM User " + "where status != 3";
////		Query query =   getSession().createQuery(hql);
////		listUsers = query.getResultList();
////		return listUsers;
////	}
////
////
//
//
//}