package com.sofiacleanteam.nn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofiacleanteam.nn.model.Category;
import com.sofiacleanteam.nn.model.JobType;

public interface JobTypeRepository extends JpaRepository<JobType, Integer> {
	List<JobType> findAllByCategory(Category category);
	List<JobType> findAllByPromo(boolean promo);
}
