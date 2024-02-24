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
//import com.example.demo.model.Category;
//
//@Repository
//@Transactional
//@Component("CategoryDao")
//public class CategoryDao {
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
//	public List<Category> getAllCategories(){
//		List<Category>  categoriesList = null;
////		from User => User là tên class, truy vấn theo tên lớp chứ k còn theo tên bảng
//		categoriesList = getSession().createQuery("from Category",Category.class).getResultList();
//		return categoriesList;
//	}
//
//}
