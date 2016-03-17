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

import com.rentapi.model.Issue;
import com.rentapi.model.Maintenance;
import com.rentapi.model.Referral;
import com.rentapi.model.Resident;
import com.rentapi.model.ResidentIssue;
import com.rentapi.service.ResidentService;

@Controller
@RequestMapping("/resident")
public class ResidentController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(ResidentController.class);

	private ResidentService residentService;
	@SuppressWarnings("unused")
	private int userId = 1;
	private int residentId = 1;

	@Autowired
	private View jsonView;

	@Autowired
	public ResidentController(ResidentService residentService) {
		this.residentService = residentService;
	}

	@RequestMapping(value = "/issues", method = RequestMethod.GET)
	public ModelAndView getIssues() {
		List<Issue> issues = residentService.GetIssues(residentId);
		return new ModelAndView(jsonView, "data", issues);
	}

	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public ModelAndView getIssue(@RequestParam(value = "residentId", required = true) int residentId,
			@RequestParam(value = "issueId", required = true) int issueId) {
		ResidentIssue issue = residentService.GetIssue(issueId, residentId);
		return new ModelAndView(jsonView, "data", issue);
	}

	@RequestMapping(value = "/issue/create", method = RequestMethod.POST)
	public ModelAndView CreateIssue(@RequestBody ResidentIssue issue) {
		issue.setResidentID(residentId);
		Integer issueId = residentService.CreateIssue(issue);
		return new ModelAndView(jsonView, "data", issueId);
	}

	@RequestMapping(value = "/referral/list", method = RequestMethod.GET)
	public ModelAndView getReferrals() {
		List<Referral> referrals = residentService.GetReferrals(residentId);
		return new ModelAndView(jsonView, "data", referrals);
	}

	@RequestMapping(value = "/referral/create", method = RequestMethod.POST)
	public ModelAndView CreateReferral(@RequestBody Referral referral) {
		referral.setResidentId(residentId);
		Integer referralId = residentService.CreateReferral(referral);
		return new ModelAndView(jsonView, "data", referralId);
	}
	
//	@RequestMapping(value = "/maintenance/create", method = RequestMethod.POST)
//	public ModelAndView CreateMaintenance(@RequestBody Maintenance maintenance) {
//		maintenance.setResidentID(residentId);
//		Integer maintenanceId = residentService.CreateMaintenance(maintenance);
//		return new ModelAndView(jsonView, "data", maintenanceId);
//	}

	
}
