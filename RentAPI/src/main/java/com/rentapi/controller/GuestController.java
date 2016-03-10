package com.rentapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.rentapi.model.Address;
import com.rentapi.model.ApartmentInfo;
import com.rentapi.model.ApartmentInfoBase;
import com.rentapi.model.Appointment;
import com.rentapi.model.ContactInfo;
import com.rentapi.model.ResidentApplication;
import com.rentapi.model.SearchQuery;
import com.rentapi.model.SearchResultItem;
import com.rentapi.service.GuestService;

@Controller
@RequestMapping("/guest")
public class GuestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	private View jsonView;
	
	private int residentId = 1;

	@Autowired
	private GuestService guestService;

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView message() {
		LOGGER.info("message");
		return new ModelAndView(jsonView, "data", "Hello Guest");
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST, consumes = "application/json")
	public ModelAndView contact(@RequestBody ContactInfo contactInfo) {

		LOGGER.info("contact");

		try {
			guestService.saveOrUpdate(contactInfo);
			return new ModelAndView(jsonView, "data", true);

		} catch (Exception ex) {
			LOGGER.error(ex.toString());
		}

		return new ModelAndView(jsonView, "data", false);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET, consumes = "application/json")
	public ModelAndView home() {

		LOGGER.info("home");

		return new ModelAndView(jsonView, "data", true);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "bedrooms", required = false) Integer noOfBedrooms,
			@RequestParam(value = "bathrooms", required = false) Integer noOfBathrooms,			
			@RequestParam(value = "garages", required = false) Integer garages) {
		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setNoOfBedrooms(noOfBedrooms);
		searchQuery.setNoOfBathrooms(noOfBathrooms);
		searchQuery.setGarages(garages);
		LOGGER.info("search");
		
		List<SearchResultItem> result = guestService.search(searchQuery);

		return new ModelAndView(jsonView, "data", result);
	}
	
	
	@RequestMapping(value = "/schedule", method = RequestMethod.POST, consumes = "application/json")
	public ModelAndView schedule(@RequestBody Appointment data) {

		LOGGER.info("schedule");
		
		guestService.ScheduleAppointment(data);

		return new ModelAndView(jsonView, "data", true);
	}

	@RequestMapping(value = "/getApartment", method = RequestMethod.GET)
	public ModelAndView getApartment(@RequestParam(value = "aptId", required = false) Integer apartmentId) {
		
		ApartmentInfo result = guestService.getApartment(apartmentId, residentId);

		return new ModelAndView(jsonView, "data", result);
	}

	@RequestMapping(value = "/bookmarkApt", method = RequestMethod.POST, consumes = "application/json")
	public ModelAndView bookmarkApt(@RequestBody ApartmentInfoBase data) {
		
		Boolean result = guestService.bookmarkApartment(data.getAptId(), residentId,data.getBookmarked());
		
		return new ModelAndView(jsonView, "data", result);
	}

	@RequestMapping(value = "/appointment/times", method = RequestMethod.GET)
	public ModelAndView getAppointmentTimes(@RequestParam(value = "requestDate", required = false) @DateTimeFormat(iso=ISO.DATE) LocalDate requestDate) {		
		//LocalDate localRequestDate = new LocalDate(requestDate.getYear(), requestDate.getMonth(), requestDate.getDay());		
		List<DateTime> availableDateTimes = guestService.GetAppointmentTimes(requestDate);
		
		List<String> times = new ArrayList<String>();
		DateTimeFormatter isoFormat = ISODateTimeFormat.dateTime();
		
		for(DateTime time : availableDateTimes){
			times.add(time.toString(isoFormat));
		}
		
		return new ModelAndView(jsonView, "data", times);
	}
	
	@RequestMapping(value = "/residentApplication", method = RequestMethod.POST, consumes = "application/json")
	public ModelAndView application(@RequestBody ResidentApplication residentApplication) {

		LOGGER.info("residentApplication");

		try {
			guestService.saveOrUpdate(residentApplication);
			return new ModelAndView(jsonView, "data", true);

		} catch (Exception ex) {
			LOGGER.error(ex.toString());
		}

		return new ModelAndView(jsonView, "data", false);
	}

	

}
