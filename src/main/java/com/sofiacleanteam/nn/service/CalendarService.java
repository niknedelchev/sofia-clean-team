package com.sofiacleanteam.nn.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofiacleanteam.nn.model.AvailableHours;
import com.sofiacleanteam.nn.model.Job;
import com.sofiacleanteam.nn.repository.JobRepository;

@Service
public class CalendarService {
	@Autowired
	private JobRepository jobRepository;

	
	public static List<LocalDate> getFullWeek(LocalDate date) {
		int day = date.getDayOfWeek().getValue();
		
		List<LocalDate> week = new ArrayList<>();

		for (int i = 1; i <= day; i++) {
			week.add(date.minusDays((day - i)));
		}

		for (int i = (day + 1); i < 8; i++) {
			week.add(date.plusDays((i - day)));
		}
		
		return week;
	}
	
	public static List<String[]> getCalendarHeaderRow (List<LocalDate> week, LocalDate parsedDate){
		List<String[]> calendarHeaderRow = new ArrayList<>();
		calendarHeaderRow.add(new String[]{"Начален час", "",""});
		
		if (parsedDate.getDayOfMonth() == week.get(0).getDayOfMonth())
			calendarHeaderRow.add(new String[]{"Понеделник", week.get(0).getDayOfMonth() + "." + week.get(0).getMonthValue() + "." + week.get(0).getYear(), "highlighted"});	
		else
			calendarHeaderRow.add(new String[]{"Понеделник", week.get(0).getDayOfMonth() + "." + week.get(0).getMonthValue() + "." + week.get(0).getYear(), ""});
		
		if (parsedDate.getDayOfMonth() == week.get(1).getDayOfMonth())
			calendarHeaderRow.add(new String[]{"Вторник", week.get(1).getDayOfMonth() + "." + week.get(1).getMonthValue() + "." + week.get(1).getYear(), "highlighted"});
		else
			calendarHeaderRow.add(new String[]{"Вторник", week.get(1).getDayOfMonth() + "." + week.get(1).getMonthValue() + "." + week.get(1).getYear(),""});
		
		if (parsedDate.getDayOfMonth() == week.get(2).getDayOfMonth())
			calendarHeaderRow.add(new String[]{"Сряда", week.get(2).getDayOfMonth() + "." + week.get(2).getMonthValue() + "." + week.get(2).getYear(), "highlighted"});
		else
			calendarHeaderRow.add(new String[]{"Сряда", week.get(2).getDayOfMonth() + "." + week.get(2).getMonthValue() + "." + week.get(2).getYear(),""});
		
		if (parsedDate.getDayOfMonth() == week.get(3).getDayOfMonth())
			calendarHeaderRow.add(new String[]{"Четвъртък", week.get(3).getDayOfMonth() + "." + week.get(3).getMonthValue() + "." + week.get(3).getYear(), "highlighted"});
		else
			calendarHeaderRow.add(new String[]{"Четвъртък", week.get(3).getDayOfMonth() + "." + week.get(3).getMonthValue() + "." + week.get(3).getYear(),""});
		
		if (parsedDate.getDayOfMonth() == week.get(4).getDayOfMonth())
			calendarHeaderRow.add(new String[]{"Петък", week.get(4).getDayOfMonth() + "." + week.get(4).getMonthValue() + "." + week.get(4).getYear(), "highlighted"});
		else
			calendarHeaderRow.add(new String[]{"Петък", week.get(4).getDayOfMonth() + "." + week.get(4).getMonthValue() + "." + week.get(4).getYear(), ""});
		
		if (parsedDate.getDayOfMonth() == week.get(5).getDayOfMonth())
			calendarHeaderRow.add(new String[]{"Събота", week.get(5).getDayOfMonth() + "." + week.get(5).getMonthValue() + "." + week.get(5).getYear(), "highlighted"});
		else
			calendarHeaderRow.add(new String[]{"Събота", week.get(5).getDayOfMonth() + "." + week.get(5).getMonthValue() + "." + week.get(5).getYear(), ""});
		
		if (parsedDate.getDayOfMonth() == week.get(6).getDayOfMonth())
			calendarHeaderRow.add(new String[]{"Неделя", week.get(6).getDayOfMonth() + "." + week.get(6).getMonthValue() + "." + week.get(6).getYear(), "highlighted"});
		else
			calendarHeaderRow.add(new String[]{"Неделя", week.get(6).getDayOfMonth() + "." + week.get(6).getMonthValue() + "." + week.get(6).getYear(), ""});		
		
		
		return calendarHeaderRow;
	}
	
	public static Map<AvailableHours, Job[]> getCalendarRows(List<LocalDate> week, Map<LocalDate, List<Job>> jobsForGivenDate){
		Map<AvailableHours, Job[]> calendarRows = new TreeMap<>();
		
		AvailableHours[] hours = AvailableHours.values();
		
		for (int i = 0; i< hours.length; i++ ) {
			Job[] jobs = new Job[7];
			
			for (int j=0;j< week.size();j++) {
				jobs[j]=null;
				
				List<Job> jobsForCurrentDate = jobsForGivenDate.get(week.get(j));
				
				for (Job currentJob : jobsForCurrentDate) {
					if (currentJob.getJobTime() == hours[i]) 
					{
						jobs[j] = currentJob;
					}
						
				}
				
			}
			calendarRows.put(hours[i], jobs);
	}
		
		return calendarRows;
	}
	
}
