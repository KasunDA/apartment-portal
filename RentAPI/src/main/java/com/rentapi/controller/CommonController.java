package com.rentapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.rentapi.service.CommonService;

@Controller
@RequestMapping("/common")
public class CommonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private View jsonView;

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView message() {
		LOGGER.info("---in message(): " + commonService.showMessage());
		return new ModelAndView(jsonView, "data", commonService.showMessage());
	}

}
