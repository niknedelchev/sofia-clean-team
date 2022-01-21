package com.sofiacleanteam.nn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {

	@Id
	@Column(name = "contact_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "cutomer_name")
	String customerName;
	
	@Column(name = "phone_number")
	String phoneNumber;
	
	@Column(name = "email")
	String email;
	
	String message;
	
	boolean isAnswered;

	public Contact() {
		super();
	}

	public Contact(int id, String customerName, String phoneNumber, String email, String message, boolean isAnswered) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.message = message;
		this.isAnswered = isAnswered;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	
	
	
}
