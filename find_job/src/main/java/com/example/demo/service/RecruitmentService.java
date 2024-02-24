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

import com.example.demo.DTO.RecruitmentDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Company;
import com.example.demo.model.Recruitment;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.RecruitmentRepository;

@Service
public class RecruitmentService {
	@Autowired
	private RecruitmentRepository rr;
	
	@Autowired
	private CategoryRepository catr;
	
	@Autowired
	private CompanyRepository comr;
	
	DateTime dateTime = new DateTime();
	

	public List<Recruitment> getListRecruitment() {
		List<Recruitment> list = new ArrayList<>();
		List<Recruitment>  listRecruitment= rr.findAll();
		for (Recruitment recruitment : listRecruitment) {
			if(recruitment.getStatus() != 3) {
				list.add(recruitment);
			}	
		}
		return list;
	}
	public List<Recruitment> getListRecruitmentByCateId(int id) {
		List<Recruitment> list = new ArrayList<>();
		Category category = new Category();
		category = catr.getReferenceById(id);
		List<Recruitment>  listRecruitment = category.getRecruitmentList();
		for (Recruitment recruitment : listRecruitment) {
			if(recruitment.getStatus() != 3) {
				list.add(recruitment);
			}	
		}
		return list;
	}
	public Recruitment getRecruitmentById(int id) {
		List<Recruitment>  listRecruitment =getListRecruitment();
		Recruitment recruitment = new Recruitment();
		
		for (Recruitment recruitment2 : listRecruitment) {
			if(recruitment2.getId() == id) {
				recruitment = recruitment2;
				return recruitment2;
			}
		}
		return recruitment;
	}
	public void addRecruitment(RecruitmentDTO recruitmentDTO, Company company, boolean a) {
		Recruitment recruitment = new Recruitment(recruitmentDTO);
		List<Category> listCategory  = catr.findAll();
		recruitment.setCompany(company);
		recruitment.setCreated(dateTime.getDate());
		for ( Category  category: listCategory) {
			if(category.getId() == recruitmentDTO.getCategoryId()) {
				recruitment.setCategory(category);
			}
		}
		rr.save(recruitment);
	}
	public List<Recruitment>  getListReFromCompanyId(int comId) {
		List<Recruitment>  listRecruitment = rr.getListByCompanyId(comId);
		return listRecruitment;
	}
	
	public void  addRecruitment(RecruitmentDTO  recruitmentDTO, Company company) {
		List<Recruitment>  listRecruitment = rr.findAll();
		Recruitment re = new Recruitment(recruitmentDTO);
		re.setCompany(company);
		re.setCategory(catr.getCateById(recruitmentDTO.getCategoryId()));
		for (Recruitment recruitment : listRecruitment) {
			if(recruitment.getId() == recruitmentDTO.getId()) {
				recruitment = re;
				rr.saveAndFlush(re);
				break;
			}
		}	
	}
	
	public void delete(int recruitmentId) {
		List<Recruitment>  listRecruitment = rr.findAll();
		
		for (Recruitment recruitment : listRecruitment) {
			if(recruitment.getId() == recruitmentId) {
				recruitment.setStatus(3);
				rr.saveAndFlush(recruitment);
				break;
			}
		}
		
	}
//	việc lam nổi bât : công việc tuyển nhiều ng nhất
	public List<Recruitment> recruitmentsMap() {
		
		List<Recruitment> recruitments = rr.findAll();
		List<Recruitment>recruit = new ArrayList<>();
		 Map<Recruitment, Integer> map = new HashMap<>();
			for (Recruitment recruitment : recruitments) {
				map.put(recruitment, recruitment.getQuantity());
			}
		 Map<Recruitment, Integer> sortedMapByValue =  map.entrySet().stream()
         .sorted((o1,o2)->o2.getValue().compareTo(o1.getValue()))
         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue,newValue)->oldValue,LinkedHashMap::new));
		 
		 Set<Recruitment> set = sortedMapByValue.keySet();
		 
	        for (Recruitment key : set) {
	        	recruit.add(key);
	        	if(recruit.size()>=3) {
	        		break;
	        	}
	        }
		return recruit;
	}
//	noi 2 arraylist
	public List<Recruitment> joinListRecruitment(List<Recruitment> ListRecruitment1,List<Recruitment> ListRecruitment2) {
		for (Recruitment recruitment : ListRecruitment2) {
			ListRecruitment1.add(recruitment);
		}
		List<Recruitment> listRecruitment = ListRecruitment1;
		return listRecruitment;
	}


}


