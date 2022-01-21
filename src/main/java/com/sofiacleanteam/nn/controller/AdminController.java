package com.sofiacleanteam.nn.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofiacleanteam.nn.model.Category;
import com.sofiacleanteam.nn.model.Contact;
import com.sofiacleanteam.nn.model.Job;
import com.sofiacleanteam.nn.model.JobType;
import com.sofiacleanteam.nn.repository.ContactRepository;
import com.sofiacleanteam.nn.repository.FAQRepository;
import com.sofiacleanteam.nn.repository.JobRepository;
import com.sofiacleanteam.nn.repository.JobTypeRepository;

@Controller
public class AdminController {
	@Autowired
	JobRepository jobRepository;
	@Autowired
	JobTypeRepository jobTypeRepository;
	@Autowired
	ContactRepository contactRepository;
	@Autowired
	FAQRepository faqRepository;
	
	@GetMapping(path = "admin/admin-panel")
	public String showAdminPanelPage() {
		return "admin/admin-panel";
	}
	
	@GetMapping(path = "/admin/jobs")
	public String showJobsAdminPage(Model model, @RequestParam(required=false) String isCompleted) {
	List<Job> jobs; // =jobRepository.findAll()
	
	if(isCompleted!= null)
		if(isCompleted.equals("true"))
			jobs = jobRepository.findAll().stream().filter(j->j.isIsCompleted()).collect(Collectors.toList());
		else
			jobs = jobRepository.findAll().stream().filter(j->!j.isIsCompleted()).collect(Collectors.toList());
	else
		jobs = jobRepository.findAll();
	
		model.addAttribute("jobs", jobs);
		return "admin/jobs";
	}
	
	@GetMapping("/admin/jobs/edit/{id}")
	public String showJobUpdateForm(@PathVariable("id") int id, Model model) {
	    Job job = jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid brand Id:" + id));
	    model.addAttribute("job", job);
	    return "admin/jobs-edit";
	}
	
	@PostMapping("/admin/jobs/edit/{id}")
	public String updateJob(@ModelAttribute Job job) throws Exception {

		Job dbJob = jobRepository.findById(job.getId()).orElse(null);
		
		if (dbJob != null) {
			dbJob.setIsCompleted(job.isIsCompleted());
			dbJob.setPricePaid(job.getPricePaid());
			jobRepository.save(dbJob);
		} else {
			throw new Exception("Job not found");
		}
		
	    return "redirect:/admin/jobs";
	}
	    
	@GetMapping("/admin/jobs/delete/{id}")
	public String deleteJob(@PathVariable("id") int id, Model model) {
		Job job = jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid brand Id:" + id));
		jobRepository.delete(job);
	    return "redirect:/admin/jobs";
	}

	@GetMapping(path = "/admin/jobtypes")
	public String showJobTypesAdminPage(Model model) {
		model.addAttribute("jobTypes", jobTypeRepository.findAll());
		return "admin/jobtypes";
	}

	
	@GetMapping(path = "/admin/jobtype/add")
	public String showAddJobTypePage(Model model) {
		model.addAttribute("jobType", new JobType());
		List<Category> categories = Arrays.asList(Category.values());
		model.addAttribute("categories", categories);
		return "admin/jobtype-add";
	}
	@PostMapping(path = "/admin/jobtype/add")
	public String addJobType(@ModelAttribute JobType jobType) {
		jobTypeRepository.save(jobType);
		return "redirect:/admin/jobtypes";
	}

	@GetMapping("/admin/jobtype/edit/{id}")
	public String showJobTypeUpdateForm(@PathVariable("id") int id, Model model) {
	    JobType jobType = jobTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid brand Id:" + id));
	    model.addAttribute("jobType", jobType);
	    return "admin/jobtype-edit";
	}
	
	@PostMapping("/admin/jobtype/edit")
	public String updateJobType(@ModelAttribute JobType jobType) throws Exception {

		JobType dbJobType = jobTypeRepository.findById(jobType.getId()).orElse(null);
		
		if (dbJobType != null) {
			dbJobType.setName(jobType.getName());
			dbJobType.setPromo(jobType.isPromo());
			dbJobType.setPromoPrice(jobType.getPromoPrice());
			dbJobType.setRegularPrice(jobType.getRegularPrice());
			jobTypeRepository.save(dbJobType);
		} else {
			throw new Exception("Job type not found");
		}
		
	    return "redirect:/admin/jobtypes";
	}
	    
	@GetMapping("/admin/jobtype/delete/{id}")
	public String deleteJobType(@PathVariable("id") int id, Model model) {
		JobType jobType = jobTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid brand Id:" + id));
		List<Job> jobs = jobRepository.findAllByJobType(jobType);
		jobRepository.deleteAll(jobs);
		jobTypeRepository.delete(jobType);
	    return "redirect:/admin/jobtypes";
	}

	@GetMapping(path = "/admin/questions")
	public String showQuestionsAdminPage(Model model, @RequestParam(required=false) String isAnswered) {
		List<Contact> questions;
		
		if(isAnswered!= null)
			if(isAnswered.equals("true"))
				questions = contactRepository.findAll().stream().filter(q->q.isAnswered()).collect(Collectors.toList());
			else
				questions = contactRepository.findAll().stream().filter(q->!q.isAnswered()).collect(Collectors.toList());
		else
			questions = contactRepository.findAll();
		
		model.addAttribute("questions", questions);
		return "admin/questions";
	}

	@GetMapping(path = "/admin/questions/answered/{id}")
	public String setToAnsweredQuestionsStatus(@PathVariable("id") int id) {
		Contact question = contactRepository.findById(id).get();
		question.setAnswered(true);
		contactRepository.saveAndFlush(question);
		return "redirect:/admin/questions";
	}
	
	@GetMapping(path = "/admin/questions/delete/{id}")
	public String deleteQuestion(@PathVariable("id") int id) {
		Contact question = contactRepository.findById(id).get();
		contactRepository.delete(question);
		return "redirect:/admin/questions";
	}
	
}
