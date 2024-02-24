package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "number_choose")
	private int numberChoose;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER )
	private List<Recruitment>recruitmentList = new ArrayList<>();

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

	public int getNumberChoose() {
		return numberChoose;
	}

	public void setNumberChoose(int numberChoose) {
		this.numberChoose = numberChoose;
	}

	public List<Recruitment> getRecruitmentList() {
		return recruitmentList;
	}

	public void setRecruitmentList(List<Recruitment> recruitmentList) {
		this.recruitmentList = recruitmentList;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", numberChoose=" + numberChoose + ", recruitmentList="
				+ recruitmentList + "]";
	}
	
	

}
