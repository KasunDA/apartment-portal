package com.rentapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentapi.data.DataRepository;
import com.rentapi.model.Address;
import com.rentapi.model.Issue;
import com.rentapi.model.Maintenance;
import com.rentapi.model.Referral;
import com.rentapi.model.ResidentIssue;

@Component
public class ResidentService {

	private DataRepository repository;

	@Autowired
	public ResidentService(DataRepository repository) {
		this.repository = repository;
	}

	public List<Issue> GetIssues(int residentId) {
		return repository.GetIssues(residentId);
	}

	public ResidentIssue GetIssue(int issueId, int residentId) {
		return repository.GetResidentIssue(issueId, residentId);
	}

	public Integer CreateIssue(ResidentIssue issue) {
		return repository.CreateIssue(issue);
	}

	public List<Referral> GetReferrals(int residentId) {
		return repository.GetReferrals(residentId);
	}

	public Integer CreateReferral(Referral referral) {
		Integer addressId = repository.SaveAddress(referral.getAddress());		
		referral.getAddress().setAddressID(addressId);		
		return repository.CreateReferral(referral);
	}
	
//	public Integer CreateMaintenance(Maintenance maintenance) {
//		Integer addressId = repository.SaveAddress(maintenance.getAddress());		
//		maintenance.getAddress().setAddressID(addressId);		
//		return repository.CreateMaintenance(maintenance);
//	}
}
