package com.example.demo.DTO;

import com.example.demo.model.ERole;
import com.example.demo.model.User;

public class UserDTO {
	

	private int id;


	private String address;
	

	private String description;
	

	private String email;


	private String fullName;


	private String image;


	private String password;


	private String phoneNumber;


	private int status;
	
	private int eRole;
	
	
	

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

	public int geteRole() {
		return eRole;
	}

	public void seteRole(int eRole) {
		this.eRole = eRole;
	}
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", address=" + address + ", description=" + description + ", email=" + email
				+ ", fullName=" + fullName + ", image=" + image + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", status=" + status + ", eRole=" + eRole + "]";
	}

	public UserDTO( ) {}
	
	public UserDTO(User user) {
		
		
		this.id = user.getId();
		this.address = user.getAddress();
		this.description = user.getDescription();
		this.email = user.getEmail();
		this.fullName = user.getFullName();
		this.image = user.getImage();
		this.password = user.getPassword();
		this.phoneNumber = user.getPhoneNumber();
		this.status = user.getStatus();
		if(user.geteRole() == ERole.ROLE_USER ) {
			this.eRole= 1;
		}else {
			this.eRole= 2;
		}
	}
	
	

}
