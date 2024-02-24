package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.DTO.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "address")
	private String address;

	@Column(name = "description")
	private String description;

	@Column(name = "email")
	private String email;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "image")
	private String image;

	@Column(name = "password")
	private String password;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "status")
	private int status;
	
//	@Column(name = "cv_id")
//	private int cv;

	@Enumerated(EnumType.STRING)
	private ERole eRole;

	public User(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.address = userDTO.getAddress();
		this.description = userDTO.getDescription();
		this.email = userDTO.getEmail();
		this.fullName = userDTO.getFullName();
		this.image = userDTO.getImage();
		this.password = userDTO.getPassword();
		this.phoneNumber = userDTO.getPhoneNumber();
		this.status = userDTO.getStatus();
		if (userDTO.geteRole() == 1) {
			this.eRole = ERole.ROLE_USER;
		} else {
			this.eRole = ERole.ROLE_COMPANY;
		}

	}

	
//	 @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
//	 @OneToOne(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "cv_id",referencedColumnName = "id")
//	 Cv cv = new Cv();
	 
//	    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,optional=false)
//	    @JoinTable(name = "user_cv",
//	        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//	        inverseJoinColumns = {@JoinColumn(name = "cv_id", referencedColumnName = "id")}
//	    )

	 @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	 private List<Cv> cv = new ArrayList<>();
	 
	 @OneToMany( fetch = FetchType.EAGER,mappedBy = "user",cascade = CascadeType.ALL)
	 private List<Company> company ;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<ApplyPost> applyPostList = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<FollowCompany> followCompanyList = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<SaveJob> saveJobList = new ArrayList<>();

	
//	  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappe
//			  dBy =
//	  "user")
//	  
//	  @JoinColumn(name = "company_id") private List<Company> company = new
//	  ArrayList<>();
	 

	public User() {

	}



	public List<Company> getCompany() {
		return company;
	}



	public void setCompany(List<Company> company) {
		this.company = company;
	}



	public List<Cv> getCv() {
		return cv;
	}

	public void setCv(List<Cv> cv) {
		this.cv = cv;
	}

	public ERole geteRole() {
		return eRole;
	}

	public void seteRole(ERole eRole) {
		this.eRole = eRole;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

//	public Role getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Role roleId) {
//		this.roleId = roleId;
//	}

	public List<ApplyPost> getApplyPostList() {
		return applyPostList;
	}
	/*
	 * public Cv getCv() { return cv; }
	 * 
	 * public void setCv(Cv cv) { this.cv = cv; }
	 */

	public void setApplyPostList(List<ApplyPost> applyPostList) {
		this.applyPostList = applyPostList;
	}

	public List<FollowCompany> getFollowCompanyList() {
		return followCompanyList;
	}

	public void setFollowCompanyList(List<FollowCompany> followCompanyList) {
		this.followCompanyList = followCompanyList;
	}

	public List<SaveJob> getSaveJobList() {
		return saveJobList;
	}

	public void setSaveJobList(List<SaveJob> saveJobList) {
		this.saveJobList = saveJobList;
	}
	
	
	
	/*
	 * public List<Company> getCompany() { return company; }
	 * 
	 * public void setCompany(List<Company> company) { this.company = company; }
	 */

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", address=" + address + ", description=" + description + ", email=" + email
//				+ ", fullName=" + fullName + ", image=" + image + ", password=" + password + ", phoneNumber="
//				+ phoneNumber + ", status=" + status + ", eRole=" + eRole + ", cvList=" + cvList + ", applyPostList="
//				+ applyPostList + ", followCompanyList=" + followCompanyList + ", saveJobList=" + saveJobList
//				+ ", company=" + company + "]";
//	}

}
