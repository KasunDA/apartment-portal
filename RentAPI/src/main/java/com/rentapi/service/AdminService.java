package com.rentapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentapi.data.DataRepository;
import com.rentapi.model.Apt;
import com.rentapi.model.Issue;
import com.rentapi.model.Lease;
import com.rentapi.model.Referral;

@Component
public class AdminService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

	private DataRepository repository;

	@Autowired
	public AdminService(DataRepository repository) {
		this.repository = repository;
	}

	public List<Apt> GetAptList(Boolean isActive) {
		return repository.GetAptList(isActive);
	}

	public Integer CreateApt(int userId, Apt apartment) {
		return repository.CreateApt(userId, apartment);
	}

	public Boolean RemoveApt(int userId, int apartmentId) {
		try {
			repository.RemoveApt(userId, apartmentId);
			return true;
		} catch (Exception ex) {
			LOGGER.error(ex.toString());
			return false;
		}
	}

	public Apt GetApt(Integer apartmentId) {
		return repository.GetApt(apartmentId);
	}

	public List<Lease> GetLeaseList() {
		return repository.GetAdminLeaseList();
	}

	public Integer CreateLease(int userId, Lease lease) {
		return repository.CreateLease(userId, lease);
	}

	public List<Referral> GetReferralsList() {
		return repository.getAdminReferralsList();
	}

	public List<Issue> GetIssueList() {
		return repository.getAdminIssueList();
	}

	public Integer CreateIssue(Integer userId, Issue issue) {
		return repository.CreateAdminIssue(userId, issue);
	}

	public Boolean ApproveAdminReferral(Integer staffId, Integer referralId) {

		try {
			return repository.ApproveAdminReferral(staffId, referralId);
		} catch (Exception ex) {
			LOGGER.error(ex.toString());
			return false;
		}
	}

}
