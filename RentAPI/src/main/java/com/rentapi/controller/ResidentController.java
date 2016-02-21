package com.rentapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.rentapi.model.Issue;
import com.rentapi.model.Referral;
import com.rentapi.service.ResidentService;

@Controller
@RequestMapping("/resident")
public class ResidentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResidentController.class);
	
	private ResidentService residentService;
	
	@Autowired
	private View jsonView;
	
	@Autowired
	public ResidentController(ResidentService residentService){
		this.residentService = residentService;
	}
	

	@RequestMapping(value = "/issues", method = RequestMethod.GET)
	public ModelAndView getIssues() {
		LOGGER.info("message");		
		List<Issue> issues = residentService.GetIssues(0);		
		return new ModelAndView(jsonView, "data", issues);
	}
	
	@RequestMapping(value = "/referrals", method = RequestMethod.GET)
	public ModelAndView getReferrals() {
		LOGGER.info("message");		
		List<Referral> referrals = residentService.GetReferrals(0);		
		return new ModelAndView(jsonView, "data", referrals);
	}
}
