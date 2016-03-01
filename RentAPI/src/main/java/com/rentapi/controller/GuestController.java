package com.rentapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.rentapi.model.ApartmentInfo;
import com.rentapi.model.ContactInfo;
import com.rentapi.model.SearchQuery;
import com.rentapi.model.SearchResultItem;
import com.rentapi.service.GuestService;

@Controller
@RequestMapping("/guest")
public class GuestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	private View jsonView;

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
	public ModelAndView search(@RequestParam(value = "noOfBedrooms", required = false) Integer noOfBedrooms,
			@RequestParam(value = "noOfBathrooms", required = false) Integer noOfBathrooms,
			@RequestParam(value = "patios", required = false) Integer patios) {
		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setNoOfBedrooms(noOfBedrooms);
		searchQuery.setNoOfBathrooms(noOfBathrooms);
		searchQuery.setPatios(patios);
		LOGGER.info("search");
		
		List<SearchResultItem> result = guestService.search(searchQuery);

		return new ModelAndView(jsonView, "data", result);
	}
	
	
	@RequestMapping(value = "/schedule", method = RequestMethod.POST, consumes = "application/json")
	public ModelAndView schedule() {

		LOGGER.info("schedule");

		return new ModelAndView(jsonView, "data", true);
	}

	@RequestMapping(value = "/getApartment", method = RequestMethod.GET)
	public ModelAndView getApartment(@RequestParam(value = "aptId", required = false) Integer apartmentId) {
		
		LOGGER.info("getApartment");
		
		ApartmentInfo result = guestService.getApartment(apartmentId);

		return new ModelAndView(jsonView, "data", result);
	}

	@RequestMapping(value = "/bookmarkApt", method = RequestMethod.POST, consumes = "application/json")
	public ModelAndView bookmarkApt(@RequestBody int aptId) {

		LOGGER.info("bookmarkApt");

		return new ModelAndView(jsonView, "data", aptId);
	}

}
