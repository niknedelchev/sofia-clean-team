package com.sofiacleanteam.nn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofiacleanteam.nn.repository.ContactRepository;
import com.sofiacleanteam.nn.repository.FAQRepository;
import com.sofiacleanteam.nn.repository.JobRepository;
import com.sofiacleanteam.nn.repository.JobTypeRepository;
import com.sofiacleanteam.nn.service.CalendarService;
import com.sofiacleanteam.nn.service.UserService;
import com.sofiacleanteam.nn.model.Contact;
import com.sofiacleanteam.nn.model.FAQ;
import com.sofiacleanteam.nn.model.JobType;
import com.sofiacleanteam.nn.model.User;
import com.sofiacleanteam.nn.model.AvailableHours;
import com.sofiacleanteam.nn.model.Category;
import com.sofiacleanteam.nn.model.Job;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	@Autowired
	private JobTypeRepository jobTypeRepository;
	@Autowired
	private FAQRepository faqRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private CalendarService calendarService;
	

	@GetMapping(path = "/")
	public String getIndexPage(Model model) {
		List<JobType> jobTypes = jobTypeRepository.findAll();
		List<JobType> jobTypes1 = new ArrayList<>();
		List<JobType> jobTypes2 = new ArrayList<>();

		List<Category> categories = new ArrayList<>();
		List<JobType> reducedJobTypes = new ArrayList<>();
				
		jobTypes.forEach(j->{
					if(!categories.contains(j.getCategory())) {
						reducedJobTypes.add(j);
						categories.add(j.getCategory());
					}
				});
		
		for (int i = 0; i < 4; i++) {
				jobTypes1.add(reducedJobTypes.get(i));
				jobTypes2.add(reducedJobTypes.get((i+4)));
		}
		
		model.addAttribute("jobTypes1", jobTypes1);
		model.addAttribute("jobTypes2", jobTypes2);
		return "index";
	}

	@GetMapping(path = "login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping(path = "register")
	public String showRegisterPage() {
		return "registration";
	}

	@PostMapping(path = "/register")
	public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username,
			@RequestParam String password) {

		userService.registerCustomer(firstName, lastName, username, password);

		return "redirect:/";
	}

	@GetMapping(path = "about")
	public String showAboutPage() {
		return "about";
	}

	@GetMapping(path = "cleaning-services")
	public String showCleaningServicesPage(Model model, @RequestParam(required = false) String category) {
		List<JobType> jobTypes = jobTypeRepository.findAll();
		List<Category> jobTypeCategories = jobTypes.stream().map(j->j.getCategory()).distinct().collect(Collectors.toList());
		
		model.addAttribute("jobTypes", jobTypes);
		model.addAttribute("jobTypeCategories", jobTypeCategories);
		
		String cat = jobTypes.get(0).getCategory().toString();

		if (category == null || category.isEmpty()) {
			// filter by first item's category
			List<JobType> selectedJobTypes = jobTypes.stream().filter(j -> j.getCategory().toString().equals(cat))
					.collect(Collectors.toList());
			model.addAttribute("selectedJobTypes", selectedJobTypes);
		} else {
			List<JobType> selectedJobTypes = jobTypes.stream().filter(j -> j.getCategory().toString().equals(category))
					.collect(Collectors.toList());
			model.addAttribute("selectedJobTypes", selectedJobTypes);
		}

		return "cleaning-services";
	}

	@GetMapping(path = "promotions")
	public String showPromotionsPage(Model model) {
		List<JobType> jobTypesOnPromo = jobTypeRepository.findAllByPromo(true);
		model.addAttribute("jobTypesOnPromo", jobTypesOnPromo);

		return "promotions";
	}

	@GetMapping(path = "bookings")
	public String showBookingPage(Model model, @RequestParam(required = false) String date) {
		model.addAttribute("date", null);
		model.addAttribute("week", null);
		
		if (date != null) {
			LocalDate parsedDate = LocalDate.parse(date);
			List<LocalDate> week = CalendarService.getFullWeek(parsedDate);
			List<String[]> calendarHeaderRow = CalendarService.getCalendarHeaderRow(week, parsedDate);
			
			Map<LocalDate, List<Job>> jobsForGivenDate = new HashMap<>();
			for (LocalDate currentDate : week) {
				jobsForGivenDate.put(currentDate, jobRepository.findAllByJobDate(currentDate));
				jobRepository.findAllByJobDate(currentDate);
			}
		
			Map<AvailableHours, Job[]> calendarRows = CalendarService.getCalendarRows(week,jobsForGivenDate);

			model.addAttribute("week", week);
			model.addAttribute("date", parsedDate);
			model.addAttribute("dayIndx", (parsedDate.getDayOfWeek().getValue()-1));
			model.addAttribute("calendarHeaderRow", calendarHeaderRow);
			model.addAttribute("calendarRows", calendarRows);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formatedDate = parsedDate.format(formatter);
			model.addAttribute("formatedDate", formatedDate);
			
			
		}
		
		model.addAttribute("jobTypes", jobTypeRepository.findAll());
		model.addAttribute("availableHours", Arrays.asList(AvailableHours.values()));
		return "bookings";
	}

	@PostMapping(path = "bookings")
	public String makeBooking(@RequestParam String jobDate, @RequestParam String jobTime, @RequestParam int jobType) {
		Job job = new Job();
		
		job.setJobDate(LocalDate.parse(jobDate));
		
		AvailableHours jobHour;
		if(jobTime.equals("H9"))
			jobHour = AvailableHours.H9;
		else if (jobTime.equals("H10"))
			jobHour = AvailableHours.H10;
		else if (jobTime.equals("H11"))
			jobHour = AvailableHours.H11;
		else if (jobTime.equals("H12"))
			jobHour = AvailableHours.H12;
		else if (jobTime.equals("H13"))
			jobHour = AvailableHours.H13;
		else if (jobTime.equals("H14"))
			jobHour = AvailableHours.H14;
		else if (jobTime.equals("H15"))
			jobHour = AvailableHours.H15;
		else if (jobTime.equals("H16"))
			jobHour = AvailableHours.H16;
		else if (jobTime.equals("H17"))
			jobHour = AvailableHours.H17;
		else	
			jobHour = AvailableHours.H18;
			
		job.setJobTime(jobHour);
		
		job.setJobType(jobTypeRepository.findById(jobType).get());
		job.setCustomer(userService.getCurrentUser().getCustomer());
		job.setPricePaid(0);
		job.setIsCompleted(false);
		
		jobRepository.save(job);
		
		return "redirect:/bookings?date="+jobDate;
	}

	@GetMapping(path = "faqs")
	public String showFAQsPage(Model model) {
		List<FAQ> faqs = faqRepository.findAll();
		model.addAttribute("faqs", faqs);

		return "faqs";
	}

	@GetMapping(path = "/contacts")
	public String showContactsPage(Model model) {
		model.addAttribute("contact", new Contact());
		return "contacts";
	}

	@PostMapping(path = "/contacts")
	public String saveContactsDetails(Model model, @ModelAttribute Contact contact) {
		contactRepository.save(contact);

		return "redirect:/contacts";
	}

	@GetMapping(path = "/customer")
	public String showCustomerProfilePage(Model model) {
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		return "customer-profile";
	}

	@GetMapping(path = "/admin")
	public String showAdminProfilePage(Model model) {
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		return "admin-profile";
	}

	@GetMapping(path = "terms")
	public String showTermsPage() {
		return "terms";
	}

	@GetMapping(path = "data-protection")
	public String showDataProtectionPage() {
		return "data-protection";
	}
	
	
}
