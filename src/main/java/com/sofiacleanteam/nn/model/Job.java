package com.sofiacleanteam.nn.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "job_type_id", nullable = false)
	private JobType jobType;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "job_date")
	private LocalDate jobDate;
	
	@Column(name = "job_time")
	private AvailableHours jobTime;

	@Column(name = "is_processed")
	private boolean isCompleted;
	
	@Column(name = "price_paid")
	double pricePaid;

	public Job() {
		super();
	}

	public Job(int id, Customer customer, JobType jobType, LocalDate jobDate, AvailableHours jobTime, boolean isCompleted,
			double pricePaid) {
		super();
		this.id = id;
		this.customer = customer;
		this.jobType = jobType;
		this.jobDate = jobDate;
		this.jobTime = jobTime;
		this.isCompleted = isCompleted;
		this.pricePaid = pricePaid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public LocalDate getJobDate() {
		return jobDate;
	}

	public void setJobDate(LocalDate jobDate) {
		this.jobDate = jobDate;
	}

	public AvailableHours getJobTime() {
		return jobTime;
	}

	public void setJobTime(AvailableHours jobTime) {
		this.jobTime = jobTime;
	}

	public boolean isIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}
	
	

}
