package com.rentapi.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentapi.model.Resident;
import com.rentapi.model.Staff;
import com.rentapi.data.DataRepository;
import com.rentapi.model.Appointment;
import com.rentapi.model.Apt;
import com.rentapi.model.AptInfo;
import com.rentapi.model.Guest;
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

	public List<Resident> GetAdminResidentList() {
		return repository.GetAdminResidentList();
	}
	
	public Resident GetAdminResident(int residentId){
		return repository.GetAdminResident(residentId);
	}

	public void setupResidentLease(Resident resident) {	
		repository.setupResidentLease(resident);
	}
	
	
	public List<Staff> getStaffList() {
		return repository.GetStaffList();
	}

	public Boolean updateIssue(Issue issue) {
		return repository.updateIssue(issue);
	}
	
	public List<Guest> getAdminGuests(){
		return repository.getAdminGuests();
	}
	
	public List<AptInfo> getAdminAvailablePropertyList() {
		return repository.getAdminAvailablePropertyList();
	}
	
	public List<Appointment> getAdminAppointments(String requestDate){
		return repository.getAdminAppointments(requestDate);
	}

}
