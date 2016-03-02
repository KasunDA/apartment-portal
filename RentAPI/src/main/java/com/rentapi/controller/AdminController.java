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

import com.rentapi.model.Apt;
import com.rentapi.model.Issue;
import com.rentapi.model.Referral;
import com.rentapi.model.ReferralBase;
import com.rentapi.model.Lease;
import com.rentapi.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	private AdminService adminService;

	private int userId = 6;
	private int staffId = 2;

	@Autowired
	private View jsonView;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/apartment/list", method = RequestMethod.GET)
	public ModelAndView GetAptList(@RequestParam(value = "isActive", required = false) Boolean isActive) {
		List<Apt> aptlist = adminService.GetAptList(isActive);
		return new ModelAndView(jsonView, "data", aptlist);
	}

	@RequestMapping(value = "/apartment/view", method = RequestMethod.GET)
	public ModelAndView GetAptList(@RequestParam(value = "apartmentId", required = false) Integer apartmentId) {
		Apt apt = adminService.GetApt(apartmentId);
		return new ModelAndView(jsonView, "data", apt);
	}

	@RequestMapping(value = "/apartment/create", method = RequestMethod.POST)
	public ModelAndView CreateApt(@RequestBody Apt apartment) {
		Integer aptId = adminService.CreateApt(userId, apartment);
		return new ModelAndView(jsonView, "data", aptId);
	}

	@RequestMapping(value = "/apartment/remove", method = RequestMethod.POST)
	public ModelAndView RemoveApt(@RequestBody Apt apartment) {
		Boolean result = adminService.RemoveApt(userId, apartment.getApartmentId());
		return new ModelAndView(jsonView, "data", result);
	}

	@RequestMapping(value = "/lease/list", method = RequestMethod.GET)
	public ModelAndView GetLeaseList() {
		List<Lease> leaselist = adminService.GetLeaseList();
		return new ModelAndView(jsonView, "data", leaselist);
	}

	@RequestMapping(value = "/lease/create", method = RequestMethod.POST)
	public ModelAndView CreateLease(@RequestBody Lease lease) {
		Integer leaseId = adminService.CreateLease(userId, lease);
		return new ModelAndView(jsonView, "data", leaseId);
	}

	@RequestMapping(value = "/referral/list", method = RequestMethod.GET)
	public ModelAndView getAdminReferralsList() {
		List<Referral> referrals = adminService.GetReferralsList();
		return new ModelAndView(jsonView, "data", referrals);
	}

	@RequestMapping(value = "/referral/approve", method = RequestMethod.POST)
	public ModelAndView ApproveAdminReferral(@RequestBody ReferralBase referral) {
		Boolean result = adminService.ApproveAdminReferral(staffId, referral.getReferralId());
		return new ModelAndView(jsonView, "data", result);
	}

	@RequestMapping(value = "/issues/list", method = RequestMethod.GET)
	public ModelAndView getAdminIssueList() {
		List<Issue> referrallist = adminService.GetIssueList();
		return new ModelAndView(jsonView, "data", referrallist);
	}

	@RequestMapping(value = "/issues/create", method = RequestMethod.GET)
	public ModelAndView createAdminIssue(@RequestBody Issue issue) {
		int issueId = adminService.CreateIssue(userId, issue);
		return new ModelAndView(jsonView, "data", issueId);
	}

}