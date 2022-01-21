package com.sofiacleanteam.nn.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "job_types")
public class JobType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	String name;

	String image;

	private Category category;
	
	@OneToMany(mappedBy =  "jobType")
	private Set<Job> jobs;
	
	@Column(name = "regular_price")
	double regularPrice;
	
	@Column(name = "is_promo")
	boolean promo;
	
	@Column(name = "promo_price")
	double promoPrice;

	@Column(name = "price_description")
	String priceDescription;
	
	public JobType() {
		super();
	}

	public JobType(int id, String name,String image, Category category,  Set<Job> jobs, double regularPrice, boolean promo, double promoPrice, String priceDescription) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.category = category;
		this.jobs=jobs;
		this.regularPrice = regularPrice;
		this.promo = promo;
		this.promoPrice = promoPrice;
		this.priceDescription = priceDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public boolean isPromo() {
		return promo;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	public double getPromoPrice() {
		return promoPrice;
	}

	public void setPromoPrice(double promoPrice) {
		this.promoPrice = promoPrice;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}
	
}
