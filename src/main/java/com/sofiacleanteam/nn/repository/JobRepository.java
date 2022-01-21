package com.sofiacleanteam.nn.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofiacleanteam.nn.model.Job;
import com.sofiacleanteam.nn.model.JobType;

public interface JobRepository extends JpaRepository<Job, Integer> {
	public List<Job> findAllByJobDate(LocalDate jobDate);
	public List<Job> findAllByJobType(JobType jobType);

}
