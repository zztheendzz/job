package com.example.demo.DTO;

public class RecruitmentDTO {

	private int id;


	private String address;
	

	private String created;
	

	private String description;
	

	private String experience;


	private int quantity;


	private String rankIs;


	private int salary;

	private int status;


	private String title;
	

	private int view;
	

	private String type;
	

	private String deadline;
	

	private int  categoryId;
    

	private int companyId;


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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDeadLine() {
		return deadline;
	}


	public void setDeadLine(String deadLine) {
		this.deadline = deadLine;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	@Override
	public String toString() {
		return "RecruitmentDTO [id=" + id + ", address=" + address + ", created=" + created + ", description="
				+ description + ", experience=" + experience + ", quantity=" + quantity + ", rankIs=" + rankIs
				+ ", salary=" + salary + ", status=" + status + ", title=" + title + ", view=" + view + ", type=" + type
				+ ", deadLine=" + deadline + ", categoryId=" + categoryId + ", companyId=" + companyId + "]";
	}
    



}
