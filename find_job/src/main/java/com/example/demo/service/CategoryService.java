package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService  {
	@Autowired
	private CategoryRepository cr;
	
	public List<Category> getListCategory() {
		List<Category>  listCategory= cr.findAll();
		return listCategory;
	}
	public Category getCategoryById(int id) {
		List<Category>  listCategory= cr.findAll();
		Category category = new Category();
		
		for (Category c : listCategory) {
			if(category.getId() == id) {
				category=c;
			}
		}
		return category;
	}
//	lấy ra danh mục nổi bật
	public List<Category> categoryMap() {
		List<Category> categories = cr.findAll();
		List<Category>categories2= new ArrayList<>();
		Map<Category, Integer> map = new HashMap<>();
			for (Category category : categories) {

				map.put(category, category.getRecruitmentList().size());
			}
		 Map<Category, Integer> sortedMapByValue =  map.entrySet().stream()
         .sorted((o1,o2)->o2.getValue().compareTo(o1.getValue()))
         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue,newValue)->oldValue,LinkedHashMap::new));
		 
		 Set<Category> set = sortedMapByValue.keySet();
		 
	        for (Category key : set) {
	        	categories2.add(key);
	        	if(categories2.size()==4) {
	        		break;
	        	}
	        }
		return categories2;
	}
	
}


