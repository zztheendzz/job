package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.DTO.RecruitmentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recruitment")
public class Recruitment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "address")
	private String address;
	
	@Column(name = "created")
	private String created;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "experience")
	private String experience;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "rank_is")
	private String rankIs;

	@Column(name = "salary")
	private int salary;

	@Column(name = "status")
	private int status;

	@Column(name = "title")
	private String title;
	
	@Column(name = "view")
	private int view;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "deadline")
	private String deadline;
	
//	@Column(name = "category_id")
//	private int categoryId;
//	
//	@Column(name = "company_id")
//	private int companyId;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    Company company;
    
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "recruitment")
	private List<ApplyPost>applyPostList = new ArrayList<>();
	
	@OneToMany(mappedBy = "recruitment",fetch = FetchType.EAGER)
	private List<SaveJob>saveJobList = new ArrayList<>();

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

	public List<ApplyPost> getApplyPostList() {
		return applyPostList;
	}

	public void setApplyPostList(List<ApplyPost> applyPostList) {
		this.applyPostList = applyPostList;
	}

	public List<SaveJob> getSaveJobList() {
		return saveJobList;
	}

	public void setSaveJobList(List<SaveJob> saveJobList) {
		this.saveJobList = saveJobList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRankIs() {
		return rankIs;
	}

	public void setRankIs(String rankIs) {
		this.rankIs = rankIs;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}


	public String getDeadline() {
		return deadline;
	}

	public void setdeadline(String deadLine) {
		this.deadline = deadLine;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	

	@Override
	public String toString() {
		return "Recruitment [id=" + id + ", address=" + address + ", created=" + created + ", description="
				+ description + ", experience=" + experience + ", quantity=" + quantity + ", rankIs=" + rankIs
				+ ", salary=" + salary + ", status=" + status + ", title=" + title + ", view=" + view + ", type=" + type
				+ ", deadLine=" + deadline + ", category=" + category + ", company=" + company + ", applyPostList="
				+ applyPostList + ", saveJobList=" + saveJobList + "]";
	}
	
	public Recruitment (RecruitmentDTO recruitmentDTO) {
		  id = recruitmentDTO.getId();
		  address = recruitmentDTO.getAddress();
		  created = recruitmentDTO.getCreated();
		  description = recruitmentDTO.getDescription();		
		  experience = recruitmentDTO.getExperience();
		  quantity = recruitmentDTO.getQuantity();
		  rankIs = recruitmentDTO.getRankIs();
		  salary = recruitmentDTO.getSalary();
		  status = recruitmentDTO.getStatus();
		  title = recruitmentDTO.getTitle();
		  view = recruitmentDTO.getView();
		  type = recruitmentDTO.getType();
		  deadline = recruitmentDTO.getDeadLine();
   
	}
	public Recruitment() {}

	

}
