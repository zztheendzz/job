package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

//	@OneToMany(mappedBy = "roleId",fetch = FetchType.EAGER)
//	private List<User>userList = new ArrayList<>();
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

//
//	public List<User> getUserList() {
//		return userList;
//	}
//
//
//	public void setUserList(List<User> userList) {
//		this.userList = userList;
//	}


	public Role() {

	}

}
