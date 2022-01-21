package com.sofiacleanteam.nn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "faqs")
public class FAQ {

	@Id
	@Column(name = "faq_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "category")
	private String category;

	
	public FAQ() {
		super();
	}
	
	
	public FAQ(int id, String title, String description, String category) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category=category;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	
	
	

}


