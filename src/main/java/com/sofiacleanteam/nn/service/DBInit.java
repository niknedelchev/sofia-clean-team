package com.sofiacleanteam.nn.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.sofiacleanteam.nn.model.AvailableHours;
import com.sofiacleanteam.nn.model.Category;
import com.sofiacleanteam.nn.model.Customer;
import com.sofiacleanteam.nn.model.FAQ;
import com.sofiacleanteam.nn.model.Job;
import com.sofiacleanteam.nn.model.JobType;
import com.sofiacleanteam.nn.repository.CustomerRepository;
import com.sofiacleanteam.nn.repository.FAQRepository;
import com.sofiacleanteam.nn.repository.JobRepository;
import com.sofiacleanteam.nn.repository.JobTypeRepository;
import com.sofiacleanteam.nn.repository.UserRepository;


@Service
public class DBInit implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private JobTypeRepository jobTypeRepository;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private FAQRepository faqRepository;
	

	@Override
	public void run(String... args) throws Exception {
	
		// Create users
		userService.registerAdmin("admin", "admin123");
		
		Customer cust1 = userService.registerCustomer("Pesho", "Peshev", "ppeshev","customer123");
		Customer cust2 = userService.registerCustomer("Georgi", "Georgiev", "ggeorgiev","customer123");
		Customer cust3 = userService.registerCustomer("Toma", "Tomov", "ttomov","customer123");

		//Create job types
		List<JobType> jobTypes = new ArrayList<>();
		jobTypes.add(new JobType(0,"Килими до 30 кв.","images/thumbnails/7.jpg", Category.CARPET_WASH,null,2.50,true,2.00,"на кв.м."));
		jobTypes.add(new JobType(0,"Килими над 30 кв.","images/thumbnails/7.jpg", Category.CARPET_WASH,null,1.50,false,0,"на кв.м."));
		jobTypes.add(new JobType(0,"Цялостно почистване	- нормално замърсяване", "images/thumbnails/10.jpg", Category.COMPLETE_CLEANING,null,3.00,false,0,"на кв.м."));
		jobTypes.add(new JobType(0,"Цялостно почистване	- силно замърсяване", "images/thumbnails/10.jpg", Category.COMPLETE_CLEANING,null,5,false,0,"на кв.м."));
		jobTypes.add(new JobType(0,"Почистване след ремонт", "images/thumbnails/10.jpg", Category.COMPLETE_CLEANING,null,5.00,false,0,"на кв.м."));
		jobTypes.add(new JobType(0,"Пране на матрак","images/thumbnails/13.jpg", Category.FURNITURE_WASH,null,12,false,0,"за бр.еднолицев"));
		jobTypes.add(new JobType(0,"Пране на диван","images/thumbnails/13.jpg", Category.FURNITURE_WASH,null,10,false,0,"на седящо място"));
		jobTypes.add(new JobType(0,"Стандартно почистване","images/thumbnails/11.jpg", Category.OFFICE_CLEANING,null,2.00,true,1.50,"на кв.м."));
		jobTypes.add(new JobType(0,"Цялостно почистване	на дома - нормално замърсяване", "images/thumbnails/8.jpg",Category.RESIDENTIAL_CLEANING,null,3.00,false,0,"на кв.м."));
		jobTypes.add(new JobType(0,"Цялостно почистване	на дома - силно замърсяване","images/thumbnails/12.jpg",Category.WINDOWS_CLEANING,null,5.00,true,4.00,"на кв.м."));
		jobTypes.add(new JobType(0,"Комплексно почистване на санитарни помещения","images/thumbnails/12.jpg",Category.WINDOWS_CLEANING,null,45.00,false,0.00,"на брой"));
		jobTypes.add(new JobType(0,"Абонамент - стандартно почистване на дома", "images/thumbnails/9.jpg", Category.SUBSCRIPTION,null,60,false,0,"на посещение"));
		jobTypes.add(new JobType(0,"Машинно почистване на твърди повърхности","images/thumbnails/15.jpg", Category.OTHERS, null,1.20,false,0,"на кв.м."));
		
		jobTypeRepository.saveAllAndFlush(jobTypes);
		
		List<Job> jobs = new ArrayList<>();
		jobs.add(new Job(0,cust1,jobTypes.get(0),LocalDate.of(2022, 1, 10),AvailableHours.H10,false,jobTypes.get(0).getRegularPrice()));
		jobs.add(new Job(0,cust1,jobTypes.get(1),LocalDate.of(2022, 1, 10),AvailableHours.H9,false,jobTypes.get(1).getRegularPrice()));
		jobs.add(new Job(0,cust1,jobTypes.get(2),LocalDate.of(2022, 1, 10),AvailableHours.H11,false,jobTypes.get(2).getRegularPrice()));
		jobs.add(new Job(0,cust1,jobTypes.get(3),LocalDate.of(2022, 1, 11),AvailableHours.H17,true,jobTypes.get(3).getRegularPrice()));
		jobs.add(new Job(0,cust1,jobTypes.get(4),LocalDate.of(2022, 1, 11),AvailableHours.H12,false,jobTypes.get(4).getRegularPrice()));
		jobs.add(new Job(0,cust1,jobTypes.get(5),LocalDate.of(2022, 1, 12),AvailableHours.H13,false,jobTypes.get(5).getRegularPrice()));
		jobs.add(new Job(0,cust1,jobTypes.get(6),LocalDate.of(2022, 1, 12),AvailableHours.H11,false,jobTypes.get(6).getRegularPrice()));
		jobs.add(new Job(0,cust1,jobTypes.get(7),LocalDate.of(2022, 1, 12),AvailableHours.H16,false,jobTypes.get(7).getRegularPrice()));
		
		jobRepository.saveAllAndFlush(jobs);
	
		List<FAQ> faqs = new ArrayList<>();
		faqs.add(new FAQ(0,"Как почиствате?", "Използваме спецални, екологични препарати и професионална техника за отлични резултати.", "общи"));
		faqs.add(new FAQ(0,"С какви препарати почиствате?", "Използваме верифицирани, анти-алергенни и високоефективни препарати с минимален отпечатък за околната среда.", "общи"));
		faqs.add(new FAQ(0,"Сигурна ли е услугата ви?", "Хората от екипа са внимателно подбрани, като те минават през различни етапи на проверка, селекция и обучение преди да бъдат изпратени на обект.  Всеки екип се ръководи от ръководтел, който следи за качествено почистване и сигурността на имота.", "общи"));
		faqs.add(new FAQ(0,"Кога да направя поръчката си?", "Календара с ангажименти при резервиране на посещение дава актуална информация за поетите от нас ангажименти за почистване. При наличие на свободен час може да направите заявка за посещение, а ние ще се свържем с вас, за да потвърдим. Необходимо е да сте регистриран потребител.", "общи"));
		faqs.add(new FAQ(0,"Необходимо ли е да съм вкъщи по време на чистене?", "Това зависи от вашите предпочитания - ако желаете да присъствате, то това е ваше право, ако пък ли не, то е необходимо да уточним как да достъпим имота.", "общи"));
		faqs.add(new FAQ(0,"Кога трябва да заплатя?", "Когато услугата бъде извършена ще ви бъде издадена бележка и тогава е нужно да платите. Преди това ще получите оценка за очакваната сума за заплащане.", "общи"));
		faqs.add(new FAQ(0,"Трябва ли имота да бъде посетен предварително от ваш служител?", "Не. Когато нашият екип дойде в заявения и потвърден ден и час, то вие ще получите оценка на желаните почистващи услуги. След почистване ще е е необходимо да заплатите ползваните услуги.", "общи"));
		faqs.add(new FAQ(0,"Ако имам рекламация?", "В такъв случай моля свържете се с ръководителя на екипа, или с офиса на телефона за контакти, за да обсъдим как можем да помогнем.", "общи"));
		faqs.add(new FAQ(0,"Какви видове услуги предлагате?", "Предлагаме широк набор от професионални почистващи услуги. В секцията Услуги може да намерите детайлна иформация по категории услуги.", "общи"));
		
		faqRepository.saveAllAndFlush(faqs);
	}

}
