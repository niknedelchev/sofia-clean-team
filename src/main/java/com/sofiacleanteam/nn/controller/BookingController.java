package com.sofiacleanteam.nn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sofiacleanteam.nn.service.CalendarService;

@Controller
public class BookingController {
	@Autowired
	CalendarService calendarService;
	
	
	
}
