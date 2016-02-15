package com.rentapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("/guest")
public class GuestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	private View jsonView;

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView message() {
		LOGGER.info("message");
		return new ModelAndView(jsonView, "data", "Hello Guest");
	}

}
